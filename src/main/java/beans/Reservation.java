package beans;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class Reservation{
    private LocalDate localDate;
	private LocalTime localTime;
	private int pax;
	private String name;
	private String contact;
    private long id;
    private Table table;
    private String time;
    private String date;

    public void setId(long id) {
        this.id = id;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public Reservation(long id, String time, String date,  int pax, String name, String contact) {
        this.id= id;
        this.date = date;
        this.time = time;
        this.pax = pax;
        this.name = name;
        this.contact = contact;
        this.table= null;
        this.localDate= LocalDate.parse(date);
        this.localTime= LocalTime.parse(time);
    }

    
    public long getId() {
        return id;
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

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public LocalTime getLocalTime() {
        return localTime;
    }

    public void setLocalTime(LocalTime localTime) {
        this.localTime = localTime;
    }
}
