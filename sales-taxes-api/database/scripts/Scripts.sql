
USE u245148167_sales_taxes;


SELECT r.description as rate FROM rate r


 
WITH rateImport
AS (
	SELECT r.rate_percent
	FROM rate r
	JOIN rate_type rt ON rt.id = r.rate_type
		AND r.id = 3
	)
SELECT p.id AS productID
	,p.description AS productName
	,pt.description AS productType
	,p.price AS productPrice
	,p.imported AS isImported
	,rt.description AS productRateType
	,r.rate_percent AS productRatePercent
	,CASE 
		WHEN p.imported = true
			THEN (
					SELECT ri.rate_percent
					FROM rateImport ri
					)
		ELSE 0
		END AS importRatePercent
FROM product p
JOIN product_type pt ON pt.id = p.product_type
JOIN rate_type rt ON rt.id = pt.rate_type
JOIN rate r ON r.rate_type = rt.id
WHERE p.id = 5


select r.rate_percent from rate r 
JOIN rate_type rt ON rt.id = r.rate_type AND r.id=3

