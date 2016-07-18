package com.phatye.mobilelearn;


public class Option implements Comparable<Option>{
    private String name;
    private String data;
    private String path;
    private String size;
    
    public Option(String n,String d,String p)
    {
        name = n;
        data = d;
        path = p;
    }
    public Option(String n,String d,String p, long s)
    {
        name = n;
        data = d;
        path = p;
        size = Long.toString(s);
    }
    public String getName()
    {
        return name;
    }
    public String getData()
    {
        return data;
    }
    public String getPath()
    {
        return path;
    }
    public String getSize()
    {
        return size;
    }
    @Override
    public int compareTo(Option o) {
        if(this.name != null)
            return this.name.toLowerCase().compareTo(o.getName().toLowerCase()); 
        else 
            throw new IllegalArgumentException();
    }
}

