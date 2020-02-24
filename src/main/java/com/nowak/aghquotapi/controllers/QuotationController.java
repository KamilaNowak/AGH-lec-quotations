package com.nowak.aghquotapi.controllers;

import com.nowak.aghquotapi.entities.Quotation;
import com.nowak.aghquotapi.repo.QuotationRepo;
import com.nowak.aghquotapi.service.QuotationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api")
public class QuotationController {

    @Autowired
    private  QuotationService quotationService;


    @GetMapping("/all")
    public ResponseEntity<?> getAll(){

        List<Quotation> quotationList = quotationService.findAll();
        return  new ResponseEntity<>(quotationList,HttpStatus.OK);
    }



}
