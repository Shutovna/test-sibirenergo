package com.sibirenergo.bonus.dao;

import com.sibirenergo.bonus.domain.Operation;
import com.sibirenergo.bonus.domain.Card;
import com.sibirenergo.bonus.domain.PointOfSale;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 * Created by IntelliJ IDEA.
 * User: Nikita
 * Date: 19.09.2010
 * Time: 14:17:59
 *
 */
public class BonusDaoImpl extends HibernateDaoSupport implements BonusDao {

    public Operation getOperation(String operationId) {
        return getHibernateTemplate().get(Operation.class, operationId);
    }

    public Card getCard(String cardId) {
        return getHibernateTemplate().get(Card.class, cardId);
    }

    public PointOfSale getPointOfSale(String pointName) {
        return getHibernateTemplate().get(PointOfSale.class, pointName);
    }

    public void saveOperation(Operation operation) {
        getHibernateTemplate().saveOrUpdate(operation);
    }

    public void saveCard(Card card) {        
        getHibernateTemplate().saveOrUpdate(card);
    }
}
