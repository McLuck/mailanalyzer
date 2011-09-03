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
    public static String input = "Não consigo acessar o portal do aluno";
    public static String input2 = "documento levar fazer matricula?";
    public static String input3 = "Gostaria de saber como faço minha grade horária.";
    public static String input4 = "Quando posso retirar meu histórico escolar?";

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
        r3.aprenderNovaComposicao(null, "eliminar matéria", Peso.NORMAL, true);
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
            System.out.println("Assunto não encontrado.");
        }
        System.out.println("------");
    }
}
