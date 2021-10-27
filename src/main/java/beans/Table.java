package beans;


import java.util.Objects;

public class Table {
    private int numOfSeats;
	private boolean isOccupied;
    private long id;
	private Reservation tableReservation;

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public Table(long id, int numOfSeats) { //
		this.numOfSeats = numOfSeats;
		this.isOccupied = false;
		this.id= id;
        this.tableReservation= null;
	}


    public int getNumOfSeats() {
        return this.numOfSeats;
    }

    public void setNumOfSeats(int numOfSeats) {
        this.numOfSeats = numOfSeats;
    }


    public void setIsOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public Reservation getTableReservation() {
        return this.tableReservation;
    }

    public void setTableReservation(Reservation tableReservation) {
        this.tableReservation = tableReservation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Table)) return false;
        Table table = (Table) o;
        return id == table.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Table{" +
                "numOfSeats=" + numOfSeats +
                ", isOccupied=" + isOccupied +
                ", id=" + id +
                ", tableReservation=" + tableReservation +
                '}';
    }
}
