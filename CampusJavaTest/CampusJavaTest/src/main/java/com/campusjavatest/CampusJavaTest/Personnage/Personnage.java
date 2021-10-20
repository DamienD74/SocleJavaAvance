package com.campusjavatest.CampusJavaTest.Personnage;

public class Personnage {
    
    private long id;
    private String name;
    public static enum Type {Guerrier, Magicien};
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

    public Type getType ()
    {
        return type;
    }
}
