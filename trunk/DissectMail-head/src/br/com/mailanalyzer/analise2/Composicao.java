/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mailanalyzer.analise2;

import br.com.mailanalyzer.log.L;
import br.com.mailanalyzer.main.Config;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author McLuck
 */
public class Composicao implements Comparable<Composicao> {

    /**
     * @return the sequencial
     */
    public boolean isSequencial() {
        return sequencial;
    }

    /**
     * @param sequencial the sequencial to set
     */
    public void setSequencial(boolean sequencial) {
        this.sequencial = sequencial;
    }

    public static final class TIPO {

        public static final int BASE = 1;
        public static final int ELIMINATORIO = 2;
        public static final int MANDATORIO = 3;
        public static final int AGREGACAO = 4;
        public static final int COMUM = 5;
    }
    private int peso, id, tipo;
    private boolean sequencial = false;
    private String textoOriginal;
    private List<Elemento> elementos;
    public static final String TAG = "Composicao";

    public Composicao() {
        elementos = new ArrayList<Elemento>();
        peso = Peso.NORMAL;
    }

    public int getRelevancia(Composicao procurado) {
        if(Config.isNivelLogMaximo()){
            L.d(TAG, this, "Procurando em composição. ID: "+id+" - texto original da composição: "+textoOriginal);
        }
        int soma = peso;
        if (!sequencial) {
            for (Elemento i : getElementos()) {
                soma += i.getRelevancia(procurado);
            }
        } else {
            //Se for sequencial, so deve contar alguma coisa se encontrar na mesma sequencia
            for (int i = 0; i < procurado.getElementos().size(); i++) {
                int temp = 0;
                if (elementos.get(0).getRelevancia(procurado.getElementos().get(i).getPalavra()) > 0) {
                    boolean encontrado = false;
                    int p = 0;
                    for (int j = i; j < procurado.getElementos().size(); j++) {
                        int temp2 = elementos.get(p).getRelevancia(procurado.getElementos().get(j).getPalavra());
                        p++;
                        if (temp2 > 0) {
                            temp += temp2;
                            if (p == elementos.size()) {
                                encontrado = true;
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    if (encontrado) {
                        //Se encontrar sequencia, add novamente o peso da composicao.
                        soma += temp + peso;

                        //elimina bloco ja analisado
                        i += elementos.size();
                    }
                }
            }
        }
        return (soma == peso) ? 0 : soma;
    }

    public int getRelevanciaTotal() {
        int soma = peso;
        for (Elemento i : getElementos()) {
            soma += i.getRelevanciaTotal();
        }
        if (sequencial) {
            soma += peso;
        }
        return soma;
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
        if (this.id != other.id) {
            return false;
        }
        if (this.tipo != other.tipo) {
            return false;
        }
        if (this.sequencial != other.sequencial) {
            return false;
        }
        if ((this.textoOriginal == null) ? (other.textoOriginal != null) : !this.textoOriginal.equals(other.textoOriginal)) {
            return false;
        }
        if (this.elementos != other.elementos && (this.elementos == null || !this.elementos.equals(other.elementos))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.peso;
        hash = 47 * hash + this.id;
        hash = 47 * hash + this.tipo;
        hash = 47 * hash + (this.textoOriginal != null ? this.textoOriginal.hashCode() : 0);
        hash = 47 * hash + (this.elementos != null ? this.elementos.hashCode() : 0);
        return hash;
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
     * @return the textotextoOriginal
     */
    public String getTextoOriginal() {
        return textoOriginal;
    }

    /**
     * @param textoOriginal the textoOriginal to set
     */
    public void setTextoOriginal(String textoOriginal) {
        this.textoOriginal = textoOriginal;
    }

    /**
     * @return the elementos
     */
    public List<Elemento> getElementos() {
        return elementos;
    }

    /**
     * @param elementos the elementos to set
     */
    public void setElementos(List<Elemento> elementos) {
        this.elementos = elementos;
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
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int compareTo(Composicao t) {
        return (t.getId() == this.id) ? 0 : (t.getId() > this.id) ? 1 : -1;
    }
}
