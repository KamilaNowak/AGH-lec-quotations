package com.nowak.aghquotapi.repo;

import com.nowak.aghquotapi.entities.Quotation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuotationRepo extends CrudRepository<Quotation,Integer> {

    Quotation findById(int id);
    Quotation findByFaculty(String faculty);

    @Modifying
    @Query(value = "UPDATE User u SET u.token =?2 WHERE u.name=?1")
    public void updateToken(String name, String token);
}
