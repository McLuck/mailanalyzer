package br.com.mailanalyzer.analise;

import br.com.mailanalyzer.log.L;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 27-08-2011
 *
 */
public class Elemento implements Item {
    public static final String TAG = "Elemento";
    private int relevancia = Peso.NORMAL;
    private String palavra;
    private String[] sinonimos;

    private Composicao itemPai;

    public int getRelevanciaTotal(){
        return relevancia;
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
        if (this.relevancia != other.relevancia) {
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
        hash = 17 * hash + this.relevancia;
        hash = 17 * hash + (this.palavra != null ? this.palavra.hashCode() : 0);
        return hash;
    }

    /**
     * @return the palavra
     */
    public String getPalavra() {
        return palavra;
    }

    /*/**
     * @param palavra the palavra to set
     * /
    public void setPalavra(String palavra) {
        this.palavra = palavra;
        if(buscarSinonimos()){
            this.sinonimos = Sinonimo.getInstancia().GET_SINONIMOS("#"+palavra+"#");
        }else{
            sinonimos = new String[]{};
        }
    }*/

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

    /**
     * @return the relevancia
     */
    public int getRelevancia(Composicao c) {
        //redefine o Peso antes de iniciar comparações.
        //Despresa pesos de palavras irrelevantes.
        this.relevancia = Peso.GET_PESO(palavra, relevancia);

        L.d(TAG, "Procurando em elemento. ID:" + palavra);
        int temp = 0;
        for (Item i : c.getItens()) {
            Elemento e = (Elemento) i;
            //procura na palavra
            if (e.getPalavra().trim().equals(palavra.trim())) {
                temp += relevancia;
            }
            //procura nos sinonimos
            if (Raiz.PROCURAR_EM_SINONIMOS) {
                for (String s : sinonimos) {
                    if (e.getPalavra().trim().equals(s.trim())) {
                        temp += relevancia;
                    }
                }
            }
        }
        //se nao encontrar nenhuma palavra, nao retorna nada
        return temp;
    }

    /**
     * @param relevancia the relevancia to set
     */
    public void setRelevancia(int relevancia) {
        this.relevancia = relevancia;
    }

    /**
     * @return the itemPai
     */
    public Composicao getItemPai() {
        return itemPai;
    }

    /**
     * @param itemPai the itemPai to set
     */
    public void setItemPai(Item itemPai) {
        this.itemPai = (Composicao) itemPai;
    }
}
