/*
 * Copyright (c) 2021 Fuel.ag.
 * All rights reserved.
 * https://fuel.ag
 */

USE u245148167_sales_taxes;

/**
 * @author Mauro Sousa
 */
 
# PK
ALTER TABLE rate_type ADD PRIMARY KEY (id);
ALTER TABLE product_type ADD PRIMARY KEY (id);
ALTER TABLE rate ADD PRIMARY KEY (id);
ALTER TABLE product ADD PRIMARY KEY (id);

# FK
ALTER TABLE rate ADD FOREIGN KEY r_rate_type_fk (rate_type) REFERENCES rate_type (id);
ALTER TABLE product_type ADD FOREIGN KEY pt_rate_type_fk (rate_type) REFERENCES rate_type (id);
ALTER TABLE product ADD FOREIGN KEY p_product_type_fk (product_type) REFERENCES product_type (id);
