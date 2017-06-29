/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2016 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.wishbuild.saas.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.wishbuild.saas.test.respository.mapper.TestMapper;
import com.wishbuild.saas.test.respository.po.City;
import com.wishbuild.saas.test.respository.po.Test;

/**
 * @author xujun
 * @since 2015-12-19 11:09
 */
@Service
public class TestService {

    @Autowired
    private TestMapper testMapper;

    public List<Test> getAll(Test test) {
        if (test.getPage() != null && test.getRows() != null) {
            PageHelper.startPage(test.getPage(), test.getRows());
        }
        return testMapper.selectAll();
    }

    public Test getById(Integer id) {
        return testMapper.selectByPrimaryKey(id);
    }

    public void deleteById(Integer id) {
    	testMapper.deleteByPrimaryKey(id);
    }

    public void save(Test test) {
        if (test.getId() != null) {
        	testMapper.updateByPrimaryKey(test);
        } else {
        	testMapper.insert(test);
        }
    }
}
