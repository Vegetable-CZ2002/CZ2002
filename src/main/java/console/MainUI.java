package console;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

public class MainUI {
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
            Scanner in = new Scanner(System.in);
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
                    break;
                case 7 :
                    break;
                case 8 :
                    break;
                case 9 :
                    System.out.println("Please choose [6] in order section");
                    OrderUI.mainUI();
                    break;
                case 10 :
                    System.out.println("Please choose [8] in order section");
                    OrderUI.mainUI();
                    break;
                default :
            }
        } while(num!= 0);


    }

}
