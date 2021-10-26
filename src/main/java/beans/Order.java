package beans;

import java.util.ArrayList;
import java.util.Date;

public class Order{
	private ArrayList<MenuItem> menuItems;
	private Staff staffAssigned;
	private Table table;
	private Date timestamp;
	private String name;
	private boolean invoiced;


    public Order(Staff staffAssigned, Table table, Date timestamp, String name, boolean invoiced) {
        this.staffAssigned = staffAssigned;
        this.table = table;
        this.timestamp = timestamp;
        this.name = name;
        this.invoiced = invoiced;
        this.menuItems= new ArrayList<>();
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

    public Date getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isInvoiced() {
        return this.invoiced;
    }

    public boolean getInvoiced() {
        return this.invoiced;
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
            if(m.getName().equals(item.getName())){
                menuItems.remove(m);
                removeSuccessful= true;
                break;
            }
        }
        if(!removeSuccessful){
            System.out.println("Remove item failure");
        }
    }

    


}
