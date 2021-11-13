package console;

import beans.Order;
import beans.Table;
import managers.ReservationManager;
import managers.TableManager;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * User interface class for the all instructions
 *
 * @author Ruan Donglin
 */
public class MainUI {
    private static TableManager tableManager;
    private static ReservationManager reservationManager;
    public static File text = new File("src/main/resources/testdata/a.txt");
    public static Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        reservationManager = new ReservationManager();
        tableManager = new TableManager();
        int num;
        do {
            System.out.println("Welcome to the restaurant! What action do you wish to do now? ");
            System.out.println("[0] Exit from restaurant");
            System.out.println("[1] Menu UI");
            System.out.println("[2] Order UI");
            System.out.println("[3] Reservation UI");
            System.out.println("[4] Print sale revenue report");
            System.out.println("[5] Check table availability");
            tableManager.setTableReserved();
            reservationManager.clearExpiredReservations();
            num = in.nextInt();
            switch (num) {
                case 1:
                    MenuUI menuUI = new MenuUI();
                    menuUI.mainUI();
                    break;
                case 2:
                    OrderUI orderUI = new OrderUI();
                    orderUI.mainUI();
                    break;
                case 3:
                    ReservationUI reservationUI = new ReservationUI();
                    reservationUI.mainUI();
                    break;
                case 4:
                    SaleUI saleUI = new SaleUI();
                    saleUI.mainUI();
                    break;
                case 5:
                    TableUI tableUI = new TableUI();
                    tableUI.checkTableAvailability();
                    break;
                default:
            }
        } while (num != 0);
    }

}
