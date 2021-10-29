package beans;

import managers.MenuManager;
import managers.TableManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Restaurant
{
    public static List<Table> tables;
    public static List<Staff> staffs;
	public static List<MenuItem> menuItems;
    public static List<Order> orders;
    public static List<Order> invoices;
	public static List<Reservation> reservations;

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

	private static void initTable() throws IOException {
		tables= TableManager.readTable();
	}

	private static void initMenu() throws IOException {
		menuItems= MenuManager.readMenuItem();
	}




}
