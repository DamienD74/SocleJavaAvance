package com.campusjavatest.CampusJavaTest.repository;

import com.campusjavatest.CampusJavaTest.Personnage.Personnage;

import org.springframework.data.repository.CrudRepository;

public interface PersonnageRepository extends CrudRepository<Personnage, Long>{
    
}
