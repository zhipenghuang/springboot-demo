package com.hzp.demo.service.impl;

import com.hzp.demo.entity.User;
import com.hzp.demo.reponsitory.UserReponsitory;
import com.hzp.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    UserReponsitory userReponsitory;

    @Override
    public String test() {
        User hzp = userReponsitory.selectById("hzp");

        return hzp.getName();
    }
}
