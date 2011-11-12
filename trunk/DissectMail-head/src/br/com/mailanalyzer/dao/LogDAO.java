package br.com.mailanalyzer.dao;

import br.com.mailanalyzer.dao.interfaces.LogInterfaceDAO;
import br.com.mailanalyzer.domain.DomainObject;
import br.com.mailanalyzer.domain.Log;
import br.com.mailanalyzer.utils.GenericsUtil;
import java.util.List;
import java.util.Map;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.SimpleExpression;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 29-04-2011
 *
 */
public class LogDAO extends BaseDAO<Log> implements LogInterfaceDAO{
    
    /**
     * Cria uma nova instância do tipo {@link LogDAO}
     */
    public LogDAO(){
        super(Log.class);
        isLOGGING = true;
    }

    @Override
    public Order getOrdemLista(){
        return Order.desc("id");
    }

    /**
     * {@inheritDoc}
     */
    public List<Log> getLikeDetalhe(String detalhe) {
        Criteria criteria = this.createCriteria(Log.class);
        criteria.add(Restrictions.like("detalhe", "%"+detalhe+"%"));
        criteria.addOrder(getOrdemLista());
        criteria.setMaxResults(getQtdRegistro());
        return GenericsUtil.checkedList(criteria.list(), Log.class);
    }

    public List<Log> getLikeAll(String all) {
        Criteria criteria = this.createCriteria(Log.class);
        criteria.add(Restrictions.or(Restrictions.like("detalhe", "%"+all+"%"), Restrictions.or(Restrictions.like("referencia", "%"+all+"%"), Restrictions.like("tagApp", "%"+all+"%"))));
        criteria.addOrder(getOrdemLista());
        criteria.setMaxResults(getQtdRegistro());
        return GenericsUtil.checkedList(criteria.list(), Log.class);
    }

    public List<Log> getLikeLikeTag(String all) {
        Criteria criteria = this.createCriteria(Log.class);
        criteria.add(Restrictions.like("tagApp", "%"+all+"%"));
        criteria.addOrder(getOrdemLista());
        criteria.setMaxResults(getQtdRegistro());
        return GenericsUtil.checkedList(criteria.list(), Log.class);
    }

    public List<Log> getLikeLikeReferencia(String referencia) {
        Criteria criteria = this.createCriteria(Log.class);
        criteria.add(Restrictions.like("referencia", "%"+referencia+"%"));
        criteria.addOrder(getOrdemLista());
        criteria.setMaxResults(getQtdRegistro());
        return GenericsUtil.checkedList(criteria.list(), Log.class);
    }


    /**
     * {@inheritDoc}
     */
    public List<Log> getByOcorrencia(Integer ocorrencia) {
        Criteria criteria = this.createCriteria(Log.class);
        criteria.add(Restrictions.eq("ocorrencia", ocorrencia));
        criteria.addOrder(getOrdemLista());
        criteria.setMaxResults(getQtdRegistro());
        return GenericsUtil.checkedList(criteria.list(), Log.class);
    }

    public List<Log> getByOcorrencias(Integer[] ocorrencias) {
        Criteria criteria = this.createCriteria(Log.class);
        criteria.add(Restrictions.in("ocorrencia", ocorrencias));
        criteria.addOrder(getOrdemLista());
        criteria.setMaxResults(getQtdRegistro());
        return GenericsUtil.checkedList(criteria.list(), Log.class);
    }

    /**
     * {@inheritDoc}
     */
    public List<Log> getByReferencia(DomainObject referencia) {
        StringBuffer sb = new StringBuffer();
        sb.append(referencia.getId());
        sb.append(";");
        sb.append(referencia.getClass().getName());
        sb.append(";");
        Criteria criteria = this.createCriteria(Log.class);
        criteria.add(Restrictions.eq("referencia", sb.toString()));
        return GenericsUtil.checkedList(criteria.list(), Log.class);
    }

