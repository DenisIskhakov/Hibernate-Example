package com.example.hibernateexampleproject.controller;

import com.example.hibernateexampleproject.model.Phone;
import com.example.hibernateexampleproject.service.PhoneService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/phone")
@AllArgsConstructor
public class PhoneController {
    private PhoneService phoneService;
    @GetMapping
    public List<Phone> getAllPhone(){
        return phoneService.getAllPhone();
    }
    @GetMapping("/{id}")
    public Phone getByIdPhone(@PathVariable Long id){
        return phoneService.getByIdPhone(id);
    }
}
