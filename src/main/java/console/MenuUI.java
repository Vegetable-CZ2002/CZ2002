package console;

import beans.Food;
import beans.MenuItem;
import beans.Restaurant;
import beans.SetPackage;
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

    public static void createAlaCarteItem() throws IOException {
        System.out.println("Please enter the following details for the new menu item\n");
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
        List<MenuItem> menuItems= MenuManager.readMenuItem();
        int i= 1;
        for(MenuItem menuItem: menuItems){
            if(menuItem.getId()!= i){
                break;
            }
            i++;
        }
        MenuItem menuItem= new Food(i, name, type, description, price);
        try {
            MenuManager.addMenuItem(menuItem);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createSetPackage() throws IOException {
        Scanner in = new Scanner(System.in);
        List<MenuItem> menuItems= MenuManager.readMenuItem();
        List<Food> foodList= new ArrayList<>();
        System.out.println("Please enter information for the new set package\n");
        System.out.println("Please enter the id of the ala carte items in the new set package, enter 0 to quit");
        int itemId;
        do{
            itemId= in.nextInt();
            for(MenuItem m: menuItems){
                if(m.getId()== itemId){
                    foodList.add((Food) m);
                }
            }
        } while (itemId!= 0);
        System.out.println("Please enter the name for the new set package");
        in.nextLine();
        String name= in.nextLine();
        System.out.println(name);
        System.out.println("Please enter the price for the new set package");
        Double price= in.nextDouble();
        System.out.println(price);
        System.out.println("Please enter the description for the new set package");
        in.nextLine();
        String description= in.nextLine();
        System.out.println(description);
        int i= 1;
        for(MenuItem menuItem: menuItems){
            if(menuItem.getId()!= i){
                break;
            }
            i++;
        }
        MenuItem menuItem= new SetPackage(i, name, description, price, foodList);
        try {
            MenuManager.addMenuItem(menuItem);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteMenuItem() throws IOException {
        Scanner in = new Scanner(System.in);
        List<MenuItem> menuItems= MenuManager.readMenuItem();
        System.out.println("Please enter the id of the menu item to delete, enter 0 to quit");
        long itemId;
        itemId= in.nextLong();
        if(itemId!= 0){
            MenuManager.deleteMenuItem(itemId);
        }
    }


    public static void updateAlaCarteItem() throws IOException{
        System.out.println("Please enter information for the updated menu item\n");
        Scanner in = new Scanner(System.in);
        System.out.println("Plsase enter the id for the updated menu item");
        Long id= in.nextLong();
        in.nextLine();
        System.out.println("Please enter the name for the updated menu item");
        String name= in.nextLine();
        System.out.println(name);
        System.out.println("Please enter the price for the updated menu item");
        Double price= in.nextDouble();
        System.out.println(price);
        System.out.println("Please enter the description for the updated menu item");
        in.nextLine();
        String description= in.nextLine();
        System.out.println(description);
        System.out.println("Please choose a type for the updated menu item");
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
        MenuItem menuItem= new Food(id, name, type, description, price);
        try {
            MenuManager.updateMenuItem(menuItem);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void updateSetPackage() throws IOException {
        Scanner in = new Scanner(System.in);
        List<MenuItem> menuItems= MenuManager.readMenuItem();
        List<Food> foodList= new ArrayList<>();
        System.out.println("Please enter information for the new set package\n");
        System.out.println("Plsase enter the id for the updated set package");
        Long id= in.nextLong();
        System.out.println("Please enter the id of the ala carte items in the new set package, enter 0 to quit");
        int itemId;
        do{
            itemId= in.nextInt();
            for(MenuItem m: menuItems){
                if(m.getId()== itemId){
                    foodList.add((Food) m);
                }
            }
        } while (itemId!= 0);
        System.out.println("Please enter the name for the new set package");
        in.nextLine();
        String name= in.nextLine();
        System.out.println(name);
        System.out.println("Please enter the price for the new set package");
        Double price= in.nextDouble();
        System.out.println(price);
        System.out.println("Please enter the description for the new set package");
        in.nextLine();
        String description= in.nextLine();
        System.out.println(description);
        MenuItem menuItem= new SetPackage(id, name, description, price, foodList);
        try {
            MenuManager.updateMenuItem(menuItem);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        int num= 0;
        do{
            System.out.println("Welcome to the menu section! What action do you wish to take?");
            System.out.println("[0] Return to main page");
            System.out.println("[1] View menu");
            System.out.println("[2] Create ala carte item");
            System.out.println("[3] Create set package");
            System.out.println("[4] Delete menu item");
            System.out.println("[5] Update ala carte item");
            System.out.println("[6] Update set package");
            Scanner in = new Scanner(System.in);
            num= in.nextInt();
            switch (num){
                case 1 :
                    printMenu();
                    break;
                case 2 :
                    createAlaCarteItem();
                    break;
                case 3 :
                    createSetPackage();
                    break;
                case 4 :
                    deleteMenuItem();
                    break;
                case 5 :
                    updateAlaCarteItem();
                    break;
                case 6:
                    updateSetPackage();
                case 0 :
                    break;
                default : break;
            }
        } while(num!= 0);
    }

    public static void mainUI() throws IOException {
        int num= 0;
        do{
            System.out.println("Welcome to the menu section! What action do you wish to take?");
            System.out.println("[0] Return to main page");
            System.out.println("[1] View menu");
            System.out.println("[2] Create ala carte item");
            System.out.println("[3] Create set package");
            System.out.println("[4] Delete menu item");
            System.out.println("[5] Update ala carte item");
            System.out.println("[6] Update set package");
            Scanner in = new Scanner(System.in);
            num= in.nextInt();
            switch (num){
                case 1 :
                    printMenu();
                    break;
                case 2 :
                    createAlaCarteItem();
                    break;
                case 3 :
                    createSetPackage();
                    break;
                case 4 :
                    deleteMenuItem();
                    break;
                case 5 :
                    updateAlaCarteItem();
                    break;
                case 6:
                    updateSetPackage();
                case 0 :
                    break;
                default : break;
            }
        } while(num!= 0);
    }

}
