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

/**
 * The manager class that controls the Staff class
 *
 * @author Ruan Donglin
 */
public class StaffManager extends BaseManager {
    private List<Staff> staffs;

    public StaffManager() throws IOException {
        staffs = readStaff();
    }

    /**
     * Read all staff from the staff.json file
     *
     * @return a list of staff in the restaurant that is in the format of list of Staff object
     *
     * @throws IOException Signals that an I/O exception occurs related to the json operation
     */
    public List<Staff> readStaff() throws IOException {
        Gson gson = new Gson();
        Path file = Path.of("src/main/resources/data/staff.json");
        String jsonString = Files.readString(file);
        Staff[] staffArray = gson.fromJson(jsonString, Staff[].class);
        if (staffArray == null) {
            staffs = new ArrayList<>();
        } else {
            staffs = new ArrayList<>(Arrays.asList(staffArray));
        }
        return staffs;
    }

    public List<Staff> getStaffs() {
        return staffs;
    }

    @Override
    public List read() throws IOException {
        return null;
    }

    /**
     * Add a staff to the staff.json file
     *
     * @param o the staff object that needs to be added
     */
    @Override
    public void add(Object o) {
        Staff s = (Staff) o;
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.setPrettyPrinting().create();
        try {
            staffs.add(s);
            Staff[] staffArray = new Staff[staffs.size()];
            staffs.toArray(staffArray);
            Path file = Path.of("src/main/resources/data/staff.json");
            Files.writeString(file, gson.toJson(staffArray), StandardOpenOption.WRITE);
        } catch (JsonIOException | IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) throws IOException {

    }

    @Override
    public int getSize() {
        return 0;
    }
}
