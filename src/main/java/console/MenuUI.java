package console;

import beans.Food;
import beans.MenuItem;
import beans.SetPackage;
import managers.MenuManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;

/**
 * User interface class for the instructions regarding menu
 *
 * @author Ruan Donglin
 * @author Zhou Yuxuan
 */
public class MenuUI {

    private final MenuManager menuManager;

    public MenuUI() throws IOException {
        this.menuManager = new MenuManager();
    }

    //private static Scanner in = new Scanner(System.in);
    private static final Scanner in = MainUI.in;

    public void printMenu() throws IOException {
        System.out.println("Here's all the menu items on the menu");
        menuManager.printMenu();
    }

    public void createAlaCarteItem() {
        System.out.println("Please enter the following details for the new menu item\n");
        in.nextLine();
        System.out.println("Please enter the name for the new menu item");
        String name = in.nextLine();
        System.out.println(name);
        System.out.println("Please enter the price for the new menu item");
        Double price = in.nextDouble();
        System.out.println(price);
        System.out.println("Please enter the description for the new menu item");
        in.nextLine();
        String description = in.nextLine();
        System.out.println(description);
        System.out.println("Please choose a type for the new menu item");
        System.out.println("[1]APPETIZER, [2]MEAT, [3]PASTA, [4]PIZZA, [5]DESSERT, [6]DRINK, [7]OTHER MAIN COURSE");
        int t = in.nextInt();
        MenuItem.Type type;
        switch (t) {
            case 1:
                type = MenuItem.Type.APPETIZER;
                break;
            case 2:
                type = MenuItem.Type.MEAT;
                break;
            case 3:
                type = MenuItem.Type.PASTA;
                break;
            case 4:
                type = MenuItem.Type.PIZZA;
                break;
            case 5:
                type = MenuItem.Type.DESSERT;
                break;
            case 6:
                type = MenuItem.Type.DRINK;
                break;
            case 7:
                type = MenuItem.Type.OTHER_MAIN_COURSE;
                break;
            default:
                type = MenuItem.Type.APPETIZER;
                break;
        }
        System.out.println(type);
        List<MenuItem> menuItems = menuManager.getMenuItemList();
        int i = 1;
        for (MenuItem menuItem : menuItems) {
            if (menuItem.getId() != i) {
                break;
            }
            i++;
        }
        MenuItem menuItem = new Food(i, name, type, description, price);
        try {
            menuManager.addMenuItem(menuItem);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createSetPackage() {
        List<MenuItem> menuItems = menuManager.getMenuItemList();
        List<Food> foodList = new ArrayList<>();
        System.out.println("Please enter information for the new set package\n");
        System.out.println("Please enter the id of the ala carte items in the new set package, enter 0 to quit");
        int itemId;
        do {
            itemId = in.nextInt();
            for (MenuItem m : menuItems) {
                if (m.getId() == itemId) {
                    foodList.add((Food) m);
                }
            }
        } while (itemId != 0);
        System.out.println("Please enter the name for the new set package");
        in.nextLine();
        String name = in.nextLine();
        System.out.println(name);
        System.out.println("Please enter the price for the new set package");
        Double price = in.nextDouble();
        System.out.println(price);
        System.out.println("Please enter the description for the new set package");
        in.nextLine();
        String description = in.nextLine();
        System.out.println(description);
        int i = 1;
        for (MenuItem menuItem : menuItems) {
            if (menuItem.getId() != i) {
                break;
            }
            i++;
        }

        MenuItem menuItem = new SetPackage(i, name, description, price, foodList);
        try {
            menuManager.addMenuItem(menuItem);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteMenuItem() {
        System.out.println("Please enter the id of the menu item to delete, enter 0 to quit");
        int itemId;
        itemId = in.nextInt();
        if (itemId != 0) {
            menuManager.deleteMenuItem(itemId);
        }
    }


    public void updateAlaCarteItem() {
        MenuItem oldMenuItem = null;
        System.out.println("Please enter information for the updated menu item\n");
        System.out.println("Please enter the id for the updated menu item");
        int id = in.nextInt();
        in.nextLine();
        for (MenuItem menuItem : menuManager.getMenuItemList()) {
            if (menuItem.getId() == id) {
                oldMenuItem = menuItem;
            }
        }
        assert oldMenuItem != null;
        if (oldMenuItem.getType() == MenuItem.Type.PACKAGE) {
            System.out.println("This item is a set package. Please type [6] to update ala carte item");
            return;
        }
        Formatter fmt = new Formatter();
        fmt.format("%2s %28s %8s %10s   %15s", "id", "name", "price", "type", "description");
        System.out.println(fmt);
        System.out.println(oldMenuItem.formatter());
        System.out.println("Please enter the name for the updated menu item, enter 's' to use the old name");
        String name = in.nextLine();
        if (name.equals("s")) {
            name = oldMenuItem.getName();
        }
        System.out.println(name);
        System.out.println("Please enter the price for the updated menu item, enter 0 to use the old price");
        Double price = in.nextDouble();
        if (price == 0) {
            price = oldMenuItem.getPrice();
        }
        System.out.println(price);
        System.out.println("Please enter the description for the updated menu item, enter 's' to use the old description");
        in.nextLine();
        String description = in.nextLine();
        if (description.equals("s")) {
            description = oldMenuItem.getDescription();
        }
        System.out.println(description);
        System.out.println("Please choose a type for the updated menu item, enter [0] to use the old type");
        System.out.println("[1]APPETIZER, [2]MEAT, [3]PASTA, [4]PIZZA, [5]DESSERT, [6]DRINK, [7]OTHER MAIN COURSE");
        int t = in.nextInt();
        MenuItem.Type type;
        switch (t) {
            case 0:
                type = oldMenuItem.getType();
                break;
            case 1:
                type = MenuItem.Type.APPETIZER;
                break;
            case 2:
                type = MenuItem.Type.MEAT;
                break;
            case 3:
                type = MenuItem.Type.PASTA;
                break;
            case 4:
                type = MenuItem.Type.PIZZA;
                break;
            case 5:
                type = MenuItem.Type.DESSERT;
                break;
            case 6:
                type = MenuItem.Type.DRINK;
                break;
            case 7:
                type = MenuItem.Type.OTHER_MAIN_COURSE;
                break;
            default:
                type = MenuItem.Type.APPETIZER;
                break;
        }
        System.out.println(type);
        MenuItem menuItem = new Food(id, name, type, description, price);
        try {
            menuManager.updateMenuItem(menuItem);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateSetPackage() {
        MenuItem oldMenuItem = null;
        List<MenuItem> menuItems = menuManager.getMenuItemList();
        List<Food> foodList = new ArrayList<>();
        System.out.println("Please enter information for the new set package\n");
        System.out.println("Please enter the id for the updated set package");
        int id = in.nextInt();
        for (MenuItem menuItem : menuManager.getMenuItemList()) {
            if (menuItem.getId() == id) {
                oldMenuItem = menuItem;
            }
        }
        assert oldMenuItem != null;
        if (oldMenuItem.getType() != MenuItem.Type.PACKAGE) {
            System.out.println("This item is not a set package. Please type [5] to update ala carte item");
            return;
        }
        System.out.println("The old set package is");
        Formatter fmt = new Formatter();
        fmt.format("%2s %28s %8s %10s   %15s", "id", "name", "price", "type", "description");
        System.out.println(fmt);
        System.out.println(oldMenuItem.formatter());
        System.out.println("Please enter the id of the ala carte items in the updated set package, enter 0 to quit");
        int itemId;
        do {
            itemId = in.nextInt();
            for (MenuItem m : menuItems) {
                if (m.getId() == itemId) {
                    foodList.add((Food) m);
                    break;
                }
            }
        } while (itemId != 0);
        System.out.println("Please enter the name for the updated set package, enter 's' to use the old name");
        in.nextLine();
        String name = in.nextLine();
        if (name.equals("s")) {
            name = oldMenuItem.getName();
        }
        System.out.println(name);
        System.out.println("Please enter the price for the updated set package, enter 0 to use the old price");
        Double price = in.nextDouble();
        if (price == 0) {
            price = oldMenuItem.getPrice();
        }
        System.out.println(price);
        System.out.println("Please enter the description for the updated set package, enter 's' to use the old description");
        in.nextLine();
        String description = in.nextLine();
        if (description.equals("s")) {
            description = oldMenuItem.getDescription();
        }
        System.out.println(description);
        MenuItem menuItem = new SetPackage(id, name, description, price, foodList);
        try {
            menuManager.updateMenuItem(menuItem);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mainUI() throws IOException {
        int num;
        do {
            System.out.println("Welcome to the menu section! What action do you wish to take?");
            System.out.println("[0] Return to main page");
            System.out.println("[1] View menu");
            System.out.println("[2] Create ala carte item");
            System.out.println("[3] Create set package");
            System.out.println("[4] Delete menu item");
            System.out.println("[5] Update ala carte item");
            System.out.println("[6] Update set package");
            num = in.nextInt();
            switch (num) {
                case 1:
                    printMenu();
                    break;
                case 2:
                    createAlaCarteItem();
                    break;
                case 3:
                    createSetPackage();
                    break;
                case 4:
                    deleteMenuItem();
                    break;
                case 5:
                    updateAlaCarteItem();
                    break;
                case 6:
                    updateSetPackage();
                case 0:
                    break;
                default:
                    break;
            }
        } while (num != 0);
    }

}
