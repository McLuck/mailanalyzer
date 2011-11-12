
package br.com.mailanalyzer.grafico;

import br.com.mailanalyzer.domain.Log;
import br.com.mailanalyzer.log.L;
import br.com.mailanalyzer.utils.Converte;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Lucas Israel
 * @see 09/10/11
 *
 */
public class LogTableModel extends GenericTableModel<Log>{

    private String[] columnNames = { "Quando", "Ocorrência", "TAG", "Classe"};
    private List<Log> logs;

    public LogTableModel() {
        logs = new ArrayList<Log>();
    }

    public LogTableModel( List<Log> logList ) {
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
    public void setData(List<Log> list) {
        this.logs.clear();
        this.logs.addAll( list );
        super.fireTableDataChanged();
    }

    @Override
    public Log getValueAt(int row) {
        return logs.get(row);
    }

    @Override
    public int indexOf(Log entity) {
        return logs.indexOf( entity );
    }

    @Override
    public void clear() {
        this.logs.clear();
        fireTableDataChanged();
    }

    @Override
    public void remove(Log entity) {
        logs.remove( entity );
        super.fireTableDataChanged();
    }

    @Override
    public void add(Log entity) {
        logs.add( 0, entity );
        super.fireTableDataChanged();
    }

    @Override
    public boolean contains(Log entity) {
        return logs.contains(entity);
    }

    @Override
    public List<Log> list() {
        return logs;
    }

    @Override
    public void updateItem(int idx, Log entity) {
        logs.set( idx, entity );
        super.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return logs.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int i, int i1) {
        Log ap = logs.get(i);
        switch( i1 ) {
            case 0: return Converte.ToStringDataVisual(ap.getDataRegistro()).concat(" - ").concat(Converte.DateToStringTimer(ap.getDataRegistro()));
            case 1: return L.GET_OCORRENCIA(ap.getOcorrencia());
            case 2: return ap.getTagApp();
            case 3: return ap.getReferencia();
        }
        return null;
    }

}
