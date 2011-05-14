package br.com.mailanalyzer.compose;

import br.com.mailanalyzer.fluxo.InterfaceComposeFlow;
import br.com.mailanalyzer.fluxo.MutableComponent;
import br.com.mailanalyzer.fluxo.PropertyRetriever;
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

    public void execute() {
        msg = Filtrar(msg);
    }

    public boolean stopFlow() {
        return false;
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
        this.msg = (String)obj;
    }
    
    private String msg;
}
