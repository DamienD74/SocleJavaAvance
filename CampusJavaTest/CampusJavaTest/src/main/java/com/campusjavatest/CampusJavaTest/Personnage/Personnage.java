package com.campusjavatest.CampusJavaTest.Personnage;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "Personnages")
public class Personnage {
    
    @Id
    @GeneratedValue
    private long id;

    @Column
    private String name;
    public static enum Type {Guerrier, Magicien};
    @Column
    private Type type;

    public Personnage()
    {

    }

    public Personnage (Long id, String name, Type type)
    {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Type getType ()
    {
        return type;
    }

    public void setType (Type type)
    {
        this.type = type;
    }

    public void setTraductionType()
    {

    }
}
