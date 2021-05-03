/*
 * Copyright (c) 2021 Fuel.ag.
 * All rights reserved.
 * https://fuel.ag
 */
package ag.fuel.api.controllers;

import ag.fuel.api.dao.ProductsDAO;
import ag.fuel.api.dao.impl.ProductsDAOImpl;
import ag.fuel.api.pojo.Product;
import ag.fuel.api.pojo.ProductQty;
import ag.fuel.api.pojo.ProductSales;
import ag.fuel.api.utils.CalculateSalesTaxes;
import ag.fuel.api.utils.GlobalProperties;
import ag.fuel.api.utils.LogSLF4J;
import ag.fuel.api.utils.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mauro Sousa
 */
@Api(tags = "Products Services", description = "Product details service")
@RestController
public class ProductsController {

    @ApiOperation(value = "Get Product Details", notes = "Servive to obtain the product details", response = RestResponse.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - Get values"),
            @ApiResponse(code = 204, message = "NO_CONTENT - Doesn't get values"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR - An Exception Occurred")})
    @RequestMapping(path = "/get-product-rate/{productID}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<RestResponse> getProductRate(@PathVariable(value = "productID") int productID) {

        Product myProduct = new Product();
        ProductsDAO productsDAO = new ProductsDAOImpl();

        RestResponse restResponse;

        try {
            myProduct = productsDAO.getProductRate(productID);
            productsDAO.getSession().close();

            restResponse = new RestResponse(myProduct, HttpStatus.OK, HttpStatus.OK.value(), GlobalProperties.MESSAGE_SUCCESS);
            return new ResponseEntity<>(restResponse, HttpStatus.OK);
        } catch (Exception ex) {
            LogSLF4J.logException(this.getClass(), ex);
            restResponse = new RestResponse(LogSLF4J.getException(this.getClass(), ex), HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), GlobalProperties.MESSAGE_ERROR);

            return new ResponseEntity<>(restResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Get All Products Details", notes = "Servive to obtain all product details", response = RestResponse.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - Get values"),
            @ApiResponse(code = 204, message = "NO_CONTENT - Doesn't get values"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR - An Exception Occurred")})
    @RequestMapping(path = "/get-all-products", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<RestResponse> getAllProducts() {

        List<Product> objectList = new ArrayList<>();
        ProductsDAO productsDAO = new ProductsDAOImpl();

        RestResponse restResponse;

        try {
            objectList = productsDAO.getAllProducts();
            productsDAO.getSession().close();

            restResponse = new RestResponse(objectList, HttpStatus.OK, HttpStatus.OK.value(), GlobalProperties.MESSAGE_SUCCESS);
            return new ResponseEntity<>(restResponse, HttpStatus.OK);
        } catch (Exception ex) {
            LogSLF4J.logException(this.getClass(), ex);
            restResponse = new RestResponse(LogSLF4J.getException(this.getClass(), ex), HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), GlobalProperties.MESSAGE_ERROR);

            return new ResponseEntity<>(restResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiOperation(value = "Calculate Sales Taxes", notes = "Servive to obtain the amount of Taxes and Total", response = RestResponse.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - Get values"),
            @ApiResponse(code = 204, message = "NO_CONTENT - Doesn't get values"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR - An Exception Occurred")})
    @RequestMapping(path = "/get-sales-taxes/{itemsList}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<RestResponse> getSalesTaxes(@PathVariable(value = "itemsList") List<Integer> inputProductIdList) {

        ProductsDAO productsDAO = new ProductsDAOImpl();
        List<ProductQty> items = new ArrayList<ProductQty>();
        RestResponse restResponse;

        try {
            inputProductIdList.stream().forEach(elem -> {
                try {
                    items.add(new ProductQty(productsDAO.getProductRate(elem), 1));
                } catch (Exception ex) {
                    LogSLF4J.logException(this.getClass(), ex);
                }
            });
            productsDAO.getSession().close();

            CalculateSalesTaxes salesTaxes = new CalculateSalesTaxes();
            ProductSales myProductSales = new ProductSales();
            myProductSales = salesTaxes.getInvoiceDetails(items);

            restResponse = new RestResponse(myProductSales, HttpStatus.OK, HttpStatus.OK.value(), GlobalProperties.MESSAGE_SUCCESS);
            return new ResponseEntity<>(restResponse, HttpStatus.OK);
        } catch (Exception ex) {
            LogSLF4J.logException(this.getClass(), ex);
            restResponse = new RestResponse(LogSLF4J.getException(this.getClass(), ex), HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), GlobalProperties.MESSAGE_ERROR);

            return new ResponseEntity<>(restResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
