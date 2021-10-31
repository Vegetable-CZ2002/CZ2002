package beans;

import managers.MenuManager;
import managers.OrderManager;
import managers.StaffManager;
import managers.TableManager;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class Restaurant
{
    public static List<Table> tables;

	static {
		try {
			tables = TableManager.readTable();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<Staff> staffs;

	static {
		try {
			staffs = StaffManager.readStaff();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<MenuItem> menuItems;

	static {
		try {
			menuItems = MenuManager.readMenuItem();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<Order> orders= new ArrayList<>();
    public static List<Order> invoices= new ArrayList<>();
	public static List<Reservation> reservations= new ArrayList<>();

//	public Restaurant() throws IOException {
//		initMenu();
//		initTable();
//		initStaff();
//		initOrders();
//		initInvoices();
//		initReservations();
//	}
//
//	private static void initReservations() {
//		reservations= new ArrayList<>();
//	}
//
//	private static void initStaff() throws IOException {
//		staffs= StaffManager.readStaff();
//	}
//
//	private static void initOrders() throws IOException {
//		orders= new ArrayList<>();
//		Order order= new Order(1, StaffManager.readStaff().get(2), TableManager.readTable().get(2), LocalDate.now(), LocalTime.now());
//		orders.add(order);
//	}
//
//	private static void initInvoices() {
//		invoices= new ArrayList<>();
//	}
//
//	private static void initTable() throws IOException {
//		tables= TableManager.readTable();
//	}
//
//	private static void initMenu() throws IOException {
//		menuItems= MenuManager.readMenuItem();
//	}




}
