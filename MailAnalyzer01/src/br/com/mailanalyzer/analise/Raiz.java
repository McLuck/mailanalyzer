package br.com.mailanalyzer.analise;

import br.com.mailanalyzer.domain.DomainObject;
import br.com.mailanalyzer.domain.Subject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 27-08-2011
 *
 * REFATORADO: dia 17 de setembro.
 * MOTIVO: acrescentar o assunto sendo tratado, adicionar composicoes comuns
 *
 */

public class Raiz{

    public String getName(){
        StringBuffer sb = new StringBuffer();
        sb.append(TAG);
        sb.append(this.getClass().getSimpleName());
        return sb.toString();
    }

    
    private Subject assunto;
    public static final String TAG = "Raiz";
    public static boolean PROCURAR_EM_SINONIMOS = true;
    private String textoOriginal;
    private Composicao base;
    private Composicao agregacoes;
    private Composicao mandatorios;
    private List<Composicao> composicoes;

    protected void setMandatorios(Composicao mand){
        this.mandatorios = mand;
    }

    protected Composicao getMandatorios(){
        return this.mandatorios;
    }

    protected void setTextoOriginal(String texto){
        this.textoOriginal = texto;
    }
    protected void setBase(Composicao base){
        this.base = base;
    }
    protected void setAgregacoes(Composicao agreg){
        this.agregacoes = agreg;
    }

    /**
     * Add composicao comum nesta raiz de assunto
     * @param composicaoComum Um objeto nao nulo de composicao
     */
    public void append(Composicao composicaoComum){
        composicoes.add(composicaoComum);
    }

    public void aprender(String assunto) {
        assunto = UtilsString.NORMALIZAR(assunto);
        composicoes = new ArrayList<Composicao>();
        agregacoes = new Composicao();
        agregacoes.setElementoFim(-2);
        agregacoes.setElementoInicio(-2);
        mandatorios = new Composicao();
        mandatorios.setElementoFim(-3);
        mandatorios.setElementoInicio(-3);
        textoOriginal = assunto;
        base = new Composicao();
        base.setRelevancia(Peso.MUITO_RELEVANTE);
        String[] vet = assunto.split(" ");
        for (String s : vet) {
            Elemento e = new Elemento();
            //e.setId(getIdElemento());
            e.setPalavra(s, true);
            //e.setSinonimos(Sinonimo.getInstancia().GET_SINONIMOS(s));
            base.getItens().add((Item) e);
        }
    }

    public void aprenderContextoMandatorio(String palavra) {
        palavra = UtilsString.NORMALIZAR(palavra);
        String[] v = palavra.split(" ");
        Composicao c = new Composicao();
        for (String s : v) {
            Elemento e = new Elemento();
            e.setRelevancia(Peso.MUITO_RELEVANTE * 2);
            e.setPalavra(s, true);
            c.getItens().add(e);
        }
        mandatorios.getItens().add(c);
    }

