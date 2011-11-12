/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.mailanalyzer.dao;

import br.com.mailanalyzer.dao.interfaces.PalavraInterfaceDAO;
import br.com.mailanalyzer.domain.Palavra;
import br.com.mailanalyzer.utils.GenericsUtil;
import br.com.mailanalyzer.utils.ListPalavra;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author McLuck
 */
public class PalavraDAO extends BaseDAO<Palavra> implements PalavraInterfaceDAO{
    private PalavraDAO(){
        super(Palavra.class);
    }

    private static PalavraDAO instance;
    public static PalavraDAO getInstance(){
        if(instance == null){
            instance = new PalavraDAO();
        }
        return instance;
    }
    private Palavra temp;

    public Palavra getPalavra(String palavra){
        if(existe(palavra)){
            return temp;
        }
        Palavra p = new Palavra(palavra);
        Object o = salvar(p);
        commit();
        if(o instanceof Palavra){
            p = (Palavra)o;
        }else if(o instanceof Integer){
            p.setId((Integer)o);
        }
        return p;
    }

    @Override
    public boolean existe(String palavra) {
        try{
            palavra = palavra.replace(" ", "").toLowerCase();
            Criteria criteria = createCriteria(Palavra.class);
            criteria.add(Restrictions.eq("palavra", palavra));
            List<Palavra> aux = GenericsUtil.checkedList(criteria.list(), Palavra.class);
            if(aux!= null && !aux.isEmpty()){
                temp = aux.get(0);
                return true;
            }
            return false;
        }catch(Exception e){

        }
        return false;
    }

    public Palavra getPalavraVerificada(){
        return temp;
    }

    @Override
    public ListPalavra getPalavrasSalvas(String texto) {
        texto = texto.trim().toLowerCase();
        String[] ps = texto.split(" ");

        Criteria criteria = createCriteria(Palavra.class);
        criteria.add(Restrictions.in("palavra", ps));
        ListPalavra existentes = new ListPalavra(GenericsUtil.checkedList(criteria.list(), Palavra.class));

        for(String p : ps){
            if(!existentes.contains(p)){
                Palavra pl = new Palavra(p);
                Object o = salvar(pl);
                if(o instanceof Palavra){
                    pl = (Palavra)o;
                }else if(o instanceof Integer){
                    pl.setId((Integer)o);
                }
                existentes.add(pl);
            }
        }
        commit();
        return existentes;
    }
}
