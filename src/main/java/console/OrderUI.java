package console;

import beans.*;
import managers.MenuManager;
import managers.OrderManager;
import managers.StaffManager;
import managers.TableManager;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderUI {
    private static Scanner in = MainUI.in;
    public static void mainUI() throws IOException{
        int num= 0;
        do{
            System.out.println("Welcome to the order section! What action do you wish to take?");
            System.out.println("[0] Return to main page");
            System.out.println("[1] View order");
            System.out.println("[2] Create order");
            System.out.println("[3] Delete order");
            System.out.println("[4] Add order item to order");
            System.out.println("[5] Delete order item from order");
            System.out.println("[6] Print order invoice");
            System.out.println("[7] Invoice order");
            System.out.println("[8] Print sale revenue report by period");
            num= in.nextInt();
            switch (num){
                case 1 :
                    printOrder();
                    break;
                case 2 :
                    createOrder();
                    break;
                case 4 :
                    addItemToOrder();
                    break;
                case 5 :
                    deleteItemFromOrder();
                    break;
                case 6 :
                    printInvoice();
                    break;
                case 7:
                    invoiceOrder();
                    break;
                case 3:
                    deleteOrder();
                    break;
                case 8:
                    printSale();
                    break;
                case 0 :
                    break;
                default : break;
            }
        } while(num!= 0);
    }

    public static void main(String[] args) throws IOException {
        int num= 0;
        do{
            System.out.println("Welcome to the order section! What action do you wish to take?");
            System.out.println("[0] Return to main page");
            System.out.println("[1] View order");
            System.out.println("[2] Create order");
            System.out.println("[3] Delete order");
            System.out.println("[4] Add order item to order");
            System.out.println("[5] Delete order item from order");
            System.out.println("[6] Print order invoice");
            System.out.println("[7] Invoice order");
            num= in.nextInt();
            switch (num){
                case 1 :
                    printOrder();
                    break;
                case 2 :
                    createOrder();
                    break;
                case 4 :
                    addItemToOrder();
                    break;
                case 5 :
                    deleteItemFromOrder();
                    break;
                case 6 :
                    printInvoice();
                    break;
                case 7:
                    invoiceOrder();
                    break;
                case 3:
                    deleteOrder();
                    break;
                case 8:
                    printSale();
                    break;
                case 0 :
                    break;
                default : break;
            }
        } while(num!= 0);
    }

    public static void printOrder(){
        System.out.println("Here's all the orders in history\n");
        OrderManager.printOrder();
    }

    public static void printInvoice() throws IOException {
        System.out.println("Here's all the invoices in history\n");
        OrderManager.printInvoice();
    }

    public static void invoiceOrder() throws IOException {
        System.out.println("Please enter the id of the order that you want to invoice\n");
        int id= in.nextInt();
        OrderManager.orderInvoiced(id);
    }

    public static void deleteItemFromOrder() throws IOException {
        System.out.println("Please enter the id of the order that you want to modify\n");
        int id= in.nextInt();
        Order order= null;
        for(Order o: Restaurant.orders){
            if(o.getId()== id){
                order= o;
            }
        }
        if(order== null){
            System.out.println("Order not found");
        }
        else{
            order.printMenuItemInOrder();
            System.out.println("Please enter the id of the menu item that you want to delete enter 0 to quit\n");
            List<MenuItem> menuItems= MenuManager.readMenuItem();
            int itemId;
            do{
                itemId= in.nextInt();
                order.removeItem(itemId);
            } while (itemId!= 0);
        }
    }

    public static void createOrder() throws IOException {
        System.out.println("Please enter the following details for order");
        LocalDate localDate = LocalDate.now();
        String date= localDate.toString();
        System.out.println("Current date:"+ localDate.toString());
        LocalTime localTime= LocalTime.now();
        String time= localTime.toString();
        System.out.println("Current time:"+ localTime.toString());
        System.out.println("Enter the number of people to seat");
        int pax= in.nextInt();
        Table table= selectTable(pax);
        if(table== null){
            System.out.println("No table available");
        }
        else{
            System.out.println("Please seat at Table "+ table.getId()+ "\n");
            System.out.println("Please choose a job title for the staffs that serve you\n");
            System.out.println("[1]GENERAL_MANAGER, [2]ASSISTANT_MANAGER, [3]SERVER, [4]CASHIER");
            int id= in.nextInt();
            Staff staff= selectStaff(id);
            System.out.printf("Staff "+ staff.getName()+ " is serving you\n\n");
            System.out.println("Add order success\n");
            long max= 0;
            if(Restaurant.orders.size()!= 0){
                for(Order order: Restaurant.orders){
                    if(order.getId()> max){
                        max= order.getId();
                    }
                }
            }
            Order order= new Order(max+1, staff, table, date, time, pax);
            OrderManager.addOrder(order);
        }
    }

    public static void addItemToOrder() throws IOException {
        System.out.println("Please enter the id of the order that you want to modify\n");
        int id= in.nextInt();
        Order order= null;
        for(Order o: Restaurant.orders){
            if(o.getId()== id){
                order= o;
            }
        }
        if (order== null){
            System.out.println("Order not found");
        }
        else{
            System.out.println("Here's all the menu item in this order");
            order.printMenuItemInOrder();
            System.out.println("Please enter the id of the menu item that you want to add, enter 0 to quit\n");
            List<MenuItem> menuItems= MenuManager.readMenuItem();
            int itemId;
            do{
                itemId= in.nextInt();
                order.addItem(itemId);
            } while (itemId!= 0);
        }
    }

    public static void deleteOrder(){
        System.out.println("Please enter the id of the order that you want to delete\n");
        int id= in.nextInt();
        OrderManager.removeOrder(id);
    }

    public static void printSale(){
        int sum= 0;
        if(Restaurant.invoices.size()!= 0){
            for(Order order: Restaurant.invoices){
                sum+= order.getSum();
            }
        }
        System.out.println("The sale for this current period is "+ sum);
    }

    public static Staff selectStaff(int type) throws IOException {
        Staff.jobTitle jobTitle;
        switch (type) {
            case 1:
                jobTitle = Staff.jobTitle.GENERAL_MANAGER;
                break;
            case 2:
                jobTitle = Staff.jobTitle.ASSISTANT_MANAGER;
                break;
            case 3:
                jobTitle = Staff.jobTitle.SERVER;
                break;
            case 4:
                jobTitle = Staff.jobTitle.CASHIER;
                break;
            default:
                jobTitle = Staff.jobTitle.SERVER;
                break;
        }
        System.out.println(jobTitle);
        List<Staff> staffList = StaffManager.readStaff();
        List<Staff> qualifyStaff = new ArrayList<>();
        for (Staff staff : staffList) {
            if (staff.getJob() == jobTitle) {
                qualifyStaff.add(staff);
            }
        }
        int finalId = (int) (Math.random() * qualifyStaff.size());
        Staff staff = qualifyStaff.get(finalId);
        return staff;
    }

    public static Table selectTable(int pax){
        Table table= TableManager.occupyTableForOrder(pax);
        return table;
    }
}
