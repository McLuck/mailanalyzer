package br.com.mailanalyzer.dao;

import br.com.mailanalyzer.dao.interfaces.MessageInterfaceDAO;
import br.com.mailanalyzer.domain.Message;
import br.com.mailanalyzer.utils.GenericsUtil;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Lucas Israel
 * @contact mcluck.ti@gmail.com
 * @version 1.0
 * @Date 29-04-2011
 *
 */

public class MessageDAO extends BaseDAO<Message> implements MessageInterfaceDAO{
    /**
     * Cria uma nova instância do tipo {@link MessageDAO}
     */
    public MessageDAO(){
        super(Message.class);
    }
    
    private static MessageDAO instance;
    public static MessageDAO getInstance(){
    	if(instance == null){
    		instance = new MessageDAO();
    	}
    	return instance;
    }

    /**
     * {@inheritDoc}
     */
    public List<Message> getLikeKeyword(String keyword) {
        Criteria criteria = this.createCriteria(Message.class);
        criteria.add(Restrictions.like("assunto", keyword));
        criteria.add(Restrictions.like("mensagem", keyword));
        return GenericsUtil.checkedList(criteria.list(), Message.class);
    }
    
}