    public List<Log> getFromParameters(Map<String, Object> parameters){
        Criteria criteria = createCriteria(Log.class);
        criteria.setMaxResults((Integer)parameters.get(Parameters.NUMERO_REGISTROS));
        long inicio = 0;
        if(parameters.get(Parameters.INICIO)!=null){
            inicio = (Long)(parameters.get(Parameters.INICIO));
        }else{
            inicio = (new java.util.Date().getTime()-(1000*60*60*24));
        }


        String referencia = String.valueOf(parameters.get(Parameters.CLASSE_REFERENCIA));
        String tag = String.valueOf(parameters.get(Parameters.TAG));
        String detalhes = String.valueOf(parameters.get(Parameters.DETALHES));
        
        boolean temRef = parameters.get(Parameters.CLASSE_REFERENCIA)!=null;
        boolean temTag = parameters.get(Parameters.TAG)!=null;
        boolean temDetalhes = parameters.get(Parameters.DETALHES)!=null;

        SimpleExpression restReferencia = Restrictions.like("referencia", "%"+referencia+"%");
        SimpleExpression restTAG = Restrictions.like("tagApp", "%"+tag+"%");
        SimpleExpression restDetalhes = Restrictions.like("detalhe", "%"+detalhes+"%");

        //Add data_inicio, add tipos
        if(!temRef && ! temTag && !temDetalhes){
            criteria.add(Restrictions.and(
                    Restrictions.ge("dataRegistro", inicio),
                    Restrictions.in("ocorrencia", (Object[])parameters.get(Parameters.TIPOS))));
        }else if(!temTag && !temDetalhes){
            criteria.add(Restrictions.and(
                    Restrictions.and(
                        Restrictions.in("ocorrencia", (Object[])parameters.get(Parameters.TIPOS)),
                        restReferencia),
                    Restrictions.ge("dataRegistro", inicio)));
        }else if(!temRef && !temDetalhes){
            criteria.add(Restrictions.and(
                    Restrictions.and(
                        Restrictions.in("ocorrencia", (Object[])parameters.get(Parameters.TIPOS)),
                        restTAG),
                    Restrictions.ge("dataRegistro", inicio)));
        }else if(!temRef && ! temTag){
            criteria.add(Restrictions.and(
                    Restrictions.and(
                        Restrictions.in("ocorrencia", (Object[])parameters.get(Parameters.TIPOS)),
                        restDetalhes),
                    Restrictions.ge("dataRegistro", inicio)));
        }else if(!temRef){
            criteria.add(Restrictions.and(
                    Restrictions.and(Restrictions.and(
                        Restrictions.in("ocorrencia", (Object[])parameters.get(Parameters.TIPOS)),
                        restDetalhes),
                        restTAG),
                    Restrictions.ge("dataRegistro", inicio)));
        }else if(!temTag){
            criteria.add(Restrictions.and(
                    Restrictions.and(Restrictions.and(
                        Restrictions.in("ocorrencia", (Object[])parameters.get(Parameters.TIPOS)),
                        restDetalhes),
                        restReferencia),
                    Restrictions.ge("dataRegistro", inicio)));
        }else if(!temDetalhes){
            criteria.add(Restrictions.and(
                    Restrictions.and(Restrictions.and(
                        Restrictions.in("ocorrencia", (Object[])parameters.get(Parameters.TIPOS)),
                        restReferencia),
                        restTAG),
                    Restrictions.ge("dataRegistro", inicio)));
        }else{
            //TEM TUDO
            criteria.add(Restrictions.and(
                    Restrictions.and(Restrictions.and(
                        Restrictions.in("ocorrencia", (Object[])parameters.get(Parameters.TIPOS)),
                        restDetalhes),
                        restTAG),
                    Restrictions.and(Restrictions.ge("dataRegistro", inicio),
                        restReferencia)));
        }
        criteria.addOrder(Order.desc("dataRegistro"));
        
        return GenericsUtil.checkedList(criteria.list(), Log.class);
    }

    public static class Parameters{
        public static final String INICIO = "DATA_INICIO";
        public static final String FIM = "DATA_FINAL";
        public static final String CLASSE_REFERENCIA = "Classe_DE_Referencia";
        public static final String DETALHES = "DetalhesDoLog";
        public static final String TIPOS = "TIPOS";
        public static final String TAG = "TAG";
        public static final String NUMERO_REGISTROS = "NUMERO_DE_REGISTROS";
    }
}
