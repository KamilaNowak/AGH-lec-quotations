package com.nowak.aghquotapi.controllers;

import com.nowak.aghquotapi.entities.Quotation;
import com.nowak.aghquotapi.repo.QuotationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class QuotationController {

    private final QuotationRepo quotationRepo;

    public QuotationController(QuotationRepo quotationRepo) {
        this.quotationRepo = quotationRepo;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        List<Quotation> quotationList = (List<Quotation>) quotationRepo.findAll();
        return  new ResponseEntity<>(quotationList,HttpStatus.OK);
    }



}
