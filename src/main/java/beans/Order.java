package beans;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Order{
	private ArrayList<MenuItem> menuItems;
	private Staff staffAssigned;
	private Table table;
	private LocalTime time;
    private LocalDate date;
	private String name;
	private boolean invoiced;
    private long id;


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

    public Order(long id, Staff staffAssigned, Table table, LocalDate date, LocalTime time, String name) {
        this.staffAssigned = staffAssigned;
        this.table = null;
        this.date= date;
        this.time= time;
        this.name = name;
        this.id= id;
        this.table= table;
        this.menuItems= new ArrayList<>();
        this.invoiced= false;
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


    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public void setInvoiced(boolean invoiced) {
        this.invoiced = invoiced;
    }

    public void addItem(MenuItem mItem){
        menuItems.add(mItem);
    }

    public void addAllItem(ArrayList<MenuItem> mItems){
        mItems.addAll(mItems);
    }

    public void removeAllItem(){
        menuItems.clear();
    }

    public void removeItem(MenuItem item){
        boolean removeSuccessful= false;
        for(MenuItem m: menuItems){
            if(m.equals(item)){
                menuItems.remove(m);
                removeSuccessful= true;
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
                "menuItems=" + menuItems +
                ", staffAssigned=" + staffAssigned +
                ", table=" + table +
                ", time=" + time +
                ", date=" + date +
                ", name='" + name + '\'' +
                ", invoiced=" + invoiced +
                ", id=" + id +
                '}';
    }

    public boolean isInvoiced() {
        return invoiced;
    }
}
