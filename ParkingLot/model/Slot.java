package model;

public class Slot {
    private String id;
    private VehicleType slotType;
    private boolean isOccupied;
    private int floor;

    public Slot(String id, VehicleType slotType, int floor) {
        this.id = id;
        this.slotType = slotType;
        this.floor = floor;
    }

    public int getFloor() { return floor; }
    public void setFloor(int floor) { this.floor = floor; }
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public VehicleType getSlotType() { return slotType; }
    public void setSlotType(VehicleType slotType) { this.slotType = slotType; }
    public boolean isOccupied() { return isOccupied; }
    public void setOccupied(boolean occupied) { isOccupied = occupied; }
}
