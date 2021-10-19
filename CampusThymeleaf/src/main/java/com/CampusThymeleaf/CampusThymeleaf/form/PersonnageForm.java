package com.CampusThymeleaf.CampusThymeleaf.form;

import com.CampusThymeleaf.CampusThymeleaf.model.Personnage;

public class PersonnageForm {
    
    private long id;
    private String name;
    private Personnage.Type type;

    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Personnage.Type getType ()
    {
        return type;
    }

    public void setType (Personnage.Type type)
    {
        this.type = type;
    }
}
