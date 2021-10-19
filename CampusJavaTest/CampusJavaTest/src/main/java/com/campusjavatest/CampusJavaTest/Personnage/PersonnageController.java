package com.campusjavatest.CampusJavaTest.Personnage;

import java.util.List;
import java.util.ArrayList; 
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class PersonnageController {
    
    private static final AtomicLong counter = new AtomicLong();
    public static List<Personnage> personnageList = new ArrayList<Personnage>();

    static {
        personnageList.add(new Personnage(counter.incrementAndGet(), "Rubert", Personnage.Type.Magicien));
        personnageList.add(new Personnage(counter.incrementAndGet(), "Prout", Personnage.Type.Guerrier));
        personnageList.add(new Personnage(counter.incrementAndGet(), "Zidane", Personnage.Type.Guerrier));
        personnageList.add(new Personnage(counter.incrementAndGet(), "Salomon", Personnage.Type.Magicien));
    }

    @Operation(summary = "Affichage de tout les personnages")
    @GetMapping("/personnages")
    public List<Personnage> personnages ()
    {        
        return personnageList;
    }

    @Operation(summary = "Affichage d'un personnage via un id")
    @RequestMapping(value = "/personnages/{id}", method = RequestMethod.GET)
    public Personnage personnage(@PathVariable final Integer id)
    {
        if (id < personnageList.size())
        {
            return personnageList.get(id - 1);
        }
        else
        {
            return personnageList.get(personnageList.size() - 1);
        }
    }

    @Operation(summary = "Ajout d'un personnage")
    @RequestMapping(value = "/personnages", method = RequestMethod.POST)
    public void addPersonnage(@RequestBody Personnage personnage)
    {
        personnageList.add(personnage);
    }

    @Operation(summary = "Modification d'un personnage via un id")
    @RequestMapping(value = "/personnages/{id}", method = RequestMethod.PUT)
    public void modifyPersonnage(@PathVariable final Integer id, @RequestBody Personnage personnage)
    {
        personnageList.set(id - 1, personnage);
    }

    @Operation(summary = "Suppression d'un personnage via un id")
    @RequestMapping(value = "/personnages/{id}", method = RequestMethod.DELETE)
    public void modifyPersonnage(@PathVariable final Integer id)
    {
        personnageList.remove((int)id - 1);
    }
}
