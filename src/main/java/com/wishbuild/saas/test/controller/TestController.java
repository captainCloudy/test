package com.wishbuild.saas.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wishbuild.saas.test.respository.mapper.TestMapper;
import com.wishbuild.saas.test.respository.po.Test;

@RestController
public class TestController {
	@Autowired
    private TestMapper testMapper;
	
    @RequestMapping("/testTest")
    public List<Test> testTest() {
        return testMapper.selectAll();
    }
}
