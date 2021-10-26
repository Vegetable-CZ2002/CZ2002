package beans;

import java.util.ArrayList;


public class Restaurant
{
    public static ArrayList<Table> tables= new ArrayList<>();
    public static ArrayList<Staff> staffs= new ArrayList<>();
    public static ArrayList<Order> orders= new ArrayList<>();
    public static ArrayList<Order> invoices= new ArrayList<>();
	public static ArrayList<Reservation> reservations= new ArrayList<>();

    public static void initRestaurant() {
		initMenu();
		initTable();
		initStaff();
		initOrders();
		initInvoices();
		initReservations();
	}

	private static void initReservations() {
		
	}

	private static void initStaff() {

	}

	private static void initOrders() {

	}

	private static void initInvoices() {

	}

	private static void initTable() {

	}

	private static void initMenu() {

	}

}
