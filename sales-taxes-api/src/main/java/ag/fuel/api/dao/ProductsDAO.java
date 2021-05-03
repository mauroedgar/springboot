/*
 * Copyright (c) 2021 Fuel.ag.
 * All rights reserved.
 * https://fuel.ag
 */
package ag.fuel.api.dao;

import ag.fuel.api.pojo.Product;
import org.apache.ibatis.session.SqlSession;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author Mauro Sousa
 */
public interface ProductsDAO {

    SqlSession getSession();

    void setSession(SqlSession session);

    Product getProductRate(@PathVariable(value = "productID") int productID) throws Exception;
    List<Product> getAllProducts() throws Exception;
}
