package managers;

import beans.Order;
import beans.Restaurant;

import java.util.ArrayList;
public class OrderManager {
    static ArrayList<Order> orders= Restaurant.orders;
    static ArrayList<Order> invoices= Restaurant.invoices;
    
    public static void addOrder(Order order){
        orders.add(order);
    }

    public static void removeOrder(Order order){
        boolean removeSuccessful= false;
        for(Order o: orders){
            if(o.getName().equals(order.getName())){
                removeSuccessful= true;
                orders.remove(o);
                break;
            }
        }
        if(!removeSuccessful){
            System.out.println("Remove Order Failure");
        }
    }

    public static void orderInvoiced(Order order){
        order.setInvoiced(true);
        order.getTable().isOccupied= false;
    }

    
}
