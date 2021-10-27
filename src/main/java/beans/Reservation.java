package beans;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Reservation{
    private LocalDate date;
	private LocalTime time;	
	private int pax;
	private String name;
	private String contact;
    private long id;
    private Table table;

    public void setId(long id) {
        this.id = id;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Reservation(long id, LocalDate date, LocalTime time, int pax, String name, String contact) {
        this.id= id;
        this.date = date;
        this.time = time;
        this.pax = pax;
        this.name = name;
        this.contact = contact;
        this.table= null;
    }

    
    public long getId() {
        return id;
    }
    

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return this.time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public int getPax() {
        return this.pax;
    }

    public void setPax(int pax) {
        this.pax = pax;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact() {
        return this.contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;
        Reservation that = (Reservation) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "date=" + date +
                ", time=" + time +
                ", pax=" + pax +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", id=" + id +
                '}';
    }
}
