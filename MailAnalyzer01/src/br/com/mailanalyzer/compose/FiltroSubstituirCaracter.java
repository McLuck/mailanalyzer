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

        return text.replaceAll("[�����]", "a").replaceAll("[����]", "e").replaceAll("[����]", "i").replaceAll("[�����]", "o").replaceAll("[����]", "u").replaceAll("[�����]", "A").replaceAll("[����]", "E").replaceAll("[����]", "I").replaceAll("[�����]", "O").replaceAll("[����]", "U").replace('�', 'c').replace('�', 'C').replace('�', 'n').replace('�', 'N').replace("\\", "").replaceAll("[�`#$%�*]", "").replaceAll("[()={}\\[\\]~^]", "").replaceAll("[-_+'��/�]", "");

    }
    /*Teste para verificar funcionamento da classe.
    public static void main(String[] args) {

    FiltroSubstituirCaracter a = new FiltroSubstituirCaracter();
    String s = "T�st� r�m�v�r ���nt����� � c�r�ct�r �sp�c���s. #%$#AE$W!%$^$%*& ()={}[]~^ -_+'��/�";
    s = a.Filtrar(s);
    System.out.println (s);
    
     }
  */
}
