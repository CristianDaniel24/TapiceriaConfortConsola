package Proyecto.TapiceriaConfort.entities;

import java.time.LocalDateTime;

public class Bill {

    private Long id;
    private LocalDateTime date;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void generateBill() {
    }
}
