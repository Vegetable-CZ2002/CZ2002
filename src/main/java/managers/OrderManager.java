package managers;

import adapters.MenuItemAdapter;
import beans.MenuItem;
import beans.Order;
import beans.Staff;
import beans.Table;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * The manager class that controls the Order class
 *
 * @author Ruan Donglin
 */
public class OrderManager extends BaseManager {
    private final StaffManager staffManager;
    private final MenuManager menuManager;
    private final TableManager tableManager;
    private List<Order> invoices;
    private final List<Order> orders;

    public OrderManager() throws IOException {
        invoices = read();
        staffManager = new StaffManager();
        menuManager= new MenuManager();
        tableManager = new TableManager();
        orders = new ArrayList<>();
    }

    public List<Order> getOrders() {
        return orders;
    }

    public List<Order> getInvoices() {
        return invoices;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }

    /**
     * Remove an order from the order list according to its order id
     *
     * @param id the order id of the order that needs to be removed
     */
    public void removeOrder(int id) {
        boolean removeSuccessful = false;
        for (Order o : orders) {
            if (o.getId() == id) {
                removeSuccessful = true;
                orders.remove(o);
                break;
            }
        }
        if (!removeSuccessful) {
            System.out.println("Remove Order Failure");
        }
    }

    /**
     * Invoice an order, that is to delete it from the order list, add it and write it to the invoice list.
     *
     * @param id       the id of the order that needs to be invoiced
     * @param isMember whether the customer of this order is a member or not
     * @throws IOException Signals that an I/O exception occurs related to the json operation
     */
    public void orderInvoiced(int id, boolean isMember) {
        boolean orderInvoiced = false;
        for (Order order : orders) {
            if (order.getId() == id) {
                if (isMember) {
                    order.setDiscount(order.getSum() * 0.05);
                }
                order.setLocalTime(LocalTime.now());
                order.setLocalDate(LocalDate.now());
                orderInvoiced = true;
                order.setInvoiced(true);
                order.getTable().setOccupied(false);
                order.setServiceFee(order.getSum() * 0.1);
                order.setTax((order.getSum() - order.getDiscount() + order.getServiceFee()) * 0.07);
                orders.remove(order);
                add(order);
                System.out.println("Order successfully invoiced");
                order.printInvoice();
                order.setSum(order.getSum() + order.getServiceFee() - order.getDiscount());
                break;
            }
        }
        if (!orderInvoiced) {
            System.out.println("Invoice order failure");
        }
    }


    /**
     * Read all existing invoices from the json file.
     *
     * @return the list of existing invoices in a list of Order object
     *
     * @throws IOException Signals that an I/O exception occurs related to the json operation
     */
    public List<Order> read() throws IOException {
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(MenuItem.class, new MenuItemAdapter());
        Gson gson = builder.setPrettyPrinting().create();
        Path file = Path.of("src/main/resources/data/invoice.json");
        String jsonString = Files.readString(file);
        Order[] orderArray = gson.fromJson(jsonString, Order[].class);
        if (orderArray == null) {
            invoices = new ArrayList<>();
        } else {
            invoices = new ArrayList<>(Arrays.asList(orderArray));
        }
        return invoices;
    }

    /**
     * Add an invoice to the invoice list, write it into the json file
     *
     * @param i the invoice that needs to be added
     */
    public void add(Object i) {
        Order o = (Order) i;
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(MenuItem.class, new MenuItemAdapter());
        Gson gson = builder.setPrettyPrinting().create();
        try {
            invoices.add(o);
            Order[] staffArray = new Order[invoices.size()];
            invoices.toArray(staffArray);
            Path file = Path.of("src/main/resources/data/invoice.json");
            Files.writeString(file, gson.toJson(staffArray), StandardOpenOption.WRITE);
        } catch (JsonIOException | IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {

    }

    public int getSize() {
        return orders.size();
    }

    public void print() {
        if (orders.size() == 0) {
            System.out.println("No order in history yet");
        } else {
            for (Order item : orders) {
                System.out.print(item.toString() + "\n");
            }
        }
    }

    public int getMenuSize() throws IOException {
        return menuManager.getSize();
    }

    public List<MenuItem> getMenuItemList(){
        return menuManager.getMenuItemList();
    }

    public List<Table> getTables(){
        return tableManager.getTables();
    }

    public List<Staff> getStaffs(){
        return staffManager.getStaffs();
    }

    /**
     * Check all available table to find one that allow the defined number of pax to seat
     *
     * @param pax the number of pax for the order
     */
    public Table occupyTableForOrder(int pax){
        return tableManager.occupyTableForOrder(pax);
    }

    public void printMenu(){
        menuManager.print();
    }
}
