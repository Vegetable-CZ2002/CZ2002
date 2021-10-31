package managers;

import beans.Reservation;
import beans.Table;
import console.OrderUI;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Iterator;

import static beans.Restaurant.reservations;

public class ReservationManager {

    public static Table addReservation(Reservation reservation){
        Table t= TableManager.occupyTableForReserve(reservation);
        // TODO: 2021/10/31 write reservation to json file
        return t;
    }


    public static void removeReservation(int id){
        boolean removeReservation= false;
        Iterator<Reservation> reservationIterator= reservations.iterator();
        while(reservationIterator.hasNext()){
            Reservation reservation= reservationIterator.next();
            {
                if(reservation.getId()== id){
                    removeReservation= true;
                    System.out.println("Remove reservation booking success");
                    reservation.getTable().setOccupied(false);
                    reservationIterator.remove();
                    break;
                }
            }
        }
        if(!removeReservation){
            System.out.println("Remove reservation booking failure");
        }
    }



    public static void reservationCheckIn(int id) throws IOException {
        boolean removeReservation= false;
        Iterator<Reservation> reservationIterator= reservations.iterator();
        while(reservationIterator.hasNext()){
            Reservation reservation= reservationIterator.next();
            {
                if(reservation.getId()== id){
                    removeReservation= true;
                    System.out.println("Check reservation booking success");
                    reservation.getTable().setOccupied(false);
                    reservationIterator.remove();
                    OrderUI.createOrder();
                    break;
                }
            }
        }
        if(!removeReservation){
            System.out.println("Check reservation booking failure");
        }
    }

    public static void clearExpiredReservations() {
        Iterator<Reservation> reservationIterator= reservations.iterator();
        while(reservationIterator.hasNext()){
            Reservation r= reservationIterator.next();
            {
                if(LocalDate.now().isAfter(r.getLocalDate()) || (LocalDate.now().isEqual(r.getLocalDate())&&LocalTime.now().isAfter(r.getLocalTime().plusMinutes(30)))){
                    reservationIterator.remove();
                    r.getTable().setOccupied(false);
                    System.out.println("Reservation "+ r.getId()+ " expires");
                }
            }
        }
	}


}
