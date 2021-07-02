package com.demo.csvread.data;


import com.demo.csvread.domain.User;
import lombok.Data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Data
public class DataClass {
    Map<String, User> userMap;
    Set<String> managerSet;

    public DataClass(){
        userMap=new HashMap<>();
        managerSet= new HashSet<>();
    }

}
