package br.com.mailanalyzer.analise;

/**
 *
 * @author Lucas Israel
 */
public class Teste {
    public static Raiz r = new Raiz();
    public static Raiz r2 = new Raiz();
    public static Raiz r3 = new Raiz();
    public static Raiz r4 = new Raiz();
    public static String input = "N�o consigo acessar o portal do aluno";
    public static String input2 = "documento levar fazer matricula?";
    public static String input3 = "Gostaria de saber como fa�o minha grade hor�ria.";
    public static String input4 = "Quando posso retirar meu hist�rico escolar?";

    public static void aprenderTudo(){
        Raiz.PROCURAR_EM_SINONIMOS = false;
        r.aprender(input);
        r.agregarPalavras("portal", Peso.RELEVANTE);
        r.aprenderNovaComposicao(null, "dados de acesso", Peso.NORMAL, true);
        r.aprenderNovaComposicao(null, "historico escolar", Peso.INCORRETO, true);


        //Matricula
        r2.aprender(input2);
        r2.agregarPalavras("rematricula documentos necessarios", Peso.RELEVANTE);


        //Grade horaria
        r3.aprender(input3);
        r3.aprenderNovaComposicao("Gostaria de saber", "Quero saber", Peso.NORMAL, true);
        r3.aprenderNovaComposicao("Gostaria de saber", "Posso saber", Peso.NORMAL, true);
        r3.aprenderNovaComposicao(null, "eliminar mat�ria", Peso.NORMAL, true);
        r3.aprenderContextoMandatorio("grade horaria");
        r3.aprenderContextoMandatorio("grade de horario");

        //Historico
        r4.aprender(input4);
        r4.aprenderContextoMandatorio("historico escolar");
        //r4.aprenderNovaComposicao(input, comparador, peso, true);
    }

    public static int posicaoComMaiorPossibilidade(int[] results){
        int maior = 0;
        int posicao = -1;
        for(int i=0;i<results.length;i++){
            if(results[i]>maior){
                posicao = i;
            }
        }
        return posicao;
    }

    public static String[] getConhecimentos(){
        return new String[]{
            input,
            input2,
            input3,
            input4
        };
    }

    public static int[] getPesos(String comparador){
        //Monta resultados
        int[] results = {
            r.getPeso(comparador),
            r2.getPeso(comparador),
            r3.getPeso(comparador),
            r4.getPeso(comparador)
        };
        return results;
    }

    public static void main(String... args) {
        aprenderTudo();
        

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

    public static void print(String input, int result) {
        System.out.println();
        System.out.println("------");
        System.out.println("Input: " + input);
        System.out.println("Peso: " + result);
        System.out.println("------");
    }
    public static void print(String input,String conhecimentos[], int[] results) {
        System.out.println();
        System.out.println("------");

        System.out.println("Input: " + input);
        /*for(int i = 0;i<results.length;i++){
            System.out.println("--");
            System.out.println("ASSUNTO: "+conhecimentos[i]);
            System.out.println("Peso: " + results[i]);
        }*/
        int provavel = posicaoComMaiorPossibilidade(results);
        if(provavel>=0){
            System.out.println("Assunto mais provavel: "+conhecimentos[provavel]);
        }else{
            System.out.println("Assunto n�o encontrado.");
        }
        System.out.println("------");
    }
}
