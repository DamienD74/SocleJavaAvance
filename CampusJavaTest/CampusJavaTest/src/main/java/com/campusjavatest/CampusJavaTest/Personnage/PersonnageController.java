package com.campusjavatest.CampusJavaTest.Personnage;

import java.util.Optional;

import com.campusjavatest.CampusJavaTest.repository.PersonnageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class PersonnageController {
    
    @Autowired 
    private PersonnageRepository personnageRepository;

    @Operation(summary = "Affichage de tout les personnages")
    @GetMapping("/personnages")
    public Iterable<Personnage> personnages ()
    {        
        System.out.println(personnageRepository.findAll());
        return personnageRepository.findAll();
    }

    @Operation(summary = "Affichage d'un personnage via un id")
    @RequestMapping(value = "/personnages/{id}", method = RequestMethod.GET)
    public Optional<Personnage> personnage(@PathVariable final Long id)
    {
        Optional <Personnage> personnage = personnageRepository.findById(id);
        System.out.println(personnage);

        return personnage;
    }

    @Operation(summary = "Ajout d'un personnage")
    @RequestMapping(value = "/personnages", method = RequestMethod.POST)
    public void addPersonnage(@RequestBody Personnage personnage)
    {
        personnageRepository.save(personnage);
    }

    @Operation(summary = "Modification d'un personnage via un id")
    @RequestMapping(value = "/personnages", method = RequestMethod.PUT)
    public void modifyPersonnage(@RequestBody Personnage personnage)
    {
        System.out.println(personnage.getId());
        Personnage personnageTable = personnageRepository.findById(personnage.getId()).get();
        personnageTable.setName(personnage.getName());
        personnageTable.setType(personnage.getType());
        personnageRepository.save(personnageTable);
    }

    @Operation(summary = "Suppression d'un personnage via un id")
    @RequestMapping(value = "/personnages/{id}", method = RequestMethod.DELETE)
    public void deletePersonnage(@PathVariable final Long id)
    {
        personnageRepository.deleteById(id);
    }
}
