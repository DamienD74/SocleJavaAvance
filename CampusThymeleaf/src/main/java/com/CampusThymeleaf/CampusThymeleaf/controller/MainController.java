package com.CampusThymeleaf.CampusThymeleaf.controller;

import java.util.List;
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
import org.springframework.web.client.RestTemplate;

@Controller
public class MainController {
    
    private static final AtomicLong counter = new AtomicLong();

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
    public Personnage personnage(@PathVariable final Integer id)
    {
        String url = "http://localhost:8081/personnages/{id}";
        RestTemplate restTemplate = new RestTemplate();
        List<Personnage> personnageList = restTemplate.getForObject(url, List.class);

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
    public String addPersonnage(Model model, 
    @ModelAttribute("personnageForm") PersonnageForm personnageForm)
    {
        String url = "http://localhost:8081/personnages";
        RestTemplate restTemplate = new RestTemplate();
        List<Personnage> personnageList = restTemplate.getForObject(url, List.class);

        Personnage personnage = new Personnage(counter.incrementAndGet(), personnageForm.getName(), personnageForm.getType());
        personnageList.add(personnage);

        restTemplate.postForObject(url, personnage, Personnage.class);

        return "redirect:/personnages";
    }
    
    @RequestMapping(value = "/personnages/{id}", method = RequestMethod.PUT)
    public void modifyPersonnage(@PathVariable final Integer id, @RequestBody Personnage personnage)
    {
        String url = "http://localhost:8081/personnages/{id}";
        RestTemplate restTemplate = new RestTemplate();
        List<Personnage> personnageList = restTemplate.getForObject(url, List.class);

        personnageList.set(id - 1, personnage);
    }

    @RequestMapping(value = "/personnages/{id}", method = RequestMethod.DELETE)
    public void modifyPersonnage(@PathVariable final Integer id)
    {
        String url = "http://localhost:8081/personnages/{id}";
        RestTemplate restTemplate = new RestTemplate();
        List<Personnage> personnageList = restTemplate.getForObject(url, List.class);

        personnageList.remove((int)id - 1);
    }
}