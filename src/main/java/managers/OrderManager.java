package managers;

import adapters.MenuItemAdapter;
import beans.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class OrderManager {
    public static List<Order> invoices;
    
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

    public static void orderInvoiced(int id) throws IOException {
        boolean orderInvoiced= false;
        for(Order order: Restaurant.orders){
            if(order.getId()== id){
                orderInvoiced= true;
                order.setInvoiced(true);
                order.getTable().setOccupied(false);
                Restaurant.orders.remove(order);
                // TODO: 2021/10/31 add invoice to json
                addInvoice(order);
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

    public static void printInvoice() throws IOException {
        List<Order> orders= readInvoice();
        for(Order item: orders){
            System.out.printf(item.toString()+"\n");
        }
    }

    public static List<Order> readInvoice() throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(MenuItem.class, new MenuItemAdapter());
        Gson gson = builder.setPrettyPrinting().create();
        Path file = Path.of("src/main/resources/data/invoice.json");
        String jsonString = Files.readString(file);
        Order[] orderArray = gson.fromJson(jsonString, Order[].class);
        if(orderArray == null){
            invoices = new ArrayList<Order>();
        }
        else{
            invoices = new ArrayList<>(Arrays.asList(orderArray));
        }
        return invoices;
    }

    public static void addInvoice(Order i) throws IOException{
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(MenuItem.class, new MenuItemAdapter());
        Gson gson = builder.setPrettyPrinting().create();
        try {
            invoices.add(i);
            Order[] staffArray = new Order[invoices.size()];
            invoices.toArray(staffArray);
            Path file = Path.of("src/main/resources/data/invoice.json");
            Files.writeString(file, gson.toJson(staffArray), StandardOpenOption.WRITE);
        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
