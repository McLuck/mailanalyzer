
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
        //redefine o Peso antes de iniciar comparações.
        //Despresa pesos de palavras irrelevantes.
        this.peso = Peso.GET_PESO(palavra, peso);

        if(Config.isNivelLogMaximo()){
            L.d(TAG, this, "Procurando em composição. ID: "+id+" - palavra: "+palavra);
        }
        
        int temp = 0;
        for (Elemento e : c.getElementos()) {
            //procura na palavra
            if (e.getPalavra().trim().equals(palavra.trim())) {
                temp += peso;
            }
            //procura nos sinonimos
            if (Raiz.PROCURAR_EM_SINONIMOS) {
                for (String s : sinonimos) {
                    if (e.getPalavra().trim().equals(s.trim())) {
                        temp += peso;
                    }
                }
            }
        }
        //se nao encontrar nenhuma palavra, nao retorna nada
        return temp;
    }

    public int getRelevancia(String palavra){
        this.peso = Peso.GET_PESO(this.palavra, peso);
        int temp = 0;
        if (palavra.trim().equals(this.palavra.trim())) {
                temp += peso;
        }
        if(Raiz.PROCURAR_EM_SINONIMOS){
            for(String s : sinonimos){
                temp += palavra.trim().equals(s.trim())?peso:0;
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
