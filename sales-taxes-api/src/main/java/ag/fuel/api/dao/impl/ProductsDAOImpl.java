/*
 * Copyright (c) 2021 Fuel.ag.
 * All rights reserved.
 * https://fuel.ag
 */
package ag.fuel.api.dao.impl;

import ag.fuel.api.dao.ProductsDAO;
import ag.fuel.api.mybatis.MyBatisUtil;
import ag.fuel.api.pojo.Product;
import ag.fuel.api.utils.LogSLF4J;
import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Mauro Sousa
 */
public class ProductsDAOImpl implements ProductsDAO {

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
    public Product getProductRate(int productID) throws Exception {

        Product result = new Product();

        try {
            if (getSession() == null) {
                setSession(getSessionMyBatis());
            }
            ProductsDAO productsDAO = getSession().getMapper(ProductsDAO.class);
            result = productsDAO.getProductRate(productID);
        } catch (IOException | SQLException | PersistenceException ex) {
            LogSLF4J.logException(this.getClass(), ex);
            throw new Exception((String) ex.getMessage().toString());
        }
        return result;
    }

    @Override
    public List<Product> getAllProducts() throws Exception {

        List<Product> result = new ArrayList<>();

        try {
            if (getSession() == null) {
                setSession(getSessionMyBatis());
            }
            ProductsDAO productsDAO = getSession().getMapper(ProductsDAO.class);
            result = productsDAO.getAllProducts();
        } catch (IOException | SQLException | PersistenceException ex) {
            LogSLF4J.logException(this.getClass(), ex);
            throw new Exception((String) ex.getMessage().toString());
        }
        return result;
    }
}