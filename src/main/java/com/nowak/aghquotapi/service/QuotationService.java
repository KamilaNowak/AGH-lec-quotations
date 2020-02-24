package com.nowak.aghquotapi.service;

import com.nowak.aghquotapi.entities.Quotation;
import com.nowak.aghquotapi.repo.QuotationRepo;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Service
public class QuotationService {

    private final QuotationRepo quotationRepo;

    public QuotationService(QuotationRepo quotationRepo) {
        this.quotationRepo = quotationRepo;
    }

    public List<Quotation> findAll(){
        return (List<Quotation>) quotationRepo.findAll();
    }
    Quotation findById(int id) throws NotFoundException {
        Quotation quotation= null;
        Optional<Quotation>  quotationOptional = Optional.ofNullable(quotationRepo.findById(id));
        if(quotationOptional.isPresent()){
            quotation = quotationOptional.get();
        }
        else{
            throw new NotFoundException("Quotation not found");
        }
        return quotation;
    }
    Quotation findByFaculty(String faculty) throws NotFoundException {
        Quotation quotation= null;
        Optional<Quotation>  quotationOptional = Optional.ofNullable(quotationRepo.findByFaculty(faculty));
        if(quotationOptional.isPresent()){
            quotation = quotationOptional.get();
        }
        else{
            throw new NotFoundException("Quotation not found");
        }
        return quotation;

    }
}
