/*
 * Copyright (c) 2021 Fuel.ag.
 * All rights reserved.
 * https://fuel.ag
 */
package ag.fuel.api.mybatis;

import ag.fuel.api.utils.ErrorsProperties;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.io.Reader;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author Mauro Sousa
 */
public class MyBatisUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyBatisUtil.class);
    private SqlSession session = null;

    public SqlSession getSession() throws IOException, SQLException {
        Reader inputStream = Resources.getResourceAsReader("mybatis/config/api.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream, getDatabaseProperties());
        session = sqlSessionFactory.openSession();

        return session;
    }

    private static Properties getDatabaseProperties() {
        Resource resource = new ClassPathResource("mybatis/config/api.properties");
        Properties databaseProperties = null;
        try {
            databaseProperties = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            LOGGER.error(ErrorsProperties.EXCEPTION + e);
        }
        return databaseProperties;
    }
}
