package managers;

import beans.Reservation;
import beans.Restaurant;
import beans.Table;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

// TODO: 2021/10/29 consider logic for this again
public class ReservationManager {
    final static List<Reservation> reservations= Restaurant.reservations;
    final static List<Table> tables= Restaurant.tables;

    public static void addReservation(Reservation reservation){
        TableManager.occupyTableForReserve(reservation);
    }


    public static void removeReservation(int id){
        boolean removeReservation= false;
        for(Reservation reservation: reservations){
            if(reservation.getId()== id){
                removeReservation= true;
                System.out.println("Remove reservation booking success");
                reservation.getTable().setOccupied(false);
                reservations.remove(reservation);
            }
        }
        if(!removeReservation){
            System.out.println("Remove reservation booking failure");
        }
    }



    public static void reservationCheckIn(int id){
        boolean removeReservation= false;
        for(Reservation reservation: reservations){
            if(reservation.getId()== id){
                removeReservation= true;
                System.out.println("Check reservation booking success");
                reservation.getTable().setOccupied(false);
                reservations.remove(reservation);
            }
        }
        if(!removeReservation){
            System.out.println("Check reservation booking failure");
        }
    }

    public static void clearExpiredReservations() {
		for(Reservation r: reservations){
            if(LocalTime.now().isAfter(r.getTime().	plusMinutes(30))){
                removeReservation((int) r.getId());
            }
        }
	}


}
