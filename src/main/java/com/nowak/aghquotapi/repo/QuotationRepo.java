package com.nowak.aghquotapi.repo;

import com.nowak.aghquotapi.entities.Quotation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotationRepo extends CrudRepository<Quotation,Integer> {

    Quotation findById(int id);
    Quotation findByFaculty(String faculty);
}
