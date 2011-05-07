package br.com.mailanalyzer.fluxo;

import br.com.mailanalyzer.commands.CommandFluxo;
import br.com.mailanalyzer.commands.CommandListener;
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
            mainRunnable.run();
        } else {
            if (componentsFlow[index] instanceof MutableComponent) {
                if (index > 0 && (componentsFlow[index - 1] instanceof PropertyRetriever)) {
                    Object retiever = ((PropertyRetriever) componentsFlow[index - 1]).getPropertyValue();
                    ((MutableComponent) componentsFlow[index]).updateComponent(retiever);
                } else {
                    ((MutableComponent) componentsFlow[index]).updateComponent(null);
                }
                if (componentsFlow[index].stopFlow()) {
                    System.gc();
                    return;
                }
            }
            if(componentsFlow[index] instanceof Fluxo){
                Fluxo f = (Fluxo)componentsFlow[index];
                f.init();
            }else{
                ((InterfaceComposeFlow) componentsFlow[index]).execute();
            }
        }
    }

    public void next() {
        //Verifica se deve continuar
        if (componentsFlow[index].stopFlow()) {
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
            } else if (propertyRetriever.getPropertyValue() instanceof Object[]) {
                Object[] objs = (Object[]) propertyRetriever.getPropertyValue();
                String[] keys = (String[]) propertyRetriever.getPropertyValue();
                for (int i = 0; i < objs.length; i++) {
                    String key = keys[i];
                    Object value = objs[i];
                    parameters.put(key, value);
                }
            } else {

                parameters.put(propertyRetriever.getPropertyName(), propertyRetriever.getPropertyValue());
            }
        }
        index++;
        if (index < componentsFlow.length) {
            if (componentsFlow[index] instanceof MutableComponent) {
                if (index > 0 && (componentsFlow[index - 1] instanceof PropertyRetriever)) {
                    Object retiever = ((PropertyRetriever) componentsFlow[index - 1]).getPropertyValue();
                    ((MutableComponent) componentsFlow[index]).updateComponent(retiever);
                } else {
                    ((MutableComponent) componentsFlow[index]).updateComponent(null);
                }
                if (componentsFlow[index].stopFlow()) {
                    System.gc();
                    return;
                }
            }
            if(componentsFlow[index] instanceof Fluxo){
                Fluxo f = (Fluxo)componentsFlow[index];
                f.init();
            }else{
                ((InterfaceComposeFlow) componentsFlow[index]).execute();
            }
        } else {
            executeCommand();
        }
        //AlimutuMidlet.display.setCurrent(getVerificationAlert(), AlimutuMidlet.menuPrincipal);
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
}
