/*
 * Copyright (c) 2021 Fuel.ag.
 * All rights reserved.
 * https://fuel.ag
 */
package ag.fuel.api.dao;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

/**
 * @author Mauro Sousa
 */
public interface TaxRulesDAO {

    SqlSession getSession();

    void setSession(SqlSession session);

    List<Object> getAvailableRates() throws Exception;
}
