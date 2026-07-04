package model;

import java.time.LocalDateTime;

public class Ticket {
    private String id;
    private LocalDateTime time;
    private Slot slot;

    public Ticket(String id, LocalDateTime time, Slot slot) {
        this.id = id;
        this.time = time;
        this.slot = slot;
    }

    public Slot getSlot() { return slot; }
    public void setSlot(Slot slot) { this.slot = slot; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public LocalDateTime getTime() { return time; }
    public void setTime(LocalDateTime time) { this.time = time; }
}
