package com.sibirenergo.bonus.dao;

import com.sibirenergo.bonus.domain.Operation;
import com.sibirenergo.bonus.domain.Card;
import com.sibirenergo.bonus.domain.PointOfSale;

/**
 * Created by IntelliJ IDEA.
 * User: Nikita
 * Date: 19.09.2010
 * Time: 14:17:31
 * Интерфейс для операций с сущностями
 */
public interface BonusDao {

    Operation getOperation(String operationId);

    Card getCard(String cardId);

    PointOfSale getPointOfSale(String pointOfSale);

    void saveOperation(Operation operation);

    void saveCard(Card card);
}
