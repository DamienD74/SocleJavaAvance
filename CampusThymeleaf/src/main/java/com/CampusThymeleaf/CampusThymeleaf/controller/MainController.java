package com.CampusThymeleaf.CampusThymeleaf.controller;

import java.util.List;

import com.CampusThymeleaf.CampusThymeleaf.form.PersonnageForm;
import com.CampusThymeleaf.CampusThymeleaf.model.Personnage;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

@Controller
public class MainController {

    @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(Model model) {

		return "index";
	}

    @RequestMapping(value = {"/personnages"}, method = RequestMethod.GET)
    public String personnages (Model model)
    {   
        String url = "http://localhost:8081/personnages";
        RestTemplate restTemplate = new RestTemplate();
        List<Personnage> personnageList = restTemplate.getForObject(url, List.class);
        model.addAttribute("personnages",personnageList);

        return "personnages";
    }

    @RequestMapping(value = "/personnages/{id}", method = RequestMethod.GET)
    public String personnage(Model model, @PathVariable final Long id)
    {
        String url = "http://localhost:8081/personnages/"+ id +"";
        RestTemplate restTemplate = new RestTemplate();
        Personnage personnage = restTemplate.getForObject(url, Personnage.class);
        model.addAttribute("personnage",personnage);

        return "detailPersonnage";
    }

    @RequestMapping(value = "/ajouterPersonnage", method = RequestMethod.GET)
    public String showAddPersonnage(Model model)
    {
        PersonnageForm personnageForm = new PersonnageForm();
        model.addAttribute("personnageForm", personnageForm);
        
        return "ajouterPersonnage";
    }

    @RequestMapping(value = "/ajouterPersonnage", method = RequestMethod.POST)
    public String addPersonnage(Model model, 
    @ModelAttribute("personnageForm") PersonnageForm personnageForm)
    {
        String url = "http://localhost:8081/personnages";
        RestTemplate restTemplate = new RestTemplate();
        List<Personnage> personnageList = restTemplate.getForObject(url, List.class);

        Personnage personnage = new Personnage(personnageForm.getId(), personnageForm.getName(), personnageForm.getType());
        personnageList.add(personnage);

        restTemplate.postForObject(url, personnage, Personnage.class);

        return "redirect:/personnages";
    }
    
    @RequestMapping(value = "/modifierPersonnages/{id}", method = RequestMethod.GET)
    public String showModifyPersonnage(Model model, @PathVariable final Long id)
    {
        String url = "http://localhost:8081/personnages/"+ id +"";
        RestTemplate restTemplate = new RestTemplate();
        Personnage personnage = restTemplate.getForObject(url, Personnage.class);

        PersonnageForm personnageForm = new PersonnageForm(personnage.getId(), personnage.getName(), personnage.getType());
        model.addAttribute("personnageForm", personnageForm);
        
        return "modifierPersonnage";
    }

    @RequestMapping(value = "/modifierPersonnages", method = RequestMethod.POST)
    public String modifyPersonnage(Model modem,
    @ModelAttribute("personnageForm") PersonnageForm personnageForm)
    {
        String url = "http://localhost:8081/personnages";
        RestTemplate restTemplate = new RestTemplate();

        Personnage personnage = new Personnage(personnageForm.getId(), personnageForm.getName(), personnageForm.getType());

        restTemplate.put(url, personnage, Personnage.class);

        return "redirect:/personnages";
    }

    @RequestMapping(value = "/supprimerPersonnages/{id}", method = RequestMethod.GET)
    public String deletePersonnage(Model model, @PathVariable final Long id)
    {
        String url = "http://localhost:8081/personnages/" + id + "";
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(url);

        return "redirect:/personnages";
    }
}