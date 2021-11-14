package console;

import beans.MenuItem;
import beans.Order;
import managers.MenuManager;
import managers.OrderManager;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * User interface class for the instructions regarding sale
 *
 * @author Ruan Donglin
 */
public class SaleUI extends BaseUI {
    private static final Scanner in = MainUI.in;
    private final OrderManager orderManager;
    private final MenuManager menuManager;

    public SaleUI() throws IOException {
        this.orderManager = new OrderManager();
        this.menuManager = new MenuManager();
    }

    void print() throws IOException {

    }

    public void mainUI() throws IOException {
        System.out.println("Please enter the date to check for sale in the format of YYYY-MM-DD:(eg. 2021-11-12)");
        in.nextLine();
        String date = in.nextLine();
        LocalDate localDate = LocalDate.parse(date);
        double sum = 0;
        double[] sale = new double[menuManager.menuSize()];
        if (orderManager.getInvoices().size() != 0) {
            for (Order order : orderManager.getInvoices()) {
                if (order.getLocalDate().isEqual(localDate)) {
                    MenuItem[] menuItems = order.getMenuItems();
                    for (MenuItem menuItem : menuItems) {
                        sale[menuItem.getId() - 1] += 1;
                    }
                    sum += order.getSum();
                }
            }
        }
        System.out.println("The sale for this current period is " + sum);
        for (MenuItem menuItem : menuManager.getMenuItemList()) {
            if (sale[menuItem.getId() - 1] != 0) {
                System.out.println("The individual sales item of id " + menuItem.getId() + " is " + (int) sale[menuItem.getId() - 1]);
            }
        }
    }

    /**
     * Print the sale in a day that the user provides with details of total sale and each item sale
     *
     * @throws IOException Signals that an I/O exception occurs in the menuManager related to json operation
     */
    public void printSale(LocalDate localDate) throws IOException {
        double sum = 0;
        double[] sale = new double[menuManager.menuSize()];
        if (orderManager.getInvoices().size() != 0) {
            for (Order order : orderManager.getInvoices()) {
                if (order.getLocalDate().isEqual(localDate)) {
                    MenuItem[] menuItems = order.getMenuItems();
                    for (MenuItem menuItem : menuItems) {
                        sale[menuItem.getId() - 1] += 1;
                    }
                    sum += order.getSum();
                }
            }
        }
        System.out.println("The sale for this current period is " + sum);
        for (MenuItem menuItem : menuManager.getMenuItemList()) {
            if (sale[menuItem.getId() - 1] != 0) {
                System.out.println("The individual sales item of id " + menuItem.getId() + " is " + (int) sale[menuItem.getId() - 1]);
            }
        }
    }
}
