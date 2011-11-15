package br.com.mailanalyzer.dao.actions;

import br.com.mailanalyzer.dao.FieldDAO;
import br.com.mailanalyzer.domain.Field;
import br.com.mailanalyzer.utils.Converte;
import java.util.List;

/**
 *
 * @author Lobo
 * @contact pedro.lobo29@gmail.com
 * @version 1.0
 * @Date 07/05/2011
 * @reviser ---
 */
public class FieldAction {

    private Field field;

    /**
     * @return the field
     */
    public Field getField() {
        return field;
    }

    /**
     * @param field the field to set
     */
    public void getById() {

        FieldDAO dao = new FieldDAO();
        field = dao.obter(field.getId());

        //System.out.println("----- Recuperando pela ID: " + field.getId());
        //System.out.println("Nome: " + field.getName());
        //System.out.println("-----");

    }

    public void setField(Field field) {
        this.field = field;
    }

    public void Salvar() {
        //System.out.println("Cadastrando...");              
        FieldDAO dao = new FieldDAO();
        dao.salvar(field);
        //System.out.println(field.getName() + " foi salvo com sucesso. ID do objeto: " + field.getId() + " - Na data: " + Converte.ToStringDataVisual(field.getDataRegistro()) + " - " + Converte.DateToStringTimer(field.getDataRegistro()));
        //System.out.println("---");
    }

    public void excluir() {
        FieldDAO dao = new FieldDAO();
        dao.excluir(field);
        //System.out.println(field.getName() + " foi excluido com sucesso.");
        //System.out.println("---");
    }

    public List<Field> getByName(String search) {
        FieldDAO dao = new FieldDAO();
        field = dao.obter(field.getId());
        List<Field> lista = dao.getLikeName(search);
        return lista;
    }

    public List<Field> showALL() {
        FieldDAO dao = new FieldDAO();
        List<Field> lista = dao.obterTodos();
        for (Field a : lista) {
            //System.out.println("ID: " + a.getId() + " - Nome: " + a.getName());

        }
        return lista;
    }

    public List<Field> getBySubject(Integer subjectID) {
        FieldDAO dao = new FieldDAO();
        List<Field> lista = dao.getBySubject(Integer.MIN_VALUE);
        
        return lista;
    }
}
