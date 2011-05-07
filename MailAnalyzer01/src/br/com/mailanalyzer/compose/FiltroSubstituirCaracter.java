package br.com.mailanalyzer.compose;

import br.com.mailanalyzer.fluxo.InterfaceComposeFlow;

/**
 * Classe para substituir caracteres especiais em um texto.
 * @author Guilherme Lucas
 * @contact gfaria.mello@gmail.com
 * @version 1.0
 * @Date 07-05-2011
 *
 */
public class FiltroSubstituirCaracter implements InterfaceComposeFlow {

    public void execute() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public boolean stopFlow() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String Filtrar(String text) {

        return text.replaceAll("[ãâàáä]", "a").replaceAll("[êèéë]", "e").replaceAll("[îìíï]", "i").replaceAll("[õôòóö]", "o").replaceAll("[ûúùü]", "u").replaceAll("[ÃÂÀÁÄ]", "A").replaceAll("[ÊÈÉË]", "E").replaceAll("[ÎÌÍÏ]", "I").replaceAll("[ÕÔÒÓÖ]", "O").replaceAll("[ÛÙÚÜ]", "U").replace('ç', 'c').replace('Ç', 'C').replace('ñ', 'n').replace('Ñ', 'N').replace("\\", "").replaceAll("[´`#$%¨*]", "").replaceAll("[()={}\\[\\]~^]", "").replaceAll("[-_+'ªº/¬]", "");

    }
    /*Teste para verificar funcionamento da classe.
    public static void main(String[] args) {

    FiltroSubstituirCaracter a = new FiltroSubstituirCaracter();
    String s = "Tésté rémóvér âçëntúÁçãó ê cáráctér êspëcïäïs. #%$#AE$W!%$^$%*& ()={}[]~^ -_+'ªº/¬";
    s = a.Filtrar(s);
    System.out.println (s);
    
     }
  */
}
