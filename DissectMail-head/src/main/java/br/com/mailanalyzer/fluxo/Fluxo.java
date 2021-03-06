package br.com.mailanalyzer.fluxo;

import br.com.mailanalyzer.commands.CommandFluxo;
import br.com.mailanalyzer.log.L;
import br.com.mailanalyzer.main.Config;
import java.util.Hashtable;

public abstract class Fluxo {

    private Runnable mainRunnable;
    public static final String TAG = "Fluxo";

    public void setMAIN_RETURN(Runnable mainRunnable) {
        this.mainRunnable = mainRunnable;
    }

    public Fluxo getFluxoCorrente() {
        return fluxoCorrente;
    }

    public void setFluxoCorrente(Fluxo fluxoCorrente) {
        this.fluxoCorrente = fluxoCorrente;
    }

    public Fluxo(String name, InterfaceComposeFlow componentsFlow[]) {
        this.name = name;
        this.componentsFlow = componentsFlow;
        index = -1;
        parameters = new Hashtable();
    }

    public void init() {
        L.d(TAG, this, "Iniciando Fluxo");

        index = 0;
        if (componentsFlow == null || componentsFlow.length == 0) {
            //mainRunnable.run();
            if(Config.isNivelLogMaximo()){
                L.d(TAG, this, "Iniciando sem nenhum comando. Indo direto para o comando do fluxo.");
            }
            executeCommand();
        } else {
            if (componentsFlow[index] instanceof MutableComponent) {
                if (index > 0 && (componentsFlow[index - 1] instanceof PropertyRetriever)) {
                    if(Config.isNivelLogMaximo()){
                        L.d(TAG, this, "Iniciando comando retiever");
                    }
                    Object retiever = ((PropertyRetriever) componentsFlow[index - 1]).getPropertyValue();
                    ((MutableComponent) componentsFlow[index]).updateComponent(retiever);
                } else {
                    if(Config.isNivelLogMaximo()){
                        L.d(TAG, this, "Iniciando comando firstObject");
                    }
                    ((MutableComponent) componentsFlow[index]).updateComponent(firstObject);
                }
                if (componentsFlow[index].stopFlow()) {
                    if(Config.isNivelLogMaximo()){
                        L.d(TAG, this, "Parando Fluxo");
                    }
                    System.gc();
                    return;
                }
            }
            if (componentsFlow[index] instanceof Fluxo) {
                Fluxo f = (Fluxo) componentsFlow[index];
                f.init();
            } else {
                ((InterfaceComposeFlow) componentsFlow[index]).execute();
                next();
            }
        }
    }

    public void next() {
        //Verifica se deve continuar
        if (componentsFlow[index].stopFlow()) {
            if (Config.isNivelLogMedio() || Config.isNivelLogMaximo()) {
                L.i(name, this, "Fluxo ordenou parada imediata");
            }
            return;
        }
        if (componentsFlow[index] instanceof PropertyRetriever) {
            PropertyRetriever propertyRetriever = (PropertyRetriever) componentsFlow[index];

            if (propertyRetriever.getPropertyValue() instanceof String[]) {
                String[] values = (String[]) propertyRetriever.getPropertyValue();
                String[] keys = (String[]) propertyRetriever.getPropertyValue();
                for (int i = 0; i < values.length; i++) {
                    String key = keys[i];
                    String value = values[i];
                    parameters.put(key, value);
                }
            } else if (propertyRetriever.getPropertyValue() instanceof Object[] && propertyRetriever.getPropertyName() instanceof String[]) {
                Object[] objs = (Object[]) propertyRetriever.getPropertyValue();
                String[] keys = (String[]) propertyRetriever.getPropertyName();
                for (int i = 0; i < objs.length; i++) {
                    String key = keys[i];
                    Object value = objs[i];
                    parameters.put(key, value);
                }
            } else {
                String namee = (String) propertyRetriever.getPropertyName();
                Object value = propertyRetriever.getPropertyValue();
                parameters.put(namee, value);
            }
        }
        if (!Config.isNivelLogBaixo()) {
            L.d(name, this, "Indo para o pr�ximo componente do fluxo...");
        }

        index++;
        if (index < componentsFlow.length) {
            if (componentsFlow[index] instanceof MutableComponent) {
                if (!Config.isNivelLogBaixo()) {
                    L.d(name, this, "Indo para o pr�ximo componente do fluxo...");
                }

                if (index > 0 && (componentsFlow[index - 1] instanceof PropertyRetriever)) {
                    Object retiever = ((PropertyRetriever) componentsFlow[index - 1]).getPropertyValue();
                    if (!Config.isNivelLogBaixo()) {
                        L.d(name, this, "Componente anterior e' um PropertyRetriever. Pegando informacao dele para atualizar componente atual. Valor: " + retiever);
                    }
                    ((MutableComponent) componentsFlow[index]).updateComponent(retiever);
                } else {
                    if (!Config.isNivelLogBaixo()) {
                        L.d(name, this, "Componente anterior nao e' um PropertyRetriever. Enviando null para componente atual.");
                    }
                    ((MutableComponent) componentsFlow[index]).updateComponent(null);
                }
                if (componentsFlow[index].stopFlow()) {
                    if (!Config.isNivelLogBaixo()) {
                        L.d(name, this, "Componente " + componentsFlow[index].getClass().getName() + " solicitou parada imediata do fluxo.");
                    }
                    System.gc();
                    return;
                }
            }
            if (componentsFlow[index] instanceof Fluxo) {
                if (!Config.isNivelLogBaixo()) {
                        L.d(name, this, "Componente " + componentsFlow[index].getClass().getName() + " eh um fluxo. Novo fluxo sera iniciado.");
                }
                Fluxo f = (Fluxo) componentsFlow[index];
                f.init();
            } else {
                if (!Config.isNivelLogBaixo()) {
                        L.d(name, this, "Componente " + componentsFlow[index].getClass().getName() + " sera executado agora.");
                }
                ((InterfaceComposeFlow) componentsFlow[index]).execute();
                next();
            }
        } else {
            if (!Config.isNivelLogBaixo()) {
                        L.d(name, this, "Fim do fluxo. Iniciar comando do fluxo " + this.getClass().getName());
            }
            executeCommand();
        }
    }

    public void previous() {
        if (index > 0) {
            index--;
            ((InterfaceComposeFlow) componentsFlow[index]).execute();
        } else {
            setFluxoCorrente(null);
            mainRunnable.run();
        }
    }

    private void executeCommand() {
        CommandFluxo command = getCommandFluxo();
        if (command != null) {
            command.setParameters(parameters);
            command.setNomeFluxo(name);
            command.setFirstObject(firstObject);
            if (command.startNewThread()) {
                Thread thread = new Thread(command);
                thread.start();
            } else {
                command.run();
            }
        }
    }

    public void notificaResultado(boolean result) {
        //Chama alguma acao pos-resultado
        if (result) {
            //LOG EVENTO/AVISO
        } else {
            //LOG ERRO
        }
    }

    public abstract CommandFluxo getCommandFluxo();

    public String getParameter(String parameterName) {
        return (String) parameters.get(parameterName);
    }

    protected InterfaceComposeFlow[] getComponentsFlow() {
        return componentsFlow;
    }
    private Fluxo fluxoCorrente;
    private InterfaceComposeFlow componentsFlow[];
    private int index;
    private String name;
    protected Hashtable parameters;
    protected Object firstObject = null;
}