    public int getRelevancia(String comparador) {
        int pesoCorrente = 0;
        Composicao procurado = StringToComposicao(comparador, Peso.MUITO_RELEVANTE);

        //Procura palavras mandatorias, caso nao encontre, responde com 0;
        boolean encMandatorio = false;
        for (Item i : mandatorios.getItens()) {
            Composicao cp = (Composicao) i;
            int c = cp.getPesoTotal(cp, procurado);
            encMandatorio = c>=cp.getRelevanciaTotal();
            if(encMandatorio){
                break;
            }
        }
        if(!encMandatorio && mandatorios.getItens().size()>0){
            return 0;
        }

        //Testa a base principal para pegar o peso inicial
        pesoCorrente = base.getPesoTotal(base, procurado);

        //Pega o peso das palavras agregadas
        int pesoAgregados = base.getPesoTotal(agregacoes, procurado);

        int temp = 0, temp2 = 0;

        for (Composicao c : composicoes) {

            //posicoes -1 indicam que os elementos são sequenciais
            //Procurar pelas sequencias de elementos nas composicoes e somar seus pesos.
            //Só serão consideradas, sequencias neste if.
            if (c.getElementoFim() == -1
                    && c.getElementoInicio() == -1) {

                boolean procurar = true;
                Map<Elemento, Boolean> encontrados = new HashMap<Elemento, Boolean>();
                for (Item i : procurado.getItens()) {
                    Elemento e = (Elemento) i;
                    for (Item ii : c.getItens()) {
                        if (((Elemento) ii).getPalavra().equals(e.getPalavra())) {
                            encontrados.put((Elemento) ii, true);
                        }
                    }
                }

                for (Item i : c.getItens()) {
                    if (encontrados.get((Elemento) i) == null) {
                        procurar = false;
                    }
                }
                if (procurar) {
                    for (Item i : procurado.getItens()) {
                        Elemento e = (Elemento) i;
                        for (Item ii : c.getItens()) {
                            if (((Elemento) ii).getPalavra().equals(e.getPalavra())) {
                                temp2 += (c.getPeso() + ((Elemento) ii).getRelevancia(c));
                            }
                        }
                    }
                }
                continue;


            } else if (c.getElementoFim() == 0
                    && c.getElementoInicio() == 0) {
                //Para composições que não possuem trechos na base de conhecimento.
                temp2 += c.getPesoTotal(c, procurado);
            }

            if (c.getElementoInicio() == 0 && c.getElementoFim() == base.getItens().size() - 1) {
                int tmp = c.getPesoTotal(c, procurado);
                if (tmp > pesoCorrente) {
                    pesoCorrente = tmp;
                }
                continue;
            }
            Composicao ctempIni = new Composicao();
            for (int i = 0; i < c.getElementoInicio(); i++) {
                Elemento e = (Elemento) base.getItens().get(i);
                ctempIni.getItens().add((Item) e);
            }
            for (int i = c.getElementoFim(); i < base.getItens().size(); i++) {
                Elemento e = (Elemento) base.getItens().get(i);
                ctempIni.getItens().add((Item) e);
            }
            //Procura o elemento isolado, para nao perder o peso individual da composicao
            int tmp = c.getPesoTotal(c, procurado);

            //Bloqueio de assunto.
            //Trata o termo que com certeza não pertence ao assunto.
            //Qualquer sentenca com Peso.INCORRETO sera eliminada aqui.
            if (tmp < 0) {
                return tmp;
            }

            temp = ctempIni.getPesoTotal(ctempIni, procurado);

            //Bloqueio de assunto
            if (temp < 0) {
                return temp;
            }

            //Armazena apenas o maior Peso
            if (temp > pesoCorrente) {
                pesoCorrente = temp;
            }
        }
        pesoCorrente += temp2 + pesoAgregados;
        return pesoCorrente;
    }

