package beans;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Order{
    private int pax;
	private ArrayList<MenuItem> menuItems;
	private Staff staffAssigned;
	private Table table;
	private LocalTime time;
    private LocalDate date;
	private boolean invoiced;
    private long id;
    private double sum= 0;

    public ArrayList<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(ArrayList<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public Order(long id, Staff staffAssigned, Table table, LocalDate date, LocalTime time, int pax) {
        this.pax= pax;
        this.staffAssigned = staffAssigned;
        this.date= date;
        this.time= time;
        this.id= id;
        this.table= table;
        this.menuItems= new ArrayList<>();
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
                menuItems.add(m);
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

    public void addAllItem(ArrayList<MenuItem> mItems){
        mItems.addAll(mItems);
    }

    public void removeAllItem(){
        menuItems.clear();
    }

    public void removeItem(int id){
        boolean removeSuccessful= false;
        for(MenuItem m: menuItems){
            if(m.getId()== id){
                menuItems.remove(m);
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


    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Order{" +
                "pax=" + pax +
                ", price sum=" + sum +
                ", menuItems=" + menuItems.toString() +
                ", staffAssigned=" + staffAssigned.getName() +
                ", table=" + table.getId() +
                ", time=" + time.toString() +
                ", date=" + date.toString() +
                ", invoiced=" + invoiced +
                ", id=" + id +
                '}';
    }

    public boolean isInvoiced() {
        return invoiced;
    }

    public void printMenuItemInOrder(){
        for(MenuItem menuItem: menuItems){
            System.out.println(menuItem.toString());
        }
    }

    public int getPax() {
        return pax;
    }

    public void setPax(int pax) {
        this.pax = pax;
    }
}
