package com.phatye.mobilelearn;

import com.google.appengine.api.datastore.Key;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class Item
{
    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key key;
    @Persistent
    private String title;
    @Persistent
    private String type;
    @Persistent
    private String category;    
    @Persistent
    private String fileExtension;
    @Persistent
    private String imgKey;
    @Persistent
    private String servingUrl;
    @Persistent
    private String fileSize;

    public Item(String title, String category, String fileExtension, String imgKey, String type,String fileSize)
    {
        this.title = title;
        this.category = category;
        this.fileExtension = fileExtension;
        this.imgKey = imgKey;
        this.type = type;
        this.fileSize = fileSize;
    }
    public Key getKey() 
    {
        return key;
    }
    public String getImageKey()
    {
     return imgKey;
    }
    public String getTitle() 
    {
        return title;
    }
    public String getType() 
    {
        return type;
    }
    public String getCategory() 
    {
        return category;
    }    
    public String getFileExtension() 
    {
        return fileExtension;
    }   
    public String getServingUrl() 
    {
        return servingUrl;
    }  
    public String getFileSize() 
    {
        return fileSize;
    }
    public void setImgageKey(String k)
    {
     this.imgKey = k;
    }
    public void setTitle(String t)
    {
     this.title = t;
    } 
    public void setType(String t)
    {
     this.type = t;
    }    
    public void setCategory(String d)
    {
     this.category = d;
    }
    public void setFileExtension(String p)
    {
     this.fileExtension = p;
    }    
    public void setServingUrl(String s)
    {
     this.servingUrl = s;
    }      
    public void setFileSize(String f)
    {
     this.fileSize = f;
    }    
}