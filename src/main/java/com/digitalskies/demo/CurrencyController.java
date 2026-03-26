package com.digitalskies.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class CurrencyController {

    @Autowired
    private CurrencyConfiguration currencyConfiguration;

    @RequestMapping("/currency-configuration")
    CurrencyConfiguration getCourses(){

       return currencyConfiguration;

    }

}
