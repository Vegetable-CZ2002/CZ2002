import adapters.MenuItemAdapter;
import beans.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import managers.MenuManager;
import managers.StaffManager;
import managers.TableManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    public static void main(String[] args) throws IOException {

        Table table1 = new Table(17, 6);
        Table table2 = new Table(18, 6);
        Table table3 = new Table(19, 10);
        Table table4 = new Table(20, 10);
        TableManager.readTable();
        TableManager.addTable(table1);
        TableManager.addTable(table2);
        TableManager.addTable(table3);
        TableManager.addTable(table4);

    }
}
