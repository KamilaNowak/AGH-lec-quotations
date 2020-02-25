package com.nowak.aghquotapi.repo;

import com.nowak.aghquotapi.entities.Quotation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "quotations",path = "quotations")
public interface QuotationRepo extends JpaRepository<Quotation,Integer> {

    Quotation findById(int id);
    List<Quotation> findByFaculty(String faculty);

    @Modifying
    @Query(value = "UPDATE User u SET u.token =?2 WHERE u.name=?1")
    public void updateToken(String name, String token);
}
