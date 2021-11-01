package beans;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class Order{
    private int pax;
	private MenuItem[] menuItems;
	private Staff staffAssigned;
	private Table table;
	private LocalTime localTime;
    private LocalDate localDate;
	private boolean invoiced;
    private long id;
    private double sum= 0;
    public MenuItem[] getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(MenuItem[] menuItems) {
        this.menuItems = menuItems;
    }


    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public Order(long id, Staff staffAssigned, Table table, LocalDate localDate, LocalTime localTime, int pax) {
        this.pax= pax;
        this.staffAssigned = staffAssigned;
        this.localTime= localTime;
        this.localDate= localDate;
        this.id= id;
        this.table= table;
        this.menuItems= new MenuItem[0];
        this.invoiced= false;
        this.sum= 0;
    }


    public Staff getStaffAssigned() {
        return this.staffAssigned;
    }

    public void setStaffAssigned(Staff staffAssigned) {
        this.staffAssigned = staffAssigned;
    }

    public Table getTable() {
        return this.table;
    }

    public void setTable(Table table) {
        this.table = table;
    }


    public void setInvoiced(boolean invoiced) {
        this.invoiced = invoiced;
    }

    public void addItem(int id){
        boolean addSuccessful= false;
        List<MenuItem> menuItemList= Restaurant.menuItems;
        for(MenuItem m: menuItemList){
            if(m.getId()== id){
                menuItems= Arrays.copyOf(menuItems, menuItems.length+1);
                menuItems[menuItems.length-1]= m;
                sum= getSum()+ m.getPrice();
                addSuccessful= true;
                System.out.println("Add item success");
                break;
            }
        }
        if(!addSuccessful){
            System.out.println("Add item failure");
        }
    }

    public void removeItem(int id){
        boolean removeSuccessful= false;
        for(MenuItem m: menuItems){
            if(m.getId()== id){
                menuItems= Arrays.copyOf(menuItems, menuItems.length-1);
                sum= getSum()- m.getPrice();
                removeSuccessful= true;
                System.out.println("Remove item success");
                break;
            }
        }
        if(!removeSuccessful){
            System.out.println("Remove item failure");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return id == order.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Order{" +
                "pax=" + pax +
                ", price sum=" + sum +
                ", menuItems=" + Arrays.asList(menuItems) +
                ", staffAssigned=" + staffAssigned.getName() +
                ", table=" + table.getId() +
                ", time=" + localTime.toString() +
                ", date=" + localDate.toString() +
                ", invoiced=" + invoiced +
                ", id=" + id +
                '}';
    }

    public boolean isInvoiced() {
        return invoiced;
    }

    public void printMenuItemInOrder(){
        if(menuItems.length== 0){
            System.out.println("No item in this order yet");
        }
        else{
            for(MenuItem menuItem: menuItems){
                System.out.println(menuItem.toString());
            }
        }

    }

    public int getPax() {
        return pax;
    }

    public void setPax(int pax) {
        this.pax = pax;
    }
}
