
package br.com.mailanalyzer.analise2;

import br.com.mailanalyzer.log.L;
import br.com.mailanalyzer.main.Config;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 19-09-2011
 *
 */
public class Elemento {
    public static final String TAG = "Elemento";
    private String palavra;
    private int peso = Peso.NORMAL, id;
    private String[] sinonimos;

    public int getRelevanciaTotal(){
        return peso;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Elemento other = (Elemento) obj;
        if (this.peso != other.peso) {
            return false;
        }
        if ((this.palavra == null) ? (other.palavra != null) : !this.palavra.equals(other.palavra)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + this.peso;
        hash = 17 * hash + (this.palavra != null ? this.palavra.hashCode() : 0);
        return hash;
    }

    /**
     * @return the relevancia
     */
    public int getRelevancia(Composicao c) {
        this.peso = Peso.GET_PESO(palavra, peso);
        int relevancia = 0;
        for (Elemento e : c.getElementos()) {
            boolean encontrado = false;
            int lev = Levenshtein.getLevenshteinDistance(palavra, e.getPalavra());
            if(peso<0 && lev == 0){
               relevancia += peso; //Trata eliminatorias
               encontrado = true;
            }else if(peso-lev>=0 && lev <= Levenshtein.getPorcentagem(30, palavra.length()<e.getPalavra().length()?palavra:e.getPalavra())){
               relevancia += (peso - lev);
               encontrado = true;
            }
            if (!encontrado && Raiz.PROCURAR_EM_SINONIMOS) {
                for (String s : sinonimos) {
                    if (e.getPalavra().equals(s)) {
                        lev = Levenshtein.getLevenshteinDistance(palavra, s);
                        if(peso<0 && lev==0){
                            relevancia += peso;
                            break;
                        }else if(peso-lev>=0){
                            relevancia += peso-lev;
                            break;
                        }
                    }
                }
            }
        }
        return relevancia;
    }

    public int getRelevancia(String palavra){
        this.peso = Peso.GET_PESO(this.palavra, peso);
        int temp = 0;
        boolean encontrado = false;
        int lev = Levenshtein.getLevenshteinDistance(palavra, this.palavra);
        if(lev==0 && peso <0){
            temp += peso;
        }else if (peso-lev>=0 && lev <= Levenshtein.getPorcentagem(30, (palavra.length()< this.palavra.length())?palavra:this.palavra)){
            temp += peso-lev;
        }
        encontrado = temp!=0;

        if(!encontrado && Raiz.PROCURAR_EM_SINONIMOS){
            for(String s : sinonimos){
                lev = Levenshtein.getLevenshteinDistance(palavra, s);
                if(lev==0 && peso <0){
                    temp += peso;
                    break;
                }else if (peso-lev>=0 && lev <= Levenshtein.getPorcentagem(30, (palavra.length()< s.length())?palavra:s)){
                    temp += peso-lev;
                    break;
                }
            }
        }
        return temp;
    }

    /**
     * @param palavra the palavra to set
     */
    public void setPalavra(String palavra, boolean srcSinonimos) {
        this.palavra = palavra;
        if(!srcSinonimos){
            sinonimos = new String[]{};
            return;
        }
        if(buscarSinonimos()){
            this.sinonimos = Sinonimo.getInstancia().GET_SINONIMOS("#"+palavra+"#");
        }else{
            sinonimos = new String[]{};
        }
    }

    private boolean buscarSinonimos(){
        if(palavra.length()<2)return false;
        return true;
    }

    /**
     * @return the palavra
     */
    public String getPalavra() {
        return palavra;
    }

    /**
     * @param palavra the palavra to set
     */
    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    /**
     * @return the peso
     */
    public int getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(int peso) {
        this.peso = peso;
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
     * @return the sinonimos
     */
    public String[] getSinonimos() {
        return sinonimos;
    }

    /**
     * @param sinonimos the sinonimos to set
     */
    public void setSinonimos(String[] sinonimos) {
        this.sinonimos = sinonimos;
    }

}
