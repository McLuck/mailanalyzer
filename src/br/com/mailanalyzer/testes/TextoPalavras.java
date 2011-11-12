/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.testes;

import br.com.mailanalyzer.analise2.UtilsString;
import br.com.mailanalyzer.dao.TextoDAO;
import br.com.mailanalyzer.domain.Palavra;
import br.com.mailanalyzer.domain.Texto;
import java.util.ArrayList;

/**
 *
 * @author McLuck
 */
public class TextoPalavras {
    public static void main(String[] args) {
        
    }

    public static void alterar(){
        TextoDAO tdao = new TextoDAO();
        Texto t = tdao.obter(2);
        t.setAssunto("Teste de palavras");
        System.out.println(t.getAssunto());
        for(Palavra p : t.getPalavras()){
            System.out.print(" "+p.getPalavra());
        }
        System.out.println("");
        t.getPalavras().add(new Palavra("deseja"));
        tdao.salvar(t);
        tdao.commit();
        tdao.close();
    }

    public static void print(){
        TextoDAO tdao = new TextoDAO();
        Texto t = tdao.obter(2);
        System.out.println(t.getAssunto());
        for(Palavra p : t.getPalavras()){
            System.out.print(" "+p.getPalavra());
        }
        tdao.close();
    }

    public static void salvar(){
        Palavra p1 = new Palavra();
        p1.setPalavra("Lucas");

        Palavra p2 = new Palavra();
        p2.setPalavra("deseja");

        Palavra p3 = new Palavra();
        p3.setPalavra("almoçar");

        Palavra p4 = new Palavra();
        p4.setPalavra("agora");

        Texto t1 = new Texto();
        t1.setPalavras(new ArrayList<Palavra>());
        t1.getPalavras().add(p1);
        t1.getPalavras().add(p2);
        t1.getPalavras().add(p3);
        t1.getPalavras().add(p4);

        TextoDAO tdao = new TextoDAO();
        tdao.salvar(t1);
        tdao.commit();
        tdao.close();
        System.out.println("Salvo com sucesso!");
    }
}
