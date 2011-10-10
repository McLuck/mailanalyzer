package br.com.mailanalyzer.commands;

/**
 *
 * @author McLuck
 */
import java.util.Hashtable;
public abstract class CommandFluxo implements Command{
    public abstract void run();
    public CommandFluxo(){}
    private Hashtable parameters;
    private String nomeFluxo;
    protected Object firstObject;

    /**
     * @return the parameters
     */
    public Hashtable getParameters() {
        return parameters;
    }

    /**
     *
     * @return if Command will be run in thread
     */
    public boolean startNewThread() {
    	return true;
    }

    /**
     * @param parameters the parameters to set
     */
    public void setParameters(Hashtable parameters) {
        this.parameters = parameters;
    }

    /**
     * @return the nomeFluxo
     */
    public String getNomeFluxo() {
        return nomeFluxo;
    }

    /**
     * @param nomeFluxo the nomeFluxo to set
     */
    public void setNomeFluxo(String nomeFluxo) {
        this.nomeFluxo = nomeFluxo;
    }

    /**
     * @return the firstObject
     */
    public Object getFirstObject() {
        return firstObject;
    }

    /**
     * @param firstObject the firstObject to set
     */
    public void setFirstObject(Object firstObject) {
        this.firstObject = firstObject;
    }
}