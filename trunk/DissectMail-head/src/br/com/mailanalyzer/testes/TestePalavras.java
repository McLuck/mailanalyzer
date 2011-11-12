/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.testes;

import br.com.mailanalyzer.dao.PalavraDAO;
import br.com.mailanalyzer.domain.Palavra;
import br.com.mailanalyzer.utils.GenericsUtil;
import br.com.mailanalyzer.utils.ListPalavra;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author McLuck
 */
public class TestePalavras {
    public static void main(String[] args) {
        String input = "Lucas deseja almoçar ao meio dia";
        PalavraDAO pdao = PalavraDAO.getInstance();
        ListPalavra lista = pdao.getPalavrasSalvas(input);
        for(Palavra p : lista){
            System.out.println(p.getId()+" - "+p.getPalavra());
        }
    }

    public static void testeEqualsAndCast(){
        Palavra p1 = new Palavra();
        p1.setPalavra("Lucas");

        Palavra p2 = new Palavra();
        p2.setPalavra("deseja");

        Palavra p3 = new Palavra();
        p3.setPalavra("almoçar");

        Palavra p4 = new Palavra();
        p4.setPalavra("agora");

        List<Palavra> lista = new ArrayList<Palavra>();
        lista.add(p1);
        lista.add(p2);
        lista.add(p3);
        lista.add(p4);

        ListPalavra lista2 = new ListPalavra(GenericsUtil.checkedList(lista, Palavra.class));

        System.out.println("lucas = "+lista2.contains("lucas"));
        System.out.println("deseja = "+lista2.contains("deseja"));
        System.out.println("ligar = "+lista2.contains("ligar"));
    }
}
