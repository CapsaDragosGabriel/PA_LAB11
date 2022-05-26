package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
@RestController
@RequestMapping("/myapp")
public class RestControllerDemo {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/user")
    public List<Object> getUsers(){
String url="http://localhost:8081/api/v1/user/";
Object[] objects= restTemplate.getForObject(url,Object[].class);
        assert objects != null;
        return Arrays.asList(   objects);
    }
    @GetMapping("/user/{id}")
    public List<Object> getUser(@PathVariable("id") Long id ){
        String url="http://localhost:8081/api/v1/user/"+id.toString();
        Object[] objects= restTemplate.getForObject(url,Object[].class);
        assert objects != null;
        return Arrays.asList(   objects);
    }
    @GetMapping("/top/{no}")
    public List<Object> getMostPopular(@PathVariable("no") Long no){
        String url="http://localhost:8081/api/v1/user/top/"+no.toString();
        Object[] objects= restTemplate.getForObject(url,Object[].class);
        assert objects != null;
        return Arrays.asList(   objects);
    }

    @GetMapping("{id}/friends")
    public List<Object> getFriends(@PathVariable("id") Long id){
        String url="http://localhost:8081/api/v1/user/"+id.toString()+"/friends";
        Object[] objects= restTemplate.getForObject(url,Object[].class);
        assert objects != null;
        return Arrays.asList(objects);
    }


}
