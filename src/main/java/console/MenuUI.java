package console;

import beans.Food;
import beans.MenuItem;
import beans.Restaurant;
import managers.MenuManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuUI {

    public static void printMenu() throws IOException {
        System.out.println("Here's all the menu items on the menu");
        MenuManager.printMenu();
    }

    public static void createMenuItem() throws IOException {
        System.out.println("Please enter information for the new menu item\n");
        System.out.println("Please enter the name for the new menu item");
        Scanner in = new Scanner(System.in);
        String name= in.nextLine();
        System.out.println(name);
        System.out.println("Please enter the price for the new menu item");
        Double price= in.nextDouble();
        System.out.println(price);
        System.out.println("Please enter the description for the new menu item");
        in.nextLine();
        String description= in.nextLine();
        System.out.println(description);
        System.out.println("Please choose a type for the new menu item");
        System.out.println("[1]APPETIZER, [2]MEAT, [3]PASTA, [4]PIZZA, [5]DESSERT, [6]DRINK");
        Integer t= in.nextInt();
        MenuItem.Type type;
        switch (t){
            case 1:
                type= MenuItem.Type.APPETIZER;
                break;
            case 2:
                type= MenuItem.Type.MEAT;
                break;
            case 3:
                type= MenuItem.Type.PASTA;
                break;
            case 4:
                type= MenuItem.Type.PIZZA;
                break;
            case 5:
                type= MenuItem.Type.DESSERT;
                break;
            case 6:
                type= MenuItem.Type.DRINK;
                break;
            default:
                type= MenuItem.Type.APPETIZER;
                break;
        }
        System.out.println(type);
        MenuItem menuItem= new Food(MenuManager.menuSize()+1, name, type, description, price);
        try {
            MenuManager.addMenuItem(menuItem);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        int num= 0;
        do{
            System.out.println("Welcome to the menu section! What action do you wish to take?");
            System.out.println("[1] View menu");
            System.out.println("[2] Create ala carte menu item");
            System.out.println("[3] Create set package");
            System.out.println("[4] Delete menu item");
            System.out.println("[5] Update menu item");
            System.out.println("[6] Return to main page");
            Scanner in = new Scanner(System.in);
            num= in.nextInt();
            switch (num){
                case 1 :
                    printMenu();
                    break;
                case 2 :
                    createMenuItem();
                    break;
                case 3 :
                    break;
                case 4 :
                    break;
                case 5 :
                    break;
                case 6 :
                    break;
                case 7 :
                    break;
                case 8 :
                    break;
                case 9 :
                    break;
                case 10 :
                    break;
                default :
            }
        } while(num!= 0);
    }

}
