/*
 * Copyright (c) 2021 Fuel.ag.
 * All rights reserved.
 * https://fuel.ag
 */
 
USE u245148167_sales_taxes;

/**
 * @author Mauro Sousa
 */
 
# FK
ALTER TABLE rate DROP FOREIGN KEY IF EXISTS r_rate_type_fk;
ALTER TABLE product_type DROP FOREIGN KEY IF EXISTS pt_rate_type_fk;
ALTER TABLE product DROP FOREIGN KEY IF EXISTS p_product_type_fk;

# PK
ALTER TABLE rate DROP INDEX IF EXISTS `PRIMARY`;
ALTER TABLE product DROP INDEX IF EXISTS `PRIMARY`;
ALTER TABLE product_type DROP INDEX IF EXISTS `PRIMARY`;
ALTER TABLE rate_type DROP INDEX IF EXISTS `PRIMARY`;

# TABLES
DROP TABLE IF EXISTS rate;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS product_type;
DROP TABLE IF EXISTS rate_type;