    public int[] getIndexTrecho(String trecho, int comeco) {//Preso
        if (!textoOriginal.contains(trecho)) {
            return null;
        }
        String[] v = trecho.split(" ");

        int inicio = -1, fim = -1;
        for (int i = comeco; i < base.getItens().size(); i++) {
            Elemento e = (Elemento) base.getItens().get(i);
            if (inicio == -1) {
                if (e.getPalavra().equals(v[0])) {
                    if (base.getItens().size() >= (i + v.length - 1)) {
                        Elemento e2 = ((Elemento) base.getItens().get(i + (v.length - 1)));
                        if (e2.getPalavra().equals(v[v.length - 1])) {
                            inicio = i;
                            fim = i + v.length - 1;
                            break;
                        }
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }
            }
        }
        if (inicio >= 0 && fim >= inicio) {
            return new int[]{inicio, fim};
        }
        return null;
    }

    public Composicao StringToComposicao(String texto, int peso) {
        Composicao c = new Composicao();
        texto = UtilsString.NORMALIZAR(texto);
        String[] v = texto.split(" ");

        for (String s : v) {
            Elemento e = new Elemento();
            //e.setId(getIdElemento());
            e.setPalavra(s, false);
            //e.setSinonimos(new String[]{});
            e.setRelevancia(peso);
            c.getItens().add((Item) e);
        }

        return c;
    }

    /**
     * Adiciona um novo aprendizado.
     * Uma composição de itens serve para avaliar exceções da mensagem original.
     * Estas exceções são pesadas junto com o texto original.
     * Muito útil para abranger novos termos e trechos no contexto tal como
     * remover a possibilidade de classificação com algumas palavras.
     * Composição de termos é um conjunto de termos pesados individualmente.
     * Combinações de arranjos são feitas com os elementos para que se somem entre si.
     * Isto garante aumenta a precisão da análise.
     *
     * @param trecho É um trecho da mensagem original que se deseja conglomerar a nova composição de palavras
     * @param novaComposicao A nova composição de palavras
     * @param peso O peso da nova avaliação para a nova composição. Mais detalhes na classe Peso
     * @param sequencia Indicar se os termos da nova composição devem ser aplicados individualmentes. Neste caso, a nova composição devera
     * ter a mesma quantidade de palavras que o trecho. Isto irá possibilitar
     * a nova composição sequencialmente.
     * Use <b>###</b> como separador padrão.<br>
     * Exemplo:<br>
     * <b>Texto Original:</b> Quero saber do meu novo crachá. <br>
     * <b>Peso desta nova composição:</b> <i>Peso.RELEVANTE</i> <br>
     * <b>Trecho a ser substituido:</b> "saber do###novo###crachá"<br>
     * <b>Nova composição:</b> " ### ### "<br>
     * <b>Como será analisado:</b> "Quero meu crachá"<br><br>
     *
     * <b>OBSERVAÇÃO:
     * Caso o parâmetro sequencia for false, então mesmo que tenha os símbolos, o trecho será reconhecido
     * como um único trecho.
     * </b>
     */
    public void aprenderNovaComposicao(String trecho, String novaComposicao, int peso, boolean sequencia) {
        if (!sequencia) {
            String v1[] = trecho.split("###");
            String v2[] = novaComposicao.split("###");
            Composicao c = new Composicao();
            //c.setId(getIdComposicao());
            c.setElementoInicio(-1);
            c.setElementoFim(-1);
            c.setRelevancia(peso);
            for (int i = 0; i < v1.length; i++) {
                //aprenderNovaComposicao(v1[i], v2[i], peso);
                Elemento e = new Elemento();
                //e.setId(getIdElemento());
                e.setPalavra(v2[i], true);
                e.setRelevancia(peso);
                c.getItens().add((Item) e);
            }
            composicoes.add(c);
        } else {
            aprenderNovaComposicao(trecho, novaComposicao, peso);
        }

    }

    private void aprenderNovaComposicao(String trecho, String novaComposicao, int peso) {
        if (trecho != null) {
            trecho = UtilsString.NORMALIZAR(trecho);
            novaComposicao = UtilsString.NORMALIZAR(novaComposicao);
            if (novaComposicao.equals("")) {
                novaComposicao = ".";
            }
            int pos[] = getIndexTrecho(trecho, 0);
            String[] v = novaComposicao.split(" ");

            if (pos == null) {
                return;
            }


            //trabalha os elementos
            List<Item> elementosNovos = new ArrayList<Item>();
            for (String s : v) {
                Elemento e = new Elemento();
                e.setRelevancia(Peso.NORMAL);
                e.setPalavra(s, true);
                //e.setId(getIdElemento());
                elementosNovos.add((Item) e);
            }


            //Varre todo o texto buscando o trecho pra adicionar a nova composicao
            while (pos != null) {
                Composicao c = new Composicao();
                //c.setId(getIdComposicao());
                c.setRelevancia(peso);
                c.setElementoInicio(pos[0]);
                c.setElementoFim(pos[1]);
                c.setItens(elementosNovos);

                composicoes.add(c);

                //pega nova posicao contendo trecho
                pos = getIndexTrecho(trecho, pos[0] + 1);
            }
        } else {
            novaComposicao = UtilsString.NORMALIZAR(novaComposicao);
            String[] v = novaComposicao.split(" ");

            //trabalha os elementos
            Composicao c = new Composicao();

            for (String s : v) {
                Elemento e = new Elemento();
                e.setRelevancia(peso);
                e.setPalavra(s, true);
                //e.setId(getIdElemento());
                c.getItens().add(e);
            }
            //composicoes.add(c);
            ManagementAnalyzer.addComposicaoComum(c);
        }
    }
    

    /**
     * Adiciona novas palavras para este assunto.<br>
     * As palavras adicionadas serão restringidas a este assunto.
     * @param palavras Palavras que deseja ser adicionadas.
     * @param peso Peso que cada palavra terá.
     */
    public void agregarPalavras(String palavras, int peso) {
        palavras = UtilsString.NORMALIZAR(palavras);
        String[] v = palavras.split(" ");
        for (String s : v) {
            if (s != null && !s.isEmpty()) {
                Elemento e = new Elemento();
                e.setPalavra(s, true);
                e.setRelevancia(peso);
                agregacoes.getItens().add(e);
            }
        }
    }

    /**
     * @return the assunto
     */
    public Subject getAssunto() {
        return assunto;
    }

    /**
     * @param assunto the assunto to set
     */
    public void setAssunto(Subject assunto) {
        this.assunto = assunto;
    }
}
