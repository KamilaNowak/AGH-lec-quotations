package com.nowak.aghquotapi.controllers;

import com.nowak.aghquotapi.entities.Quotation;
import com.nowak.aghquotapi.service.QuotationService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class QuotationController {

    @Autowired
    private QuotationService quotationService;

    @GetMapping(value = "/quotations")
    public ResponseEntity<List<Quotation>> getAll() {
        List<Quotation> quotationList = quotationService.findAll();
        return new ResponseEntity<>(quotationList,HttpStatus.OK);

    }

    @GetMapping(value = "/quotations", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Quotation>> getSpecivicFaculty(@RequestParam(required = false) String f) {
        List<Quotation> quotations;
        try {
            quotations = quotationService.findByFaculty(f);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Quotation>>(quotations, HttpStatus.OK);
    }


    @GetMapping(value = "/quotations/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Quotation> getById(@PathVariable("id") int id) {
        Quotation quotation = null;
        try {
            quotation = quotationService.findById(id);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(quotation,HttpStatus.OK);
    }
}
