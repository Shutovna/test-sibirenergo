package com.sibirenergo.bonus.servlet;

import com.sibirenergo.bonus.service.BonusService;
import com.sibirenergo.bonus.service.CardNotFoundException;
import com.sibirenergo.bonus.service.InsufficientFundsException;
import com.sibirenergo.bonus.service.OperationAlreadyExistsException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;


/**
 * User: Nikita Shutov
 * Date: 18.09.2010
 * Time: 12:52:59
 * "Frontend" сервиса
 */
public class BonusServlet extends HttpServlet {
    private Logger log = Logger.getLogger(BonusServlet.class);

    private BonusService service;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        service = (BonusService) config.getServletContext().getAttribute("bonusService");
        log.debug("servlet inited, bonus service injected " + service);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("processing bonus request...");
        log.debug("parameters:");
        printParams(req);
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String operation = req.getParameter("operation");
        log.debug("processing operation " + operation);
        try {
            if (operation == null) {
                throw new NullPointerException("Operation is null");

            } else {
                if (operation.equals("award")) {
                    processAward(req, resp);

                } else if (operation.equals("check")) {
                    processCheck(req, resp);

                } else if (operation.equals("pay")) {
                    processPay(req, resp);

                } else {
                    throw new IllegalStateException("Unknow operation " + operation);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    private void processAward(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String cardId = req.getParameter("card");
        String pointOfSale = req.getParameter("pointOfSale");
        String amount = req.getParameter("amount");
        String operationId = req.getParameter("id");

        log.info("processing award...");

        if (StringUtils.isEmpty(cardId) ||
                StringUtils.isEmpty(pointOfSale) ||
                StringUtils.isEmpty(amount) ||
                StringUtils.isEmpty(amount) ||
                StringUtils.isEmpty(operationId)
                ) {
            throw new IllegalArgumentException("One of argment is invalid");
        }

        try {
            double cardBalance = service.award(cardId, pointOfSale, Double.parseDouble(amount), operationId);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(cardBalance);

            log.info("award processed");

        } catch (OperationAlreadyExistsException e) {
            log.error(e.getMessage(), e);
            resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        }
    }

    private void processCheck(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String cardId = req.getParameter("card");

        log.info("processing check...");

        if (StringUtils.isEmpty(cardId)) {
            throw new IllegalArgumentException("One of argment is invalid");
        }

        try {
            double cardBalance = service.check(cardId);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(cardBalance);

            log.info("check processed");

        } catch (CardNotFoundException e) {
            log.error(e.getMessage(), e);
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }
    }

    private void processPay(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String cardId = req.getParameter("card");
        String pointOfSale = req.getParameter("pointOfSale");
        String amount = req.getParameter("amount");
        String operationId = req.getParameter("id");

        log.info("processing pay...");

        if (StringUtils.isEmpty(cardId) ||
                StringUtils.isEmpty(pointOfSale) ||
                StringUtils.isEmpty(amount) ||
                StringUtils.isEmpty(amount) ||
                StringUtils.isEmpty(operationId)
                ) {
            throw new IllegalArgumentException("One of argment is invalid");
        }

        try {
            double cardBalance = service.pay(cardId, pointOfSale, Double.parseDouble(amount), operationId);
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().println(cardBalance);

            log.info("pay processed");
            
        } catch (CardNotFoundException e) {
            log.error(e.getMessage(), e);
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } catch (OperationAlreadyExistsException e) {
            log.error(e.getMessage(), e);
            resp.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
        } catch (InsufficientFundsException e) {
            log.error(e.getMessage(), e);
            resp.setStatus(HttpServletResponse.SC_CONFLICT);
        }
    }


    private void printParams(HttpServletRequest req) {
        Enumeration parameterNames = req.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            Object param = parameterNames.nextElement();
            Object val = req.getParameterMap().get(param);
            log.debug(param + ": " + (val instanceof String ? val : ((String[]) val)[0]));
        }
    }

    public void setService(BonusService service) {
        this.service = service;
    }
}


