package br.com.mailanalyzer.compose;

import br.com.mailanalyzer.domain.Message;
import br.com.mailanalyzer.fluxo.InterfaceComposeFlow;
import br.com.mailanalyzer.fluxo.MutableComponent;
import br.com.mailanalyzer.fluxo.PropertyRetriever;
import br.com.mailanalyzer.log.L;
import br.com.mailanalyzer.main.Base;
import br.com.mailanalyzer.main.Config;

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
    private static final String TAG = "Filtro de substituicao de caracteres"; 
    public void execute() {
        try{
        	if(Config.isNivelLogMaximo()){
        		L.d(TAG, this.getClass(), "Filtro para substituicao de caracter especial na mensagem: \n".concat(msg));
        	}
            msg = Filtrar(msg);
            if(Config.isNivelLogMaximo()){
        		L.d(TAG, this.getClass(), "Filtro para substituicao de caracter especial foi aplicado na mensagem: \n".concat(msg));
        	}
        }catch(Exception ex){
            L.e(TAG, this, "Falhou ao tentar substituir caracteres na mensagem", ex);
        }
        
    }

    public boolean stopFlow() {
        return stop;
    }

    public String Filtrar(String text) {
        return text.replaceAll("[„‚‡·‰]", "a").replaceAll("[ÍËÈÎ]", "e").replaceAll("[ÓÏÌÔ]", "i").replaceAll("[ıÙÚÛˆ]", "o").replaceAll("[˚˙˘¸]", "u").replaceAll("[√¬¿¡ƒ]", "A").replaceAll("[ »…À]", "E").replaceAll("[ŒÃÕœ]", "I").replaceAll("[’‘“”÷]", "O").replaceAll("[€Ÿ⁄‹]", "U").replace('Á', 'c').replace('«', 'C').replace('Ò', 'n').replace('—', 'N').replace("\\", "").replaceAll("[¥`#$%®*&]", "").replaceAll("[()={}\\[\\]~^]", "").replaceAll("[-_+'™∫/¨]", "");
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
