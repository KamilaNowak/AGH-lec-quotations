package com.nowak.aghquotapi.repo;

import com.nowak.aghquotapi.entities.Authority;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepo extends CrudRepository<Authority,Integer> {
    Authority findByAuthority(String name);
}
