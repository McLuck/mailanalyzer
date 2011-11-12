package br.com.mailanalyzer.utils;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 29-04-2011
 *
 */
public class VerhoeffUtil {
    
    /**
     * Nova instancia de VerhoeffUtil. <br />
     * Esta classe e usada para gerar digito verificado em uma sequencia de numeros.
     * Isto e largamente utilizado em validacoes de numeros bancarios (cartoes de creditos, cheques), documentos governamentais e em muitos protocolos de diversos sistemas.
     */
    public VerhoeffUtil(){
    }
    
    // The multiplication table
    static int[][] d = {
        {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
        {1, 2, 3, 4, 0, 6, 7, 8, 9, 5},
        {2, 3, 4, 0, 1, 7, 8, 9, 5, 6},
        {3, 4, 0, 1, 2, 8, 9, 5, 6, 7},
        {4, 0, 1, 2, 3, 9, 5, 6, 7, 8},
        {5, 9, 8, 7, 6, 0, 4, 3, 2, 1},
        {6, 5, 9, 8, 7, 1, 0, 4, 3, 2},
        {7, 6, 5, 9, 8, 2, 1, 0, 4, 3},
        {8, 7, 6, 5, 9, 3, 2, 1, 0, 4},
        {9, 8, 7, 6, 5, 4, 3, 2, 1, 0}
    };
    // The permutation table
    static int[][] p = {
        {0, 1, 2, 3, 4, 5, 6, 7, 8, 9},
        {1, 5, 7, 6, 2, 8, 3, 0, 9, 4},
        {5, 8, 0, 3, 7, 9, 6, 1, 4, 2},
        {8, 9, 1, 6, 0, 4, 3, 5, 2, 7},
        {9, 4, 5, 3, 1, 2, 6, 8, 7, 0},
        {4, 2, 8, 6, 5, 7, 3, 9, 0, 1},
        {2, 7, 9, 3, 8, 0, 6, 4, 1, 5},
        {7, 0, 4, 6, 9, 1, 3, 2, 5, 8}
    };
    // The inverse table
    static int[] inv = {0, 4, 3, 2, 1, 5, 6, 7, 8, 9};

    
    /**
     * Validates that an entered number is Verhoeff compliant.<br/>
     * NB: Make sure the check digit is the last one.
     * @param num
     * @return 
     */
    public static boolean validateVerhoeff(String num) {
        int c = 0;
        int[] myArray = StringToReversedIntArray(num);
        for (int i = 0; i < myArray.length; i++) {
            c = d[c][p[(i % 8)][myArray[i]]];
        }
        return (c == 0);
    }

    public static void main(String []ar){
        String num = "12563876874";
        System.out.println(VerhoeffUtil.generateVerhoeff(num));
    }

    /*
     * Converts a string to a reversed integer array.
     */
    private static int[] StringToReversedIntArray(String num) {
        int[] myArray = new int[num.length()];
        for (int i = 0; i < num.length(); i++) {
            myArray[i] = Integer.parseInt(num.substring(i, i + 1));
        }
        myArray = Reverse(myArray);
        return myArray;
    }

    
    /**
     * For a given number generates a Verhoeff digit
     * @param num String uma String contendo o numero a ser gerado o verificador
     * @return Uma String contendo o digito verificador para o parametro informado
     */
    public static String generateVerhoeff(String num) {

        int c = 0;
        int[] myArray = StringToReversedIntArray(num);

        for (int i = 0; i < myArray.length; i++) {
            c = d[c][p[((i + 1) % 8)][myArray[i]]];
        }

        return Integer.toString(inv[c]);
    }

    /*
     * Reverses an int array
     */
    private static int[] Reverse(int[] myArray) {
        int[] reversed = new int[myArray.length];

        for (int i = 0; i < myArray.length; i++) {
            reversed[i] = myArray[myArray.length - (i + 1)];
        }
        return reversed;
    }
}
