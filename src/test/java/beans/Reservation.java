package beans;

import java.time.LocalDate;
import java.time.LocalTime;

public class Reservation{
    private LocalDate date;
	private LocalTime time;	
	private int pax;
	private String name;
	private String contact;
    private int id;

    public Reservation(int id, LocalDate date, LocalTime time, int pax, String name, String contact) {
        this.id= id;
        this.date = date;
        this.time = time;
        this.pax = pax;
        this.name = name;
        this.contact = contact;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

}
