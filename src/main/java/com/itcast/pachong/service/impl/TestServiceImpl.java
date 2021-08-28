package com.itcast.pachong.service.impl;

import com.itcast.pachong.mapper.TestMapper;
import com.itcast.pachong.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {


    @Autowired
    private TestMapper testMapper;

    @Override
    public String selectDate() {
        return testMapper.queryDate();
    }
}
