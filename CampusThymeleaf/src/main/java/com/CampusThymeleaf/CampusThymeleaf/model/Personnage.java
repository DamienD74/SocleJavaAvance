package com.CampusThymeleaf.CampusThymeleaf.model;

public class Personnage {

    private long id;
    private String name;
    public static enum Type {Guerrier, Magicien};
    private Type type;

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

    public Type getType ()
    {
        return type;
    }

    public void setType (Type type)
    {
        this.type = type;
    }
}
