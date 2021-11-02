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
    public static List<Order> orders= new ArrayList<>();
    
    public static void addOrder(Order order){
        orders.add(order);
    }

    public static void removeOrder(int id){
        boolean removeSuccessful= false;
        for(Order o: orders){
            if(o.getId()== id){
                removeSuccessful= true;
                orders.remove(o);
                break;
            }
        }
        if(!removeSuccessful){
            System.out.println("Remove Order Failure");
        }
    }

    public static void orderInvoiced(int id) throws IOException {
        boolean orderInvoiced= false;
        for(Order order: orders){
            if(order.getId()== id){
                orderInvoiced= true;
                order.setInvoiced(true);
                order.getTable().setOccupied(false);
                orders.remove(order);
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
        if(orders.size()== 0){
            System.out.println("No order in history yet");
        }
        else{
            for(Order item: orders){
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
        invoices= readInvoice();
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
