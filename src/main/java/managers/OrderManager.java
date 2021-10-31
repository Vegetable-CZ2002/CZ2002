package managers;

import beans.MenuItem;
import beans.Order;
import beans.Restaurant;
import beans.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderManager {
    
    public static void addOrder(Order order){
        Restaurant.orders.add(order);
    }

    public static void removeOrder(int id){
        boolean removeSuccessful= false;
        for(Order o: Restaurant.orders){
            if(o.getId()== id){
                removeSuccessful= true;
                Restaurant.orders.remove(o);
                break;
            }
        }
        if(!removeSuccessful){
            System.out.println("Remove Order Failure");
        }
    }

    public static void orderInvoiced(int id){
        boolean orderInvoiced= false;
        for(Order order: Restaurant.orders){
            if(order.getId()== id){
                orderInvoiced= true;
                order.setInvoiced(true);
                order.getTable().setOccupied(false);
                Restaurant.invoices.add(order);
                Restaurant.orders.remove(order);
                System.out.println("Order successfully invoiced");
                break;
            }
        }
        if(!orderInvoiced){
            System.out.println("Invoice order failure");
        }
    }

    public static void printOrder(){
        if(Restaurant.orders.size()== 0){
            System.out.println("No order in history yet");
        }
        else{
            for(Order item: Restaurant.orders){
                System.out.printf(item.toString()+"\n");
            }
        }
    }

    public static void printInvoice(){
        for(Order item: Restaurant.invoices){
            System.out.printf(item.toString()+"\n");
        }
    }


}
