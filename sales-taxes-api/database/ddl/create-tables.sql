/*
 * Copyright (c) 2021 Fuel.ag.
 * All rights reserved.
 * https://fuel.ag
 */

USE u245148167_sales_taxes;

/**
 * @author Mauro Sousa
 */
 
CREATE TABLE rate_type
(
    id       		BIGINT NOT NULL,
    description 	VARCHAR(100) NOT NULL
);
CREATE TABLE product_type
(
    id       		BIGINT NOT NULL,
    description 	VARCHAR(100) NOT NULL,
    rate_type		BIGINT NOT NULL
);
CREATE TABLE rate
(
    id       		BIGINT NOT NULL,
    description 	VARCHAR(100) NOT NULL,
    rate_percent    DOUBLE NOT NULL,
    rate_type		BIGINT NOT NULL
);
CREATE TABLE product
(
    id       		BIGINT NOT NULL,
    description 	VARCHAR(100) NOT NULL,
    price       	DOUBLE NOT NULL,
    product_type	BIGINT NOT NULL,
    imported		BOOLEAN NOT NULL
);