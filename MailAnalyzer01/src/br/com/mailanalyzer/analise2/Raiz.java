package br.com.mailanalyzer.analise2;


import br.com.mailanalyzer.domain.Subject;
import java.util.ArrayList;
import java.util.List;

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
 * REFATORADO: dia 24 de setembro.
 * MOTIVO: Alteracao da estrutura
 */
public class Raiz {

    public static boolean PROCURAR_EM_SINONIMOS = true;
    public static boolean CARREGAR_SINONIMOS = false;
    public static final String TAG = "Raiz";
    private int id;
    private Subject assunto;
    private String baseConhecimento;
    private Composicao base;
    private List<Composicao> agregacoes;
    private List<Composicao> mandatorios;
    private List<Composicao> eliminatorios;
    public void setBaseConhecimento(String base){
        this.baseConhecimento = base;
    }
    /**
     * Add composicao nesta raiz de assunto
     * @param composicao Um objeto nao nulo de composicao
     */
    public void appendComposicao(Composicao composicao) {
        if (composicao.getTipo() == Composicao.TIPO.MANDATORIO) {
            this.mandatorios.add(composicao);
        } else if (composicao.getTipo() == Composicao.TIPO.AGREGACAO) {
            this.agregacoes.add(composicao);
        } else if (composicao.getTipo() == Composicao.TIPO.ELIMINATORIO) {
            this.eliminatorios.add(composicao);
        }
    }

    public void aprender(String textoAssunto) {
        textoAssunto = UtilsString.NORMALIZAR(textoAssunto);
        baseConhecimento = textoAssunto;
        setAgregacoes(new ArrayList<Composicao>());
        setMandatorios(new ArrayList<Composicao>());
        setEliminatorios(new ArrayList<Composicao>());
        base = new Composicao();
        base.setPeso(Peso.MUITO_RELEVANTE);
        base.setTipo(Composicao.TIPO.BASE);
        base.setTextoOriginal(textoAssunto);
        String[] vet = textoAssunto.split(" ");
        for (String s : vet) {
            Elemento e = new Elemento();
            e.setPalavra(s, CARREGAR_SINONIMOS);
            base.getElementos().add(e);
        }
    }

    public void aprender(Subject assunto) {
        this.assunto = assunto;
        String textoAssunto = assunto.getText();
        baseConhecimento = textoAssunto;
        textoAssunto = UtilsString.NORMALIZAR(textoAssunto);
        setAgregacoes(new ArrayList<Composicao>());
        setMandatorios(new ArrayList<Composicao>());
        setEliminatorios(new ArrayList<Composicao>());
        base = new Composicao();
        base.setPeso(Peso.MUITO_RELEVANTE);
        base.setTipo(Composicao.TIPO.BASE);
        base.setTextoOriginal(textoAssunto);
        String[] vet = textoAssunto.split(" ");
        for (String s : vet) {
            Elemento e = new Elemento();
            e.setPalavra(s, CARREGAR_SINONIMOS);
            base.getElementos().add(e);
        }
    }

    /**
     * Contexto mandatorio. Sao palavras ou conjunto de palavras que, sem eles em uma mensagem,
     * a mensagem sera descartada. Sao palavras obrigatorias na mensagens.
     * @param texto novo texto para ser agregado na analise
     * @param sequencial se estas palavras devem ser levadas em conta nesta ordem ou podem ser espalhadas
     * na mensagem, no momento da analise
     */
    public void aprenderContextoMandatorio(String texto, boolean sequencial) {
        texto = UtilsString.NORMALIZAR(texto);
        String[] v = texto.split(" ");
        Composicao c = new Composicao();
        c.setTipo(Composicao.TIPO.MANDATORIO);
        c.setSequencial(sequencial);
        c.setPeso(Peso.RELEVANTE);
        c.setTextoOriginal(texto);
        for (String s : v) {
            Elemento e = new Elemento();
            e.setPeso(Peso.MUITO_RELEVANTE * 2);
            e.setPalavra(s, CARREGAR_SINONIMOS);
            c.getElementos().add(e);
        }
        mandatorios.add(c);
    }

    /**
     * Adiciona um novo conjunto de palavras para ser considerado na analise
     * @param texto texto novo para ser agregado na analise
     * @param peso Peso deste conjunto de palavras
     * @param sequencial se estas palavras devem ser levadas em conta nesta ordem ou podem ser espalhadas
     * na mensagem, no momento da analise
     */
    public void aprenderNovaAgregacao(String texto, int peso, boolean sequencial) {
        texto = UtilsString.NORMALIZAR(texto);
        String[] v = texto.split(" ");
        Composicao c = new Composicao();
        c.setSequencial(sequencial);
        c.setPeso(peso);
        c.setTextoOriginal(texto);
        for (String s : v) {
            Elemento e = new Elemento();
            e.setPeso(peso);
            e.setPalavra(s, CARREGAR_SINONIMOS);
            c.getElementos().add(e);
        }
        if (peso == Peso.INCORRETO) {
            c.setTipo(Composicao.TIPO.ELIMINATORIO);
            eliminatorios.add(c);
        } else {
            c.setTipo(Composicao.TIPO.AGREGACAO);
            agregacoes.add(c);
        }
    }

