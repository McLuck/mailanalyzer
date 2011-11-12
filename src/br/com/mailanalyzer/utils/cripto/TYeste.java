/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mailanalyzer.utils.cripto;

/**
 *
 * @author lucasisrael
 */
public class TYeste {
    public static void main(String[]args) throws Exception{
        Cryptography cryMD5 = HashFactory.MD5();
        Cryptography crySHA2 = HashFactory.SHA256();
        Cryptography crySHA512 = HashFactory.SHA512();
        
        String nome = "Lucas Israel";
        
        System.out.println(cryMD5.encrypt(nome));
        System.err.println("--------");
        nome = "Lucas Israel";
        Thread.sleep(100);
        System.out.println(crySHA2.encrypt(nome));
        System.err.println("--------");
        nome = "Lucas Israel";
        Thread.sleep(100);
        System.out.println(crySHA512.encrypt(nome));
    }
}
