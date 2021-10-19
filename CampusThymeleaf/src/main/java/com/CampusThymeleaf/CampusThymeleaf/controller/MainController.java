package com.CampusThymeleaf.CampusThymeleaf.controller;

import java.util.List;
import java.util.ArrayList; 
import java.util.concurrent.atomic.AtomicLong;

import com.CampusThymeleaf.CampusThymeleaf.form.PersonnageForm;
import com.CampusThymeleaf.CampusThymeleaf.model.Personnage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
    
    private static final AtomicLong counter = new AtomicLong();
    public static List<Personnage> personnageList = new ArrayList<Personnage>();

    static {
        personnageList.add(new Personnage(counter.incrementAndGet(), "Rubert", Personnage.Type.Magicien));
        personnageList.add(new Personnage(counter.incrementAndGet(), "Prout", Personnage.Type.Guerrier));
        personnageList.add(new Personnage(counter.incrementAndGet(), "Zidane", Personnage.Type.Guerrier));
        personnageList.add(new Personnage(counter.incrementAndGet(), "Salomon", Personnage.Type.Magicien));
    }

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(Model model) {

		return "index";
	}

    @RequestMapping(value = {"/personnages"}, method = RequestMethod.GET)
    public String personnages (Model model)
    {           
        model.addAttribute("personnages",personnageList);

        return "personnages";
    }

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

    @RequestMapping(value = "/ajouterPersonnage", method = RequestMethod.GET)
    public String showAddPersonnage(Model model)
    {
        PersonnageForm personnageForm = new PersonnageForm();
        model.addAttribute("personnageForm", personnageForm);
        return "ajouterPersonnage";
    }

    @RequestMapping(value = "/ajouterPersonnage", method = RequestMethod.POST)
    public void addPersonnage(Model model, 
    @ModelAttribute("personnageForm") PersonnageForm personnageForm)
    {
        personnageList.add(new Personnage(counter.incrementAndGet(), personnageForm.getName(), personnageForm.getType()));
    }
    
    @RequestMapping(value = "/personnages/{id}", method = RequestMethod.PUT)
    public void modifyPersonnage(@PathVariable final Integer id, @RequestBody Personnage personnage)
    {
        personnageList.set(id - 1, personnage);
    }

    @RequestMapping(value = "/personnages/{id}", method = RequestMethod.DELETE)
    public void modifyPersonnage(@PathVariable final Integer id)
    {
        personnageList.remove((int)id - 1);
    }
}