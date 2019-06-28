/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafp;

//Food item class
public class Food{
    //Food item variables
    private String foodName;
    private double price;
    
    //Food item constructor
    public Food(String fn, double p){
        foodName = fn;
        price = p;
    }
    
    //setter function for food name of food item
    public void setFoodName(String fn) {
        foodName = fn;
    }
    
    //setter function for price of food item
    public void setPrice(double p) {
        price = p;
    }
    
    //getter function for food name of food item
    public String getFoodName() {
        return foodName;
    }
    
    //getter function for price of food item
    public double getPrice() {
        return price;
    }
}
