/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafp;

import java.io.File; 
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.InputMismatchException;
        
//main function
public class driver{
    //throws FileNotFoundException if file can't be found
    public static void main(String[] args) throws FileNotFoundException{
        //create Scanner object to take in user input
        Scanner sc = new Scanner(System.in);
        
        //create new file object
        File file = new File("/Users/elizabethgirlang/Downloads/javastock.txt");
        File file2 = new File("/Users/elizabethgirlang/Downloads/javastock2.txt");
        
        //create Scanner object for reading the file
        Scanner scn = new Scanner(file);
        Scanner scn2 = new Scanner(file2);
        int opt;
        
        //create Restaurant objects
        Restaurant rest1 = new Restaurant("Burger King");
        Restaurant rest2 = new Restaurant("Pizza Hut");
        
        //add the food item from the txt file to the menu
        while(scn.hasNext()){
            rest1.addToList(new Dish(scn.next() +" " + scn.next(), Double.parseDouble(scn.next()), Integer.parseInt(scn.next())));
        }
        while(scn2.hasNext()){
            rest2.addToList(new Dish(scn2.next() +" " + scn2.next(), Double.parseDouble(scn2.next()), Integer.parseInt(scn2.next())));
        }
        
        System.out.println("==============================");
        System.out.println("1. " + rest1.getRestName() + "\n"
                         + "2. " + rest2.getRestName() + "\n"
                         + "3. Exit");
        System.out.println("==============================");
        
        while(true){
            //error handling if input is not integer type
            try {
               opt = sc.nextInt();
            } 
            catch (InputMismatchException e){
                System.err.println("Input must be integer");
                sc.next();
                continue;
            }
            //run the program for rest1
            if(opt == 1){   
                rest1.run(rest1);
            }
            //run the program for rest2
            else if(opt == 2){
                rest2.run(rest2);
            }
            //exit the program
            else if(opt == 3){
                System.exit(0);
            }
            //if input is incorrect
            else{
                System.err.println("Invalid Input(1-3)");
            }
        }  
    }
}
