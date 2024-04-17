package com.sibirenergo.bonus.service;

/**
 * Created by IntelliJ IDEA.
 * User: Nikita
 * Date: 19.09.2010
 * Time: 14:28:01
 * ������ ��� �������� � �������
 */
public interface BonusService {
    /**
     * �������� ����������� ������� � ���������� ������
     *
     * @param cardId      ����� �����
     * @param pointOfSale ����� ���������� ��������
     * @param amount      ����� ����� �������
     * @param operationId ������������� ��������
     * @return ������� ������ �����
     * @throws OperationAlreadyExistsException
     *          ���� �������� � ����� ��������������� ��� ����
     */
    double award(String cardId, String pointOfSale, Double amount, String operationId) throws OperationAlreadyExistsException;

    /**
     * �������� ��������� �������� �������
     *
     * @param cardId ����� �����
     * @return ������� ������ �����
     * @throws CardNotFoundException ���� ������ � ����� ����������
     */
    double check(String cardId) throws CardNotFoundException;

    /**
     * @param cardId      ����� �����
     * @param pointOfSale ����� ���������� ��������
     * @param amount      ����� ����� �������
     * @param operationId ������������� ��������
     * @return ������� ������ �����
     * @throws CardNotFoundException      ���� ������ � ����� ����������
     * @throws OperationAlreadyExistsException
     *                                    ���� �������� � ����� ��������������� ��� ����
     * @throws InsufficientFundsException ���� �� ������� ����� �� ���������� �������
     */
    double pay(String cardId, String pointOfSale, double amount, String operationId) throws CardNotFoundException, OperationAlreadyExistsException, InsufficientFundsException;
}
