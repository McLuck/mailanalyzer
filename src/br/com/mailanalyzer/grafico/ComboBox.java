/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.mailanalyzer.grafico;

import br.com.mailanalyzer.analise2.Raiz;

/**
 *
 * @author McLuck
 */
public class ComboBox {
    public ComboBox(Raiz r) {
        this.raiz = r;
        this.id = r.getId();
        this.label = r.getAssunto().getName();
    }
    private int id;
    private String label;
    private Raiz raiz;

    @Override
    public String toString() {
        return label;
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
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }
}
