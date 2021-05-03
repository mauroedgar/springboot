/*
 * Copyright (c) 2021 Fuel.ag.
 * All rights reserved.
 * https://fuel.ag
 */
 
USE u245148167_sales_taxes;

/**
 * @author Mauro Sousa
 */
 
TRUNCATE TABLE rate;
TRUNCATE TABLE product;
DELETE FROM product_type WHERE id > 0;
DELETE FROM rate_type WHERE id > 0;

INSERT INTO rate_type VALUES (1, 'Exempt');
INSERT INTO rate_type VALUES (2, 'Regular');
INSERT INTO rate_type VALUES (3, 'Import');

INSERT INTO product_type VALUES (1, 'Other', 2);
INSERT INTO product_type VALUES (2, 'Book', 1);
INSERT INTO product_type VALUES (3, 'Food', 1);
INSERT INTO product_type VALUES (4, 'Medical', 1);

INSERT INTO rate VALUES (1, 'Exempt Rate', 0, 1);
INSERT INTO rate VALUES (2, 'Regular Rate (10%)', 0.1, 2);
INSERT INTO rate VALUES (3, 'Import Rate (5%)', 0.05, 3);

## INPUT 1
INSERT INTO product VALUES (1, 'Book', 12.49, 2, false);
INSERT INTO product VALUES (2, 'Music CD', 14.99, 1, false);
INSERT INTO product VALUES (3, 'Chocolate Bar', 0.85, 3, false);
## INPUT 2
INSERT INTO product VALUES (4, '[Imported] Box of Chocolates', 10.00, 3, true);
INSERT INTO product VALUES (5, '[Imported] Bottle of Perfume', 47.50, 1, true);
## INPUT 3
INSERT INTO product VALUES (6, '[Imported] Bottle of Perfume', 27.99, 1, true);
INSERT INTO product VALUES (7, 'Bottle of Perfume', 18.99, 1, false);
INSERT INTO product VALUES (8, 'Packet of Headache Pills', 9.75, 4, false);
INSERT INTO product VALUES (9, '[Imported] Box of Chocolates', 11.25, 3, true);