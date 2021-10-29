package managers;

import beans.Staff;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StaffManager {

    public static List<Staff> staffs;

    // TODO: 2021/10/28 readStaffs
    public static List<Staff> readStaff() throws IOException{
        Gson gson = new Gson();
        Path file = Path.of("src/main/resources/data/staff.json");
        String jsonString = Files.readString(file);
        Staff[] staffArray = gson.fromJson(jsonString, Staff[].class);
        if(staffArray == null){
            staffs = new ArrayList<Staff>();
        }
        else{
            staffs = new ArrayList<>(Arrays.asList(staffArray));
        }
        return staffs;
    }

    public static void addStaff(Staff s) throws IOException{
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        try {
            staffs.add(s);
            Staff[] staffArray = new Staff[staffs.size()];
            staffs.toArray(staffArray);
            Path file = Path.of("src/main/resources/data/staff.json");
            Files.writeString(file, gson.toJson(staffArray), StandardOpenOption.WRITE);
        } catch (JsonIOException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
