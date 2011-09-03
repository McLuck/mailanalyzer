package br.com.mailanalyzer.analise;

import br.com.mailanalyzer.log.L;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 27-08-2011
 *
 */
public class Composicao implements Item{

    public static final String TAG = "Composição";
    private int id, elementoInicio, elementoFim;
    private List<Item> itens;
    private int relevancia;
    public Composicao(){
        itens = new ArrayList<Item>();
        relevancia = Peso.NORMAL;
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
        L.d(TAG,"Procurando em composição. ID:"+id);
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
}
