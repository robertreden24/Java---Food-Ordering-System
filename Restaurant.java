/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafp;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Restaurant implements Order{
    private final String restName;
    
    //ArrayList to store restaurant menu
    private final ArrayList<Dish> dishList = new ArrayList<>();
    
    //ArrayList to store user order
    private final ArrayList<Dish> dishOrder = new ArrayList<>();
    
    //constructor for Restaurant class
    public Restaurant(String rn) {
        restName = rn;
    }
    
    //getter function for restaurant name
    public String getRestName(){
        return restName;
    }

    //function to add dish item to restaurant menu
    public void addToList(Dish d){
        dishList.add(d);
    }
    
    //function to display all dishes in restaurant menu
    public void displayList(){
        System.out.println("===============================");
        for(int i = 0; i < dishList.size(); i++){
            System.out.println(String.format("%-1d%-2s%-17s$%-8f",(i+1),".", dishList.get(i).getFoodName(), dishList.get(i).getPrice()));
        }
        System.out.println("===============================");
    }
    
    //getter function for user order
    public ArrayList<Dish> getDishOrder(){
        return dishOrder;
    }
    
    //getter function for restaurant menu
    public ArrayList<Dish> getDishList(){
        return dishList;
    }
    
    //overriden function from order interface
    @Override
    //function to add an item to the order
    public void order(int po, int q){
        boolean found = false;
        //if dish is already found in order, add the quantity
        for(int i = 0; i<dishOrder.size(); i++){
            if(dishOrder.get(i)==dishList.get(po-1)){
                dishOrder.get(i).addQuantity(q);
                found = true;
            }
        }
        //else add the dish to order
        if(found == false){
            dishOrder.add(dishList.get(po-1));
            dishOrder.get(dishOrder.size()-1).setQuantity(q);
        }
    }
    
    //function to remove dish from order
    public void removeFromOrder(int po, int q){
        //reduce quantity of dish in order
        dishOrder.get(po-1).reduceQuantity(q);
       
        //if dish quantity is 0 remove it from order
        if(dishOrder.get(po-1).getQuantity()==0){
            dishOrder.remove(po-1);
        }
    }
    
    //function to display all dish in order
    public void displayOrder(){
        System.out.println("=================================");
        System.out.println("Order:");
        for(int i = 0; i < dishOrder.size(); i++){
            System.out.println(String.format("%-1d%-2s%-17s$%-8f%3d", (i+1),".", dishOrder.get(i).getFoodName(), dishOrder.get(i).getPrice(), dishOrder.get(i).getQuantity()));
        }
        System.out.println("=================================");
    }
    
    //billing function to display the bill and prompt user to pay
    public void billing(){
        //create Scanner to take in user input
        Scanner sc = new Scanner(System.in);
        //create DecimalFormat to round values
        DecimalFormat two = new DecimalFormat("0.00");
        double total, subtotal = 0, pay, change;
        //print the bill
        System.out.println("==========================================");
        for(int i = 0; i < dishOrder.size(); i++){
            total = dishOrder.get(i).getPrice() * dishOrder.get(i).getQuantity();
            subtotal += dishOrder.get(i).getPrice() * dishOrder.get(i).getQuantity();
            System.out.println(String.format("%-17s$%-8f%3d  $%8f", dishOrder.get(i).getFoodName(), dishOrder.get(i).getPrice(), dishOrder.get(i).getQuantity(), total));
        }
        System.out.println("==========================================");
        System.out.println(String.format("%32s%-8f", "$", subtotal));
        //loop for customer pay
        while(true){
            System.out.println("Enter amount: ");
            //user input for pay
            pay = sc.nextDouble();
            //double change is customer's pay - bill
            change = pay - subtotal;
            //if pay is less than bill
            if(change < 0){
                System.err.println("Amount Invalid");
            }
            //finish transaction
            else{
                System.out.println("Your change is $" + two.format(change));
                System.out.println("Thank you for shopping with us!!!");
                break;
            } 
        }
        
    }
    
    //function to run the food ordering system
    public void run(Restaurant rest){
        //create Scanner to take in user input
        Scanner sc = new Scanner(System.in);
        //main loop of the program
        while(true){
            //display the choices
            System.out.println("============================");
            System.out.println("1. Order Food\n"
                         + "2. Remove From Order\n"
                         + "3. View Order\n"
                         + "4. Finish Order\n"
                         + "5. Exit");
            System.out.println("============================");
            //user input for choice
            int opt2 = sc.nextInt();
            //if user choose (1)
            if(opt2 == 1){
                //display menu of the restaurant
                rest.displayList();
                int opt3, qty;
                while(true){
                   opt3 = sc.nextInt();
                   //if dish chosen not in menu
                    if(opt3 > (rest.getDishList().size()) || opt3 <= 0){
                        System.err.println("Invalid Input");
                    }
                    else{
                        //boolean to check if quantity is valid
                        boolean valid = false;
                        while(valid == false){
                            System.out.println("Input Quantity: ");
                            //user input for quantity of dish to be ordered
                            qty = sc.nextInt();
                            //if quantity is invalid(<=0)
                            if(qty<=0){
                                System.err.println("Invalid amount");
                            }
                            //add to order if quantity is valid(>0)
                            else{
                                rest.order(opt3, qty);
                                System.out.println(rest.getDishList().get(opt3-1).getFoodName() + " added to order");
                                valid = true;
                                break; 
                            } 
                        }
                    } 
                    break;
                }
            }
            //if user choose(2)
            else if(opt2 == 2){
                //if order is empty
                if(rest.getDishOrder().isEmpty()==true){
                    System.out.println("Order is Empty");
                }
                else{
                    //display order
                    rest.displayOrder();
                    int pos, q;
                    while(true){
                        //user input for position of dish to be removed
                        pos = sc.nextInt();
                        //if dish is not found in order
                        if(pos <= 0 || pos > rest.getDishOrder().size()){
                            System.err.println("Item not found");
                        }
                        else{
                            break;
                        }
                    }
                    while(true){
                        System.out.println("input quantity:");
                        //user input for quantity of dish to be removed
                        q = sc.nextInt();
                        //if quantity of dish to be removed is invalid(<=0 or > quantity in order)
                        if((q>rest.getDishOrder().get(pos-1).getQuantity()) || (q<=0)){
                            System.err.println("Invalid amount");
                        }
                        //remove dish from order
                        else{
                            System.out.println(rest.getDishOrder().get(pos-1).getFoodName() + " removed from order");
                            rest.removeFromOrder(pos, q);
                            break;
                        }
                    }
                }
            }
            //if user choose (3)
            else if(opt2 == 3){
                //display order
                rest.displayOrder();
            }
            //if user choose (4)
            else if(opt2 == 4){
                //if order is empty
                if(rest.getDishOrder().isEmpty()==true){
                    System.out.println("Order is Empty");
                }
                //execute the billing function
                else{
                    rest.billing();
                    //exit the program
                    System.exit(0);
                }
            }
            //if user choose (5)
            else if(opt2 == 5){
                //exit the program
                System.exit(0);
            }
            //if input is incorrect
            else{
                System.err.println("Incorrect Input(1-5)");
            }
        }
    }
}
