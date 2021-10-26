package beans;



public class Table {
    public int numOfSeats;
	public int tableId;
	public boolean isOccupied;
	public Reservation tableReservation;
	
	public Table(int numOfSeats, int tableId) { //
		this.numOfSeats = numOfSeats;
		this.isOccupied = false;
		this.tableId = tableId;
	}


    public int getNumOfSeats() {
        return this.numOfSeats;
    }

    public void setNumOfSeats(int numOfSeats) {
        this.numOfSeats = numOfSeats;
    }

    public int getTableId() {
        return this.tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public boolean isIsOccupied() {
        return this.isOccupied;
    }

    public boolean getIsOccupied() {
        return this.isOccupied;
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
}
