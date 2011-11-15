/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.analise2;

import br.com.mailanalyzer.analise2.adapter.RaizAdapter;
import br.com.mailanalyzer.dao.RaizDAO;
import br.com.mailanalyzer.domain.RaizDomain;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author McLuck
 */
public class Teste2 {
    public static List<Raiz> matrizes;
    public static void load(){
        RaizDAO rdao = new RaizDAO();
        List<RaizDomain> rs = rdao.obterTodos();
        matrizes = new ArrayList<Raiz>();
        for(RaizDomain rd : rs){
            RaizAdapter rAdapt = new RaizAdapter(rd);
            matrizes.add(rAdapt.getRaiz());
        }
        rdao.close();
    }
    public static List<String> cms;
    public static List<Integer> pesos;
    public static List<String> getConhecimentos() {
        cms = new ArrayList<String>();
        for(Raiz r : matrizes){
            cms.add(r.getAssunto().getText());
        }
        return (cms);
    }

    public static int posicaoComMaiorPossibilidade(List<Integer> results) {
        int maior = 0;
        int posicao = -1;
        for (int i = 0; i < results.size(); i++) {
            if (results.get(i) > maior) {
                posicao = i;
            }
        }
        return posicao;
    }

    public static List<Integer> getPesos(String comparador) {
        //Monta resultados
        pesos = new ArrayList<Integer>();
        for(Raiz r : matrizes){
            pesos.add(r.getRelevancia(comparador));
        }
        return (pesos);
    }

    public static void main(String...arg){
        load();

        String tester = "Solicito meus dados de acesso ao portal do aluno";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "Esqueci minha senha de acesso ao portal";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "O portal do aluno não está funcionando";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "O prof não conseguiu postar a nota no portal do aluno";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "Como funciona o portal de notas?";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "Como faço para ver as notas no site?";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "O prof não conseguiu postar o conteúdo no portal do aluno";
        print(tester, getConhecimentos(), getPesos(tester));







        //Teste para Matricula (ASSUNTO 2 em Raiz 2)
        tester = "Qual a taxa a ser paga na matricula?";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "Quais são os requisitos para realizar a matricula?";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "Em que horário posso fazer minha rematricula?";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "Que documentos tem que levar para fazer matricula?";
        print(tester, getConhecimentos(), getPesos(tester));







        //Teste para: Gostaria de saber como faço minha grade horária.
        tester = "Solicito saber como criar minha grade horária";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "Como é feita a criação da minha grade horária";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "Quais requisitos para criar a grade horária";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "Como monto minha grade horária";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "Como faço para alterar minha grade horária";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "Quero saber como faço para mudar minha grade horária";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "Quando posso fazer minha grade horária";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "Até que data pode ser feita a grade horária";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "Até que data pode ser alterada a grade horária";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "Tem limite de matéria na grade horária";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "Posso eliminar matéria na grade horária";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "Gostaria de saber quantas matérias tem na grade horária";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "Gostaria de saber quais matérias tem na minha grade horária";
        print(tester, getConhecimentos(), getPesos(tester));








        //Quando posso retirar meu histórico escolar?
        tester = "Qual é a taxa a ser paga para retirada do histórico escolar?";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "Em quanto tempo fica pronto o meu histórico escolar?";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "Meu histórico escolar saiu com dados incorretos";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "A nota do enade vai para o histórico escolar?";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "É possível tirar segunda via do histórico escolar?";
        print(tester, getConhecimentos(), getPesos(tester));



    }

    public static void print(String input, List<String> conhecimentos, List<Integer> results) {
        System.out.println();
        System.out.println("------");

        System.out.println("Input: " + input);
        /*for(int i = 0;i<results.length;i++){
        System.out.println("--");
        System.out.println("ASSUNTO: "+conhecimentos[i]);
        System.out.println("Peso: " + results[i]);
        }*/
        int provavel = posicaoComMaiorPossibilidade(results);
        if (provavel >= 0) {
            System.out.println("Assunto mais provavel: " + conhecimentos.get(provavel));
        } else {
            System.out.println("Assunto não encontrado.");
        }
        System.out.println("------");
    }
}
