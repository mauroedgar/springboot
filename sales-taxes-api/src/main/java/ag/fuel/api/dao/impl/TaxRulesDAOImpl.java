/*
 * Copyright (c) 2021 Fuel.ag.
 * All rights reserved.
 * https://fuel.ag
 */
package ag.fuel.api.dao.impl;

import ag.fuel.api.dao.TaxRulesDAO;
import ag.fuel.api.mybatis.MyBatisUtil;
import ag.fuel.api.utils.LogSLF4J;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * @author Mauro Sousa
 */
public class TaxRulesDAOImpl implements TaxRulesDAO {

    public SqlSession session;

    @Override
    public SqlSession getSession() {
        return session;
    }

    public void setSession(SqlSession session) {
        this.session = session;
    }

    @Autowired
    private SqlSession getSessionMyBatis() throws IOException, SQLException {
        return new MyBatisUtil().getSession();
    }

    @Override
    public List<Object> getAvailableRates() throws Exception {

        List<Object> result = null;

        try {
            if (getSession() == null) {
                setSession(getSessionMyBatis());
            }
            TaxRulesDAO taxRulesDAO = getSession().getMapper(TaxRulesDAO.class);
            result = taxRulesDAO.getAvailableRates();
        } catch (IOException | SQLException | PersistenceException ex) {
            LogSLF4J.logException(this.getClass(), ex);
            throw new Exception((String) ex.getMessage().toString());
        }
        return result;
    }
}