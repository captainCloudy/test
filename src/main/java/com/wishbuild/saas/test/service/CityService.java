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
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.wishbuild.saas.test.cache.service.RedisService;
import com.wishbuild.saas.test.respository.mapper.CityMapper;
import com.wishbuild.saas.test.respository.po.City;

/**
 * @author xujun
 * @since 2015-12-19 11:09
 */
@Service
public class CityService {

    @Autowired
    private CityMapper cityMapper;
    
    @Autowired 
    private RedisService redisService; 

    public List<City> getAll(City city) {
        if (city.getPage() != null && city.getRows() != null) {
            PageHelper.startPage(city.getPage(), city.getRows());
        }
        return cityMapper.selectAll();
    }

    public City getById(Integer id) {
    	String key = "city_" + id; 
        // 缓存存在 
        boolean hasKey = redisService.hasKey(key); 
        if (hasKey) { 
            City city = redisService.get(key,City.class); 
            System.out.println("CityServiceImpl.findCityById() : 从缓存中获取了城市 >> " + city.toString()); 
            return city; 
        }
        // 从 DB 中获取城市信息 
        City city = cityMapper.selectByPrimaryKey(id); 
  
        // 插入缓存 
        redisService.set(key, city, 10L, TimeUnit.SECONDS); 
        System.out.println("CityServiceImpl.findCityById() : 城市插入缓存 >> " + city.toString()); 
        return city; 
    }

    public void deleteById(Integer id) {
    	String key = "city_" + id; 
        boolean hasKey = redisService.hasKey(key); 
        if (hasKey) { 
        	redisService.delete(key); 
            System.out.println("CityServiceImpl.updateCity() : 从缓存中删除城市 ID >> " + key); 
        } 
        cityMapper.deleteByPrimaryKey(id);
    }

    public void save(City city) {
        if (city.getId() != null) {
        	 String key = "city_" + city.getId(); 
             boolean hasKey = redisService.hasKey(key); 
             if (hasKey) { 
            	 redisService.delete(key); 
                 System.out.println("CityServiceImpl.updateCity() : 从缓存中删除城市 >> " + city.toString()); 
             } 
            cityMapper.updateByPrimaryKey(city);
        } else {
            cityMapper.insert(city);
        }
    }
    
}
