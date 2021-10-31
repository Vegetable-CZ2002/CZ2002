package beans;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DateReservation {
    private LocalDate date;
    private List<Integer> occupiedTableId;

    public DateReservation(LocalDate date) {
        this.date = date;
        occupiedTableId= new ArrayList<>();
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Integer> getOccupiedTableId() {
        return occupiedTableId;
    }

    public void addOccupiedTableId(int id) {
        this.occupiedTableId.add(id);
    }
}
