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

        tester = "O portal do aluno n�o est� funcionando";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "O prof n�o conseguiu postar a nota no portal do aluno";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "Como funciona o portal de notas?";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "Como fa�o para ver as notas no site?";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "O prof n�o conseguiu postar o conte�do no portal do aluno";
        print(tester, getConhecimentos(), getPesos(tester));







        //Teste para Matricula (ASSUNTO 2 em Raiz 2)
        tester = "Qual a taxa a ser paga na matricula?";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "Quais s�o os requisitos para realizar a matricula?";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "Em que hor�rio posso fazer minha rematricula?";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "Que documentos tem que levar para fazer matricula?";
        print(tester, getConhecimentos(), getPesos(tester));







        //Teste para: Gostaria de saber como fa�o minha grade hor�ria.
        tester = "Solicito saber como criar minha grade hor�ria";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "Como � feita a cria��o da minha grade hor�ria";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "Quais requisitos para criar a grade hor�ria";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "Como monto minha grade hor�ria";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "Como fa�o para alterar minha grade hor�ria";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "Quero saber como fa�o para mudar minha grade hor�ria";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "Quando posso fazer minha grade hor�ria";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "At� que data pode ser feita a grade hor�ria";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "At� que data pode ser alterada a grade hor�ria";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "Tem limite de mat�ria na grade hor�ria";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "Posso eliminar mat�ria na grade hor�ria";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "Gostaria de saber quantas mat�rias tem na grade hor�ria";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "Gostaria de saber quais mat�rias tem na minha grade hor�ria";
        print(tester, getConhecimentos(), getPesos(tester));








        //Quando posso retirar meu hist�rico escolar?
        tester = "Qual � a taxa a ser paga para retirada do hist�rico escolar?";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "Em quanto tempo fica pronto o meu hist�rico escolar?";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "Meu hist�rico escolar saiu com dados incorretos";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "A nota do enade vai para o hist�rico escolar?";
        print(tester, getConhecimentos(), getPesos(tester));

        tester = "� poss�vel tirar segunda via do hist�rico escolar?";
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
            System.out.println("Assunto n�o encontrado.");
        }
        System.out.println("------");
    }
}
