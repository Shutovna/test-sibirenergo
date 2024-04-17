package com.sibirenergo.bonus.service;

import com.sibirenergo.bonus.dao.BonusDao;
import com.sibirenergo.bonus.domain.*;
import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by IntelliJ IDEA.
 * User: Nikita
 * Date: 19.09.2010
 * Time: 14:28:13
 * Реализация сервиса операций
 */
@Transactional(propagation = Propagation.REQUIRED)
public class BonusServiceImpl implements BonusService {
    public static final double BONUS_PERCENT = 10;
    private Logger log = Logger.getLogger(BonusServiceImpl.class);

    private BonusDao dao;

    public double award(String cardId, String pointOfSale, Double amount, String operationId) throws OperationAlreadyExistsException {
        Operation operation = dao.getOperation(operationId);
        if (operation != null) {
            throw new OperationAlreadyExistsException("Operation " + operation + " already exists");
        }

        Card card = dao.getCard(cardId);
        if (card == null) {
            card = new Card(cardId);
            dao.saveCard(card);
        }

        PointOfSale point = dao.getPointOfSale(pointOfSale);
        if (point == null) {
            point = new PointOfSale(pointOfSale);
        }

        double award = (amount / 100d) * BONUS_PERCENT;

        operation = new Purchase(operationId, card, point, amount, award);
        card.setBalance(card.getBalance() + award);

        dao.saveOperation(operation);

        log.debug("card: " + card);
        log.debug("operation: " + operation);

        return card.getBalance();
    }

    public double check(String cardId) throws CardNotFoundException {
        Card card = dao.getCard(cardId);
        if (card == null) {
            throw new CardNotFoundException("Card " + card + " not found");
        }
        return card.getBalance();
    }

    public double pay(String cardId, String pointOfSale, double amount, String operationId) throws CardNotFoundException, OperationAlreadyExistsException, InsufficientFundsException {
        Card card = dao.getCard(cardId);
        if (card == null) {
            throw new CardNotFoundException("Card " + card + " not found");
        }

        Operation operation = dao.getOperation(operationId);
        if (operation != null) {
            throw new OperationAlreadyExistsException("Operation " + operation + " already exists");
        }

        if(card.getBalance() < amount) {
            throw new InsufficientFundsException("Card " + card + " has not enough money to discard " + amount, amount);
        }

        PointOfSale point = dao.getPointOfSale(pointOfSale);
        if (point == null) {
            point = new PointOfSale(pointOfSale);
        }

        operation = new UseBonus(operationId, card, point, amount);
        card.setBalance(card.getBalance() - amount);

        dao.saveOperation(operation);

        return card.getBalance();
    }

    public void setDao(BonusDao dao) {
        this.dao = dao;
    }
}
