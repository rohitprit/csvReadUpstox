package com.demo.csvread.resource;

import com.demo.csvread.service.MethodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/demo")
public class Methods {

    @Autowired
    MethodsService methodsService;

    //All methods here
    public ResponseEntity<?> add_csv(@RequestParam("file") MultipartFile file) throws IOException {
        return methodsService.add_csv(file);
    }

}
