package com.itcast.pachong.controller;


import com.itcast.pachong.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private TestService testService;


    @RequestMapping("queryDate")
    public String queryDate(){
        return testService.selectDate();
    }

}
