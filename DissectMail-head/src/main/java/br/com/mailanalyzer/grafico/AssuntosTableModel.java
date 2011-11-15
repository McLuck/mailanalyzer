/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.grafico;

import br.com.mailanalyzer.domain.AssuntosPendentes;
import br.com.mailanalyzer.utils.Converte;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author McLuck
 */
public class AssuntosTableModel extends GenericTableModel<AssuntosPendentes>{

    private String[] columnNames = { "Origem", "Data", "Mensagem"};
    private List<AssuntosPendentes> assuntos;

    public AssuntosTableModel() {
        assuntos = new ArrayList<AssuntosPendentes>();
    }

    public AssuntosTableModel( List<AssuntosPendentes> logList ) {
        this();
        setData( logList );
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    @Override
    public void setData(List<AssuntosPendentes> list) {
        this.assuntos.clear();
        this.assuntos.addAll( list );
        super.fireTableDataChanged();
    }

    @Override
    public AssuntosPendentes getValueAt(int row) {
        return assuntos.get(row);
    }

    @Override
    public int indexOf(AssuntosPendentes entity) {
        return assuntos.indexOf( entity );
    }

    @Override
    public void clear() {
        this.assuntos.clear();
        fireTableDataChanged();
    }

    @Override
    public void remove(AssuntosPendentes entity) {
        assuntos.remove( entity );
        super.fireTableDataChanged();
    }

    @Override
    public void add(AssuntosPendentes entity) {
        assuntos.add( 0, entity );
        super.fireTableDataChanged();
    }

    @Override
    public boolean contains(AssuntosPendentes entity) {
        return assuntos.contains(entity);
    }

    @Override
    public List<AssuntosPendentes> list() {
        return assuntos;
    }

    @Override
    public void updateItem(int idx, AssuntosPendentes entity) {
        assuntos.set( idx, entity );
        super.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return assuntos.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        AssuntosPendentes ap = assuntos.get(i);
        switch( i1 ) {
            case 0: return ap.getMessage().getOrigem();
            case 1: return Converte.ToStringDataVisual(ap.getMessage().getDataRegistro());
            case 2: return ap.getMessage().getMensagem();
        }
        return null;
    }

}
