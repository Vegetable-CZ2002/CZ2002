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

/**
 * User interface class for the instructions regarding order
 *
 *  @author Ruan Donglin
 */
public class OrderUI {
    private static final Scanner in = MainUI.in;
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
            System.out.println("[6] Invoice order");
            System.out.println("[7] Print sale revenue report by period");
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
                case 6:
                    invoiceOrder();
                    break;
                case 3:
                    deleteOrder();
                    break;
                case 7:
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
            System.out.println("[6] Invoice order");
            System.out.println("[7] Print sale revenue report by period");
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
                case 6:
                    invoiceOrder();
                    break;
                case 3:
                    deleteOrder();
                    break;
                case 7:
                    printSale();
                    break;
                case 0 :
                    break;
                default : break;
            }
        } while(num!= 0);
    }

    /**
     * print all/ a specific order detail
     *
     */
    public static void printOrder() {
        System.out.println("Do you want to view all the orders in history? Please enter [Y/n]");
        in.nextLine();
        String value= in.nextLine();
        if(value.equals("Y")){
            System.out.println("Here's all the orders in history\n");
            OrderManager.printOrder();
        }
        else if(value.equals("n")){
            System.out.println("Please enter the id of the order that you want to view\n");
            int id= in.nextInt();
            for(Order order: OrderManager.orders){
                if(order.getId()== id){
                    System.out.println(order);
                }
            }
        }

    }

    /**
     * invoice an order
     *
     * @throws IOException
     */
    public static void invoiceOrder() throws IOException {
        boolean isMember= false;
        System.out.println("Please enter the id of the order that you want to invoice\n");
        int id= in.nextInt();
        System.out.println("Are you a member of the restaurant? Please enter [Y/n]");
        in.nextLine();
        String value= in.nextLine();
        if(value.equals("Y")){
            isMember= true;
        }
        else if(value.equals("n")){
            isMember= false;
        }
        OrderManager.orderInvoiced(id, isMember);
    }

    /**
     * delete a defined menuItem from the order
     *
     * @throws IOException
     */
    public static void deleteItemFromOrder() throws IOException {
        System.out.println("Please enter the id of the order that you want to modify\n");
        int id= in.nextInt();
        Order order= null;
        for(Order o: OrderManager.orders){
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

    /**
     * Create an order after the customer checks in.
     *
     * @param reservation
     * @throws IOException
     */
    public static void createOrderAfterReservation(Reservation reservation) throws IOException{
        LocalDate localDate = LocalDate.now();
        System.out.println("Current date:"+ localDate.toString());
        LocalTime localTime= LocalTime.now();
        System.out.println("Current time:"+ localTime.toString());
        System.out.println("Please seat at Table "+ reservation.getTable().getId()+ "\n");
        System.out.println("Please choose a job title for the staffs that serve you\n");
        System.out.println("[1]GENERAL_MANAGER, [2]ASSISTANT_MANAGER, [3]SERVER, [4]CASHIER");
        int id= in.nextInt();
        Staff staff= selectStaff(id);
        System.out.printf("Staff "+ staff.getName()+ " is serving you\n\n");
        System.out.println("Add order success\n");
        long max= 0;
        if(OrderManager.orders.size()!= 0){
            for(Order order: OrderManager.orders){
                if(order.getId()> max){
                    max= order.getId();
                }
            }
        }
        Order order= new Order(max+1, staff, reservation.getTable(), localDate, localTime, reservation.getPax());
        OrderManager.addOrder(order);
    }

    /**
     * Create a new order after the customer walks in
     *
     * @throws IOException
     */
    public static void createOrder() throws IOException {
        System.out.println("Please enter the following details for order");
        LocalDate localDate = LocalDate.now();
        System.out.println("Current date:"+ localDate.toString());
        LocalTime localTime= LocalTime.now();
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
            long max= 0;
            if(OrderManager.orders.size()!= 0){
                for(Order order: OrderManager.orders){
                    if(order.getId()> max){
                        max= order.getId();
                    }
                }
            }
            Order order= new Order(max+1, staff, table, localDate, localTime, pax);
            System.out.println("You order id is "+ (max+1) + ". Add order success\n");
            OrderManager.addOrder(order);
        }
    }

    /**
     * Add a defined item to the order
     *
     * @throws IOException
     */
    public static void addItemToOrder() throws IOException {
        System.out.println("Please enter the id of the order that you want to modify\n");
        int id= in.nextInt();
        Order order= null;
        for(Order o: OrderManager.orders){
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

    /**
     * print the sale in this period with details of total sale and each item sale
     *
     * @throws IOException
     */
    public static void printSale() throws IOException {
        double sum= 0;
        double[] sale= new double[MenuManager.menuSize()];
        if(OrderManager.readInvoice().size()!= 0){
            for(Order order: OrderManager.readInvoice()){
                if (order.getLocalDate().isEqual(LocalDate.now())) {
                    MenuItem[] menuItems = order.getMenuItems();
                    for (int i = 0; i < menuItems.length; i++) {
                        sale[menuItems[i].getId() - 1] += 1;
                    }
                    sum += order.getSum();
                }
            }
        }
        System.out.println("The sale for this current period is "+ sum);
        for(MenuItem menuItem: MenuManager.readMenuItem()){
            if(sale[menuItem.getId()-1]!= 0){
                System.out.println("The individual sales item of id "+ menuItem.getId()+ " is "+ (int)sale[menuItem.getId()-1]);
            }
        }
    }

    /**
     * Select a random staff to serve an order
     *
     * @param type
     * @return
     * @throws IOException
     */
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

    /**
     * Select a qualified table for an order
     *
     * @param pax
     * @return
     */
    public static Table selectTable(int pax){
        Table table= TableManager.occupyTableForOrder(pax);
        return table;
    }
}
