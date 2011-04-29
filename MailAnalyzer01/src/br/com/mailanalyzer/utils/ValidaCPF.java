package br.com.mailanalyzer.utils;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 29-04-2011
 *
 */
public class ValidaCPF {
    
    /**
     * Nao pode ser instanciada
     */
    private ValidaCPF(){
    }

    private static String calcDigVerif(String num) {
        Integer primDig, segDig;
        int soma = 0, peso = 10;
        for (int i = 0; i < num.length(); i++) {
            soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;
        }

        if (soma % 11 == 0 | soma % 11 == 1) {
            primDig = new Integer(0);
        } else {
            primDig = new Integer(11 - (soma % 11));
        }

        soma = 0;
        peso = 11;
        for (int i = 0; i < num.length(); i++) {
            soma += Integer.parseInt(num.substring(i, i + 1)) * peso--;
        }

        soma += primDig.intValue() * 2;
        if (soma % 11 == 0 | soma % 11 == 1) {
            segDig = new Integer(0);
        } else {
            segDig = new Integer(11 - (soma % 11));
        }

        return primDig.toString() + segDig.toString();
    }

    private static int calcSegDig(String cpf, int primDig) {
        int soma = 0, peso = 11;
        for (int i = 0; i < cpf.length(); i++) {
            soma += Integer.parseInt(cpf.substring(i, i + 1)) * peso--;
        }

        soma += primDig * 2;
        if (soma % 11 == 0 | soma % 11 == 1) {
            return 0;
        } else {
            return 11 - (soma % 11);
        }
    }

    /**
     * Funcao geradora de CPF para testes.
     * @return Uma String contendo um CPF valido
     */
    public static String geraCPF() {
        String iniciais = "";
        Integer numero;
        for (int i = 0; i < 9; i++) {
            numero = new Integer((int) (Math.random() * 10));
            iniciais += numero.toString();
        }
        return iniciais + calcDigVerif(iniciais);
    }

    /**
     * Verifica a autenticidade de um CPF validando os numeros com o digitos verificadores
     * @param cpf Uma String contendo o CPF a ser validado
     * @return Um boolean representando se o CPF foi ou nao validado
     */
    public static boolean validaCPF(String cpf) {
        if(cpf==null)
            return false;
        if (cpf.length() != 11) {
            return false;
        }

        String numDig = cpf.substring(0, 9);
        return calcDigVerif(numDig).equals(cpf.substring(9, 11));
    }
}
