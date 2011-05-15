package br.com.mailanalyzer.compose;

import br.com.mailanalyzer.domain.Message;
import br.com.mailanalyzer.fluxo.InterfaceComposeFlow;
import br.com.mailanalyzer.fluxo.MutableComponent;
import br.com.mailanalyzer.fluxo.PropertyRetriever;
import br.com.mailanalyzer.log.Log;
import br.com.mailanalyzer.main.Base;

/**
 * Classe para substituir caracteres especiais em um texto.
 * @author Guilherme Lucas
 * @contact gfaria.mello@gmail.com
 * @version 1.0
 * @Date 07-05-2011
 *
 */
public class FiltroSubstituirCaracter implements InterfaceComposeFlow, PropertyRetriever, MutableComponent {
    private boolean stop = false;
    public void execute() {
        Log.d(this.getClass().getSimpleName(), "Executando...");
        try{
            msg = Filtrar(msg);
        }catch(Exception ex){
            Log.d(this.getClass().getSimpleName(), ex);
        }
        
        Log.d(this.getClass().getSimpleName(), "Finalizado.");
    }

    public boolean stopFlow() {
        return stop;
    }

    public String Filtrar(String text) {
        return text.replaceAll("[�����]", "a").replaceAll("[����]", "e").replaceAll("[����]", "i").replaceAll("[�����]", "o").replaceAll("[����]", "u").replaceAll("[�����]", "A").replaceAll("[����]", "E").replaceAll("[����]", "I").replaceAll("[�����]", "O").replaceAll("[����]", "U").replace('�', 'c').replace('�', 'C').replace('�', 'n').replace('�', 'N').replace("\\", "").replaceAll("[�`#$%�*&]", "").replaceAll("[()={}\\[\\]~^]", "").replaceAll("[-_+'��/�]", "");
    }
    

    public Object getPropertyName() {
        return Base.FIELD_FILTRO_CARACTER;
    }

    public Object getPropertyValue() {
        return msg;
    }

    public void updateComponent(Object obj) {
        if(obj instanceof String){
            this.msg = (String) obj;
        }else if(obj instanceof Message){
            this.msg = ((Message)obj).getMensagem();
        }else{
            this.msg = null;
        }
    }
    
    private String msg;
}
