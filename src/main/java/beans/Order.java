package beans;

import managers.MenuManager;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Formatter;
import java.util.List;

/**
 * Represents the order that customer gives, will be created in two cases: when customer directly walks in
 * or when customer check in to a reservation
 *
 * @author Ruan Donglin
 */
public class Order {
    private MenuManager menuManager;
    private int pax;
    private MenuItem[] menuItems;
    private Staff staffAssigned;
    private Table table;
    private LocalTime localTime;
    private LocalDate localDate;
    private boolean invoiced;
    private int id;
    private double sum;
    private double tax;
    private double serviceFee;
    private double discount = 0;

    public Order(int id, Staff staffAssigned, Table table, LocalDate localDate, LocalTime localTime, int pax) throws IOException {
        this.pax = pax;
        this.staffAssigned = staffAssigned;
        this.localTime = localTime;
        this.localDate = localDate;
        this.id = id;
        this.table = table;
        this.menuItems = new MenuItem[0];
        this.invoiced = false;
        this.sum = 0;
        this.tax = 0;
        this.menuManager = new MenuManager();
    }

    public MenuItem[] getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(MenuItem[] menuItems) {
        this.menuItems = menuItems;
    }


    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getServiceFee() {
        return serviceFee;
    }

    public void setServiceFee(double serviceFee) {
        this.serviceFee = serviceFee;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Staff getStaffAssigned() {
        return this.staffAssigned;
    }

    public void setStaffAssigned(Staff staffAssigned) {
        this.staffAssigned = staffAssigned;
    }

    public Table getTable() {
        return this.table;
    }

    public void setTable(Table table) {
        this.table = table;
    }


    public void setInvoiced(boolean invoiced) {
        this.invoiced = invoiced;
    }

    /**
     * Add menuItem into the order
     *
     * @param id the id of the menuItem that needs to be added
     */
    public void addItem(int id) {
        boolean addSuccessful = false;
        List<MenuItem> menuItemList = menuManager.getMenuItemList();
        for (MenuItem m : menuItemList) {
            if (m.getId() == id) {
                menuItems = Arrays.copyOf(menuItems, menuItems.length + 1);
                menuItems[menuItems.length - 1] = m;
                sum = getSum() + m.getPrice();
                addSuccessful = true;
                System.out.println("Add item success");
                break;
            }
        }
        if (!addSuccessful && id != 0) {
            System.out.println("Add item failure");
        }
    }

    /**
     * Remove an menuItem from the order if exists
     *
     * @param id the id of the menuItem that needs to be deleted
     */
    public void removeItem(int id) {
        boolean removeSuccessful = false;
        for (MenuItem m : menuItems) {
            if (m.getId() == id) {
                menuItems = Arrays.copyOf(menuItems, menuItems.length - 1);
                sum = getSum() - m.getPrice();
                removeSuccessful = true;
                System.out.println("Remove item success");
                break;
            }
        }
        if (!removeSuccessful && id != 0) {
            System.out.println("Remove item failure");
        }
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Order{" +
                "pax=" + pax +
                ", price sum=" + sum +
                ", menuItems=" + Arrays.asList(menuItems) +
                ", staffAssigned=" + staffAssigned.getName() +
                ", table=" + table.getId() +
                ", time=" + localTime.toString() +
                ", date=" + localDate.toString() +
                ", invoiced=" + invoiced +
                ", id=" + id +
                '}';
    }


    /**
     * Print all the existing menuItem in the order
     */
    public void printMenuItemInOrder() {
        if (menuItems.length == 0) {
            System.out.println("No item in this order yet");
        } else {
            for (MenuItem menuItem : menuItems) {
                System.out.println(menuItem.formatter());
            }
        }

    }

    public int getPax() {
        return pax;
    }

    public void setPax(int pax) {
        this.pax = pax;
    }

    /**
     * Print the formatted invoice for the order
     */
    public void printInvoice() {
        printTitles();
        printHeaders();
        for (MenuItem menuItem : menuItems) {
            printRows(String.valueOf(menuItem.getId()), menuItem.getName(), menuItem.getPrice());
        }
        printResult();
    }

    public void printTitles() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        Formatter fmt = new Formatter();
        Formatter fmt2 = new Formatter();
        System.out.print("_______________________________________________");
        System.out.print("\n\t\t\tInvoice\n");
        fmt.format("%8s %20s %5s %10s", "Server: ", staffAssigned.getName(), "Date: ", localDate);
        System.out.println(fmt);
        fmt2.format("%8s %20s %5s %10s", "Table: ", table.getId(), "Time: ", localTime.format(dtf));
        System.out.print(fmt2);
    }


    public void printHeaders() {
        System.out.print("\n-----------------------------------------------");
        System.out.print("\n| Id  |\t\t\t\tName\t\t\t|\ttotal\t|");
        System.out.print("\n-----------------------------------------------");
    }


    public static void printRows(String id, String desc, double fee) {
        System.out.printf("\n| %3s | %-28.28s| %.2f\t|", id, desc, fee);
        System.out.print("\n-----------------------------------------------");
    }

    public void printResult() {
        System.out.printf("\n|\t   Sub Total\t\t\t\t\t%.2f\t|", sum);
        System.out.print("\n-----------------------------------------------");
        System.out.printf("\n|\t   Membership Discount(5%%)\t\t-%.2f\t|", discount);
        System.out.printf("\n|\t   Service Fee(10%%)\t\t\t\t%.2f\t|", serviceFee);
        System.out.printf("\n|\t   Tax(7%%)\t\t\t\t\t\t%.2f\t|", tax);
        System.out.print("\n-----------------------------------------------");
        System.out.printf("\n|\t   Total\t\t\t\t\t\t%.2f\t|", sum - discount + serviceFee + tax);
        System.out.print("\n-----------------------------------------------");
        System.out.print("\n\t\t\t\tTHANK YOU!");
        System.out.print("\n\n-----------------------------------------------");
        System.out.println();
        System.out.println();
        System.out.println();
    }

}