    /**
     * Adiciona um novo conjunto de palavras para ser considerado na analise como conjunto eliminatorios.
     * Significa que se forem encontrados, a analise para porque foi eliminada por este contexto de palavras
     * @param texto texto novo para ser analisado
     * @param peso Peso deste conjunto de palavras
     * @param sequencial se estas palavras devem ser levadas em conta nesta ordem ou podem ser espalhadas
     * na mensagem, no momento da analise
     */
    public void aprenderNovaComposicaoEliminatoria(String texto, boolean sequencial) {
        texto = UtilsString.NORMALIZAR(texto);
        String[] v = texto.split(" ");
        Composicao c = new Composicao();
        c.setSequencial(sequencial);
        c.setTipo(Composicao.TIPO.ELIMINATORIO);
        c.setPeso(Peso.INCORRETO);
        c.setTextoOriginal(texto);
        for (String s : v) {
            Elemento e = new Elemento();
            e.setPeso(Peso.INCORRETO);
            e.setPalavra(s, CARREGAR_SINONIMOS);
            c.getElementos().add(e);
        }
        eliminatorios.add(c);
    }

    public int getRelevancia(String comparador) {
        Composicao procurado = StringToComposicao(comparador, Peso.MUITO_RELEVANTE);

        //Procura palavras mandatorias, caso nao encontre, responde com 0;
        boolean verificado = false;
        for (Composicao cp : mandatorios) {
            int c = cp.getRelevancia(cp, procurado);
            verificado = c >= cp.getRelevanciaTotal();
            if (verificado) {
                break;
            }
        }
        if (!verificado && mandatorios.size() > 0) {
            return 0;
        }



        //Testa os eliminatorios
        verificado = false;
        for (Composicao cp : eliminatorios) {
            int c = cp.getRelevancia(cp, procurado);
            //Caso seja menor que zero, entao existe ponto de bloqueio
            verificado = c < 0;
            if (verificado) {
                break;
            }
        }
        if (verificado && eliminatorios.size() > 0) {
            return 0;
        }

        //Testa a base principal para pegar o peso inicial
        int pesoCorrente = base.getRelevancia(base, procurado);

        //Soma agregacoes
        for (Composicao cp : agregacoes) {
            pesoCorrente += cp.getRelevancia(cp, procurado);
        }

        return pesoCorrente;
    }

    private Composicao StringToComposicao(String texto, int peso) {
        Composicao c = new Composicao();
        texto = UtilsString.NORMALIZAR(texto);
        String[] v = texto.split(" ");
        for (String s : v) {
            Elemento e = new Elemento();
            e.setPalavra(s, false);
            e.setPeso(peso);
            c.getElementos().add(e);
        }

        return c;
    }

    private int[] getIndexTrecho(String trecho, int comeco) {
        if (!assunto.getText().contains(trecho)) {
            return null;
        }
        String[] v = trecho.split(" ");

        int inicio = -1, fim = -1;
        for (int i = comeco; i < base.getElementos().size(); i++) {
            Elemento e = (Elemento) base.getElementos().get(i);
            if (inicio == -1) {
                if (e.getPalavra().equals(v[0])) {
                    if (base.getElementos().size() >= (i + v.length - 1)) {
                        Elemento e2 = ((Elemento) base.getElementos().get(i + (v.length - 1)));
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

    public String getName() {
        StringBuffer sb = new StringBuffer();
        sb.append(TAG);
        sb.append(this.getClass().getSimpleName());
        sb.append(id);
        sb.append(assunto != null ? assunto.getName() : "assuntoNulo");
        return sb.toString();
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
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

    /**
     * @return the base
     */
    public Composicao getBase() {
        return base;
    }

    /**
     * @param base the base to set
     */
    public void setBase(Composicao base) {
        this.base = base;
    }

    /**
     * @return the agregacoes
     */
    public List<Composicao> getAgregacoes() {
        return agregacoes;
    }

    /**
     * @param agregacoes the agregacoes to set
     */
    public void setAgregacoes(List<Composicao> agregacoes) {
        this.agregacoes = agregacoes;
    }

    /**
     * @return the mandatorios
     */
    public List<Composicao> getMandatorios() {
        return mandatorios;
    }

    /**
     * @param mandatorios the mandatorios to set
     */
    public void setMandatorios(List<Composicao> mandatorios) {
        this.mandatorios = mandatorios;
    }

    /**
     * @return the eliminatorios
     */
    public List<Composicao> getEliminatorios() {
        return eliminatorios;
    }

    /**
     * @param eliminatorios the eliminatorios to set
     */
    public void setEliminatorios(List<Composicao> eliminatorios) {
        this.eliminatorios = eliminatorios;
    }
}
