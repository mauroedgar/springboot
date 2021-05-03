/*
 * Copyright (c) 2021 Fuel.ag.
 * All rights reserved.
 * https://fuel.ag
 */
package ag.fuel.api.controllers;

import ag.fuel.api.dao.TaxRulesDAO;
import ag.fuel.api.dao.impl.TaxRulesDAOImpl;
import ag.fuel.api.utils.GlobalProperties;
import ag.fuel.api.utils.LogSLF4J;
import ag.fuel.api.utils.RestResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Mauro Sousa
 */
@Api(tags = "Sales Taxes Services", description = "Rules for Sales Rate Attribution")
@RestController
public class SalesTaxesController {

    @ApiOperation(value = "Get all available taxes", notes = "Servive to obtain all taxes available", response = RestResponse.class, responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK - Get values"),
            @ApiResponse(code = 204, message = "NO_CONTENT - Doesn't get values"),
            @ApiResponse(code = 500, message = "INTERNAL SERVER ERROR - An Exception Occurred")})
    @RequestMapping(path = "/get-available-rates", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<RestResponse> getAvailableRates() {

        List<Object> objectList = new ArrayList<>();
        TaxRulesDAO taxRulesDAO = new TaxRulesDAOImpl();

        RestResponse restResponse;

        try {
            objectList = taxRulesDAO.getAvailableRates();
            taxRulesDAO.getSession().close();

            restResponse = new RestResponse(objectList, HttpStatus.OK, HttpStatus.OK.value(), GlobalProperties.MESSAGE_SUCCESS);
            return new ResponseEntity<>(restResponse, HttpStatus.OK);
        } catch (Exception ex) {
            LogSLF4J.logException(this.getClass(), ex);
            restResponse = new RestResponse(LogSLF4J.getException(this.getClass(), ex), HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), GlobalProperties.MESSAGE_ERROR);

            return new ResponseEntity<>(restResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
