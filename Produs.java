/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Cofetarie;

/**
 *
 * @author Bugs Bunny
 */
public class Produs {
    private int id;
    private String name;
    private float price;
    private float greutate;
    private byte[] picture;
    
     
    public Produs(int pid, String pname, float pprice,float pgreutate, byte[] pimg)
    {
       this.id=pid;
       this.name=pname;
       this.price=pprice;
       this.greutate=pgreutate;
       this.picture=pimg;
       
    }
    public int getId()
            {
                return id;
            }
    public String getName()
    {
        return name;
    }
    public float getPrice()
    {
        return price;
    }
   public float getGreutate()
    {
        return greutate;
    }       


    public byte[] getImage()
    {
        return picture;
    }
            
 }   