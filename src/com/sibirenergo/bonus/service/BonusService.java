package com.sibirenergo.bonus.service;

/**
 * Created by IntelliJ IDEA.
 * User: Nikita
 * Date: 19.09.2010
 * Time: 14:28:01
 * Сервис для операций с картами
 */
public interface BonusService {
    /**
     * Операция регистрации покупки и начисления баллов
     *
     * @param cardId      номер карты
     * @param pointOfSale место совершения операции
     * @param amount      общая сумма покупки
     * @param operationId идентификатор операции
     * @return текущий баланс карты
     * @throws OperationAlreadyExistsException
     *          если операция с таким идентификатором уже есть
     */
    double award(String cardId, String pointOfSale, Double amount, String operationId) throws OperationAlreadyExistsException;

    /**
     * Операция получения текущего баланса
     *
     * @param cardId номер карты
     * @return текущий баланс карты
     * @throws CardNotFoundException если данные о карте отутствуют
     */
    double check(String cardId) throws CardNotFoundException;

    /**
     * @param cardId      номер карты
     * @param pointOfSale место совершения операции
     * @param amount      общая сумма покупки
     * @param operationId идентификатор операции
     * @return текущий баланс карты
     * @throws CardNotFoundException      если данные о карте отутствуют
     * @throws OperationAlreadyExistsException
     *                                    если операция с таким идентификатором уже есть
     * @throws InsufficientFundsException если на балансе карты не достаточно средств
     */
    double pay(String cardId, String pointOfSale, double amount, String operationId) throws CardNotFoundException, OperationAlreadyExistsException, InsufficientFundsException;
}
