package com.sibirenergo.bonus.service;

/**
 * Created by IntelliJ IDEA.
 * User: Nikita
 * Date: 19.09.2010
 * Time: 14:33:56
 * ������������ � ������ ������� �������� �������� ������� ��� ���� � ����
 */
public class OperationAlreadyExistsException extends Exception {

    public OperationAlreadyExistsException(String message) {
        super(message);
    }
}
