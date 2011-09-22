package br.com.mailanalyzer.analise;

import br.com.mailanalyzer.domain.DomainObject;
import br.com.mailanalyzer.log.L;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.hibernate.annotations.Cascade;


/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 27-08-2011
 *
 * REFATORADO: dia 17 de setembro
 * MOTIVO: Incluindo String "original"
 */

public class Composicao implements Item{

    public static final String TAG = "Composição";
    private int elementoInicio;
    private int elementoFim;
    private Raiz raiz;
    private List<Item> itens;
    private Composicao itemPai;
    private int relevancia;
    private String original;


    public Composicao(){
        itens = new ArrayList<Item>();
        relevancia = Peso.NORMAL;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Composicao other = (Composicao) obj;
        if (this.itens != other.itens && (this.itens == null || !this.itens.equals(other.itens))) {
            return false;
        }
        if ((this.original == null) ? (other.original != null) : !this.original.equals(other.original)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + (this.itens != null ? this.itens.hashCode() : 0);
        hash = 71 * hash + (this.original != null ? this.original.hashCode() : 0);
        return hash;
    }

    public int getRelevanciaTotal(){
        int soma = relevancia;
        for(Item i : getItens()){
            if(i instanceof Composicao){
                soma += ((Composicao)i).getRelevanciaTotal();
            }else{
                soma += ((Elemento)i).getRelevanciaTotal();
            }
        }
        return soma;
    }

    public void setRelevancia(int relevancia){
        this.relevancia = relevancia;
    }

    public int getPesoTotal(Composicao conhecimento, Composicao procurado){
        L.d(TAG,"Procurando em composição. ID:"+original);
        int soma = relevancia;
        for(Item i : conhecimento.getItens()){
            if(i instanceof Composicao){
                soma += getPesoTotal((Composicao)i, procurado);
            }else{
                soma += ((Elemento)i).getRelevancia(procurado);
            }
        }
        return (soma==relevancia)?0:soma;
    }



    /**
     * @return the elementoInicio
     */
    public int getElementoInicio() {
        return elementoInicio;
    }

    /**
     * @param elementoInicio the elementoInicio to set
     */
    public void setElementoInicio(int elementoInicio) {
        this.elementoInicio = elementoInicio;
    }

    /**
     * @return the elementoFim
     */
    public int getElementoFim() {
        return elementoFim;
    }

    /**
     * @param elementoFim the elementoFim to set
     */
    public void setElementoFim(int elementoFim) {
        this.elementoFim = elementoFim;
    }

    public int getPeso() {
        return relevancia;
    }

    /**
     * @return the itens
     */
    public List<Item> getItens() {
        return itens;
    }

    /**
     * @param itens the itens to set
     */
    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    /**
     * @return the original
     */
    public String getOriginal() {
        return original;
    }

    /**
     * @param original the original to set
     */
    public void setOriginal(String original) {
        this.original = original;
    }

    /**
     * @return the raiz
     */
    public Raiz getRaiz() {
        return raiz;
    }

    /**
     * @param raiz the raiz to set
     */
    public void setRaiz(Raiz raiz) {
        this.raiz = raiz;
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
    public void setItemPai(Composicao itemPai) {
        this.itemPai = itemPai;
    }
}
