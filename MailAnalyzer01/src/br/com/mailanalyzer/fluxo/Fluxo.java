package br.com.mailanalyzer.fluxo;

import br.com.mailanalyzer.commands.CommandFluxo;
import br.com.mailanalyzer.commands.CommandListener;
import br.com.mailanalyzer.log.Log;
import java.util.Hashtable;

public abstract class Fluxo {

    private Runnable mainRunnable;

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
        index = 0;
        if (componentsFlow == null || componentsFlow.length == 0) {
            //mainRunnable.run();
            executeCommand();
        } else {
            if (componentsFlow[index] instanceof MutableComponent) {
                if (index > 0 && (componentsFlow[index - 1] instanceof PropertyRetriever)) {
                    Object retiever = ((PropertyRetriever) componentsFlow[index - 1]).getPropertyValue();
                    ((MutableComponent) componentsFlow[index]).updateComponent(retiever);
                } else {
                    ((MutableComponent) componentsFlow[index]).updateComponent(firstObject);
                }
                if (componentsFlow[index].stopFlow()) {
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
            Log.d(this.getClass().getSimpleName(),"Fluxo ordenou parada imediata.");
            System.gc();
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
                String name = (String) propertyRetriever.getPropertyName();
                Object value = propertyRetriever.getPropertyValue();
                Log.d(this.getClass().getSimpleName(),"FOR: " + name + " - value is: " + value);
                parameters.put(name, value);
            }
        }
        Log.d(this.getClass().getSimpleName(),"Indo para a proxima componente do fluxo...");
        index++;
        if (index < componentsFlow.length) {
            if (componentsFlow[index] instanceof MutableComponent) {
                Log.d(this.getClass().getSimpleName(),"Componente "+componentsFlow[index].getClass().getName()+" e' um MutableComponent.");
                if (index > 0 && (componentsFlow[index - 1] instanceof PropertyRetriever)) {
                    Object retiever = ((PropertyRetriever) componentsFlow[index - 1]).getPropertyValue();
                    Log.d(this.getClass().getSimpleName(),"Componente anterior e' um PropertyRetriever. Pegando informacao dele para atualizar componente atual. Valor: "+retiever);
                    ((MutableComponent) componentsFlow[index]).updateComponent(retiever);
                } else {
                    Log.d(this.getClass().getSimpleName(),"Componente anterior nao e' um PropertyRetriever. Enviando null para componente atual.");
                    ((MutableComponent) componentsFlow[index]).updateComponent(null);
                }
                if (componentsFlow[index].stopFlow()) {
                    Log.d("Fluxo.next()","Componente "+componentsFlow[index].getClass().getName()+" solicitou parada imediata do fluxo.");
                    System.gc();
                    return;
                }
            }
            if (componentsFlow[index] instanceof Fluxo) {
                Log.d(this.getClass().getSimpleName(),"Componente "+componentsFlow[index].getClass().getName()+" eh um fluxo. Novo fluxo sera iniciado.");
                Fluxo f = (Fluxo) componentsFlow[index];
                f.init();
            } else {
                Log.d(this.getClass().getSimpleName(),"Componente "+componentsFlow[index].getClass().getName()+" sera executado agora.");
                ((InterfaceComposeFlow) componentsFlow[index]).execute();
                next();
            }
        } else {
            Log.d(this.getClass().getSimpleName(),"Fim do fluxo. Iniciar comando do fluxo "+this.getClass().getName());
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
