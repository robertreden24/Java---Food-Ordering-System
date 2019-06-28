/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafp;

//Dish class inherits Food class
public class Dish extends Food{
    private int quantity;
    
    //Dish constructor
    public Dish(String fn, double p, int q) {
        super(fn, p);
        quantity = q;
    }
    
    //getter function for quantity of food item
    public int getQuantity() {
        return quantity;
    }
    
    //setter function for quantity of food item
    public void setQuantity(int q) {
        quantity = q;
    }
    
    //function to add quantity of food item
    public void addQuantity(int q){
        quantity += q;
    }
    
    //function to reduce quantity of food item
    public void reduceQuantity(int q){
        quantity -= q;
    }
}
