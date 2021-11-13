package beans;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Formatter;
import java.util.Objects;

/**
 * Represents the reservation the customer books
 *
 * @author Ruan Donglin
 */
public class Reservation {
    private LocalDate localDate;
    private LocalTime localTime;
    private int pax;
    private String name;
    private String contact;
    private int id;
    private Table table;

    public Reservation(int id, LocalDate localDate, LocalTime localTime, int pax, String name, String contact, Table table) {
        this.id = id;
        this.pax = pax;
        this.name = name;
        this.contact = contact;
        this.table = table;
        this.localDate = localDate;
        this.localTime = localTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
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
    public String toString() {
        return "Reservation{" +
                "localDate=" + localDate +
                ", localTime=" + localTime +
                ", pax=" + pax +
                ", name='" + name + '\'' +
                ", contact='" + contact + '\'' +
                ", id=" + id +
                ", table=" + table +
                '}';
    }

    public Formatter formatter() {
        Formatter fmt = new Formatter();
        return fmt.format("%2s  %10s  %5s  %8s  %3s %12s %15s", id, localDate, localTime, table.getId(), pax, name, contact);
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
