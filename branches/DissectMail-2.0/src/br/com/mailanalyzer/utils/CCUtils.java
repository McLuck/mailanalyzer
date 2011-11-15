package br.com.mailanalyzer.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Esta classe verifica e valida numeros de cartoes de creditos.<br/>
 * Ela tambem gera numeros para testes.
 * 
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 29-04-2011
 *
 */
public class CCUtils {
    
    /*
     * Nao pode ser instanciada
     */
    private CCUtils(){
        
    }

    public static final int INVALID = -1;
    public static final int VISA = 0;
    public static final int MASTERCARD = 1;
    public static final int AMERICAN_EXPRESS = 2;
    public static final int EN_ROUTE = 3;
    public static final int DINERS_CLUB = 4;
    public static final int CODEONE = 5;
    private static final String[] cardNames = {"Visa",
        "Mastercard",
        "American Express",
        "En Route",
        "Diner's CLub/Carte Blanche",};

    /**
     * Valid a Credit Card number<br/>
     * Verifica se o numero informado e valido
     * @param number String com o numero a ser verificado
     * @return Um booleano informando se e valido ou nao
     * @throws Exception Possiveis excecoes ocorridas por erro da entrada fornecida
     */
    public static boolean validCC(String number)
            throws Exception {
        int CardID;
        if ((CardID = getCardID(number)) != -1) {
            return validCCNumber(number);
        }
        return false;
    }

    /**
     * Get the Card type
     * returns the credit card type
     * INVALID = -1;
     * VISA = 0;
     * MASTERCARD = 1;
     * AMERICAN_EXPRESS = 2;
     * EN_ROUTE = 3;
     * DINERS_CLUB = 4;
     * @param Number Numero do cartao
     * @return Um inteiro com o digito do tipo do cartao(Bandeira)
     */
    public static int getCardID(String number) {
        int valid = INVALID;

        String digit1 = number.substring(0, 1);
        String digit2 = number.substring(0, 2);
        String digit3 = number.substring(0, 3);
        String digit4 = number.substring(0, 4);

        if (isNumber(number)) {
            /* ----
             ** VISA prefix=4
             ** ---- length=13 or 16 (can be 15 too!?! maybe)
             */
            if (digit1.equals("4")) {
                if (number.length() == 13 || number.length() == 16) {
                    valid = VISA;
                }
            } /* ----------
             ** MASTERCARD prefix= 51 ... 55
             ** ---------- length= 16
             */ else if (digit2.compareTo("51") >= 0 && digit2.compareTo("55") <= 0) {
                if (number.length() == 16) {
                    valid = MASTERCARD;
                }
            } /* ----
             ** AMEX prefix=34 or 37
             ** ---- length=15
             */ else if (digit2.equals("34") || digit2.equals("37")) {
                if (number.length() == 15) {
                    valid = AMERICAN_EXPRESS;
                }
            } /* -----
             ** ENROU prefix=2014 or 2149
             ** ----- length=15
             */ else if (digit4.equals("2014") || digit4.equals("2149")) {
                if (number.length() == 15) {
                    valid = EN_ROUTE;
                }
            } /* -----
             ** DCLUB prefix=300 ... 305 or 36 or 38
             ** ----- length=14
             */ else if (digit2.equals("36") || digit2.equals("38")
                    || (digit3.compareTo("300") >= 0 && digit3.compareTo("305") <= 0)) {
                if (number.length() == 14) {
                    valid = DINERS_CLUB;
                }
            } else if (digit3.equals("888")) {
                if (number.length() == 16) {
                    valid = CODEONE;
                }
            }
        }
        return valid;

        /* ----
         ** DISCOVER card prefix = 60
         ** -------- lenght = 16
         ** left as an exercise ...
         */

    }

    public static boolean isNumber(String n) {
        try {
            double d = Double.valueOf(n).doubleValue();
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getCardName(int id) {
        return (id > -1 && id < cardNames.length ? cardNames[id] : "");
    }

    public static boolean validCCNumber(String n) {
        try {
            /*
             ** known as the LUHN Formula (mod10)
             */
            int j = n.length();

            String[] s1 = new String[j];
            for (int i = 0; i < n.length(); i++) {
                s1[i] = "" + n.charAt(i);
            }

            int checksum = 0;

            for (int i = s1.length - 1; i >= 0; i -= 2) {
                int k = 0;

                if (i > 0) {
                    k = Integer.valueOf(s1[i - 1]).intValue() * 2;
                    if (k > 9) {
                        String s = "" + k;
                        k = Integer.valueOf(s.substring(0, 1)).intValue()
                                + Integer.valueOf(s.substring(1)).intValue();
                    }
                    checksum += Integer.valueOf(s1[i]).intValue() + k;
                } else {
                    checksum += Integer.valueOf(s1[0]).intValue();
                }
            }
            return ((checksum % 10) == 0);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String args[]) throws Exception {
        //482864 492022
        int qtd = 0;
        int qtd2 = 0;
        System.out.println(new java.util.Date());
        for (int i = 0; i <= 9999; i++) {
            String a = "482864";
            String b = getFrt(i);
            String c = "492022";

            String aCard = a.concat(b);
            aCard = aCard.concat(c);
            if (getCardID(aCard) > -1) {
                System.out.println("This card is supported.");
                System.out.println("This a " + getCardName(getCardID(aCard)));
                boolean tvalid = validCC(aCard);
                System.out.println("The card number " + aCard + " is "
                        + (tvalid ? " good." : " bad."));
                System.out.println("---");
                qtd++;
                if (tvalid) {
                    qtd2++;
                }
            } /*else {
            System.out.println("This card is invalid or unsupported!");
            }*/
        }
        System.out.println("-------------");
        System.out.println("-------------");
        System.out.println("-------------");
        System.out.println("Quantidades de combina��es poss�veis: " + qtd);
        System.out.println("Quantidades de combina��es poss�veis validas: " + qtd2);
        System.out.println(new java.util.Date());
    }

    public static String getFrt(int num) {
        String as = String.valueOf(num);
        while (as.length() < 4) {
            as = "0".concat(as);
        }
        return as;
    }
}
