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
        Scanner sc = new Scanner(System.in);
        DecimalFormat two = new DecimalFormat("0.00");
        double total, subtotal = 0, pay, change;
        System.out.println("==========================================");
        for(int i = 0; i < dishOrder.size(); i++){
            total = dishOrder.get(i).getPrice() * dishOrder.get(i).getQuantity();
            subtotal += dishOrder.get(i).getPrice() * dishOrder.get(i).getQuantity();
            System.out.println(String.format("%-17s$%-8f%3d  $%8f", dishOrder.get(i).getFoodName(), dishOrder.get(i).getPrice(), dishOrder.get(i).getQuantity(), total));
        }
        System.out.println("==========================================");
        System.out.println(String.format("%32s%-8f", "$", subtotal));
        while(true){
            System.out.println("Enter amount: ");
            pay = sc.nextDouble();
            change = pay - subtotal;
            if(change < 0){
                System.err.println("Amount Invalid");
            }
            else{
                System.out.println("Your change is $" + two.format(change));
                System.out.println("Thank you for shopping with us!!!");
                break;
            } 
        }
        
    }
    
    //function to run the food ordering system
    public void run(Restaurant rest){
        Scanner sc = new Scanner(System.in);
        while(true){
            System.out.println("============================");
            System.out.println("1. Order Food\n"
                         + "2. Remove From Order\n"
                         + "3. View Order\n"
                         + "4. Finish Order\n"
                         + "5. Exit");
            System.out.println("============================");
            int opt2 = sc.nextInt();
            if(opt2 == 1){
                rest.displayList();
                int opt3, qty;
                while(true){
                   opt3 = sc.nextInt();
                    if(opt3 > (rest.getDishList().size()) || opt3 <= 0){
                        System.err.println("Invalid Input");
                    }
                    else{
                        boolean valid = false;
                        while(valid == false){
                            System.out.println("Input Quantity: ");
                            qty = sc.nextInt();
                            if(qty<=0){
                                System.err.println("Invalid amount");
                            }
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
            else if(opt2 == 2){
                if(rest.getDishOrder().isEmpty()==true){
                    System.out.println("Order is Empty");
                }
                else{
                    rest.displayOrder();
                    int pos, q;
                    while(true){
                        pos = sc.nextInt();
                        if(pos <= 0 || pos > rest.getDishOrder().size()){
                            System.err.println("Item not found");
                        }
                        else{
                            break;
                        }
                    }
                    while(true){
                        System.out.println("input quantity:");
                        q = sc.nextInt();
                        if((q>rest.getDishOrder().get(pos-1).getQuantity()) || (q<=0)){
                            System.err.println("Invalid amount");
                        }
                        else{
                            System.out.println(rest.getDishOrder().get(pos-1).getFoodName() + " removed from order");
                            rest.removeFromOrder(pos, q);
                            break;
                        }
                    }
                }
            }

            else if(opt2 == 3){
                rest.displayOrder();
            }

            else if(opt2 == 4){
                if(rest.getDishOrder().isEmpty()==true){
                    System.out.println("Order is Empty");
                }
                else{
                    rest.billing();
                    System.exit(0);
                }
            }

            else if(opt2 == 5){
                System.exit(0);
            }

            else{
                System.err.println("Incorrect Input");
            }
        }
    }
}
