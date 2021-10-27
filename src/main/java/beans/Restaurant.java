package beans;

import managers.MenuManager;

import java.io.IOException;
import java.util.ArrayList;


public class Restaurant
{
    public static ArrayList<Table> tables;
    public static ArrayList<Staff> staffs;
	public static ArrayList<MenuItem> menuItems;
    public static ArrayList<Order> orders;
    public static ArrayList<Order> invoices;
	public static ArrayList<Reservation> reservations;

	public Restaurant() throws IOException {
		initMenu();
		initTable();
		initStaff();
		initOrders();
		initInvoices();
		initReservations();
	}

	private static void initReservations() {
		reservations= new ArrayList<>();
	}

	private static void initStaff() {

		staffs= new ArrayList<>();
	}

	private static void initOrders() {
		orders= new ArrayList<>();
	}

	private static void initInvoices() {
		invoices= new ArrayList<>();
	}

	private static void initTable() {
		tables= new ArrayList<>();
	}

	private static void initMenu() throws IOException {
		menuItems= (ArrayList<MenuItem>) MenuManager.readMenuItem();
	}



}
