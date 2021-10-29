package managers;

import beans.Reservation;
import beans.Restaurant;
import beans.Table;

import java.util.ArrayList;
import java.util.List;

public class TableManager {
    static List<Table> tables= Restaurant.tables;

    // TODO: 2021/10/28 readTables


    public Table checkTableAvailability(Reservation r){
        for(Table t: tables){
            if(t.isOccupied() == false && t.getTableReservation()== null && t.getNumOfSeats() >= r.getPax()){
                return t;
            }
        }
        return null;
    }
}
