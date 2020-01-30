package com.hzp.demo.service.impl;

import com.hzp.demo.entity.User;
import com.hzp.demo.repository.UserRepository;
import com.hzp.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    UserRepository userRepository;

    @Override
    public String test() {
        User hzp = userRepository.selectById("hzp");

        return hzp.getName();
    }
}
