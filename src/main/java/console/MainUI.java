package console;

import managers.MenuManager;
import managers.ReservationManager;
import managers.TableManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * User interface class for the all instructions
 *
 *  @author Ruan Donglin
 */
public class MainUI {
    public static File text = new File("src/main/resources/testdata/a.txt");
    public static Scanner in = new Scanner(System.in);
//    static {
//        try {
//            in = new Scanner(text);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//    }

    public static void main(String[] args) throws IOException {
        int num;
        do{
            System.out.println("Welcome to the restaurant! What action do you wish to do now? ");
            System.out.println("[0] Exit from restaurant");
            System.out.println("[1] Create/Update/Remove menu item");
            System.out.println("[2] Create/Update/Remove promotion");
            System.out.println("[3] Create order");
            System.out.println("[4] View order");
            System.out.println("[5] Add/Remove order item/s to/from order");
            System.out.println("[6] Create reservation booking");
            System.out.println("[7] Check/Remove reservation booking");
            System.out.println("[8] Check table availability");
            System.out.println("[9] Print order invoice");
            System.out.println("[10] Print sale revenue report by period");
            TableManager.setTableReserved();
            ReservationManager.clearExpiredReservations();
            num= in.nextInt();
            switch (num){
                case 1 :
                    System.out.println("Please choose [2]/[5]/[4] in menu section");
                    MenuUI.mainUI();
                    break;
                case 2 :
                    System.out.println("Please choose [3]/[6]/[4] in menu section");
                    MenuUI.mainUI();
                    break;
                case 3 :
                    System.out.println("Please choose [2] in order section");
                    OrderUI.mainUI();
                    break;
                case 4 :
                    System.out.println("Please choose [1] in order section");
                    OrderUI.mainUI();
                    break;
                case 5 :
                    System.out.println("Please choose [4]/[5] in order section");
                    OrderUI.mainUI();
                    break;
                case 6 :
                    System.out.println("Please choose [1] in reservation section");
                    ReservationUI.mainUI();
                    break;
                case 7 :
                    System.out.println("Please choose [2]/[3] in reservation section");
                    ReservationUI.mainUI();
                    break;
                case 8 :
                    TableUI.checkTableAvailability();
                    break;
                case 9 :
                    System.out.println("Please choose [6] in order section");
                    OrderUI.mainUI();
                    break;
                case 10 :
                    System.out.println("Please choose [7] in order section");
                    OrderUI.mainUI();
                    break;
                default :
            }
        } while(num!= 0);
    }

}
