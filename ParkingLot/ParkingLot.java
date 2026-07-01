import java.util.*;

enum VehicleType {
    CAR, BIKE, TRUCK
}

abstract class Vehicle {

    private String vehicleId;
    private String regNum;
    private VehicleType type;

    public Vehicle(String vehicleId, String regNum, VehicleType type) {
        this.vehicleId = vehicleId;
        this.regNum = regNum;
        this.type = type;
    }

    public VehicleType getType() {
        return type;
    }
}

class Bike extends Vehicle {
    public Bike(String vehicleId, String regNum) {
        super(vehicleId, regNum, VehicleType.BIKE);
    }
}

class Car extends Vehicle {
    public Car(String vehicleId, String regNum) {
        super(vehicleId, regNum, VehicleType.CAR);
    }
}

class Truck extends Vehicle {
    public Truck(String vehicleId, String regNum) {
        super(vehicleId, regNum, VehicleType.TRUCK);
    }
}

class Slot {

    private String slotId;
    private VehicleType slotType;
    private int floorId;
    private boolean occupied;

    public Slot(String slotId, VehicleType slotType, int floorId) {
        this.slotId = slotId;
        this.slotType = slotType;
        this.floorId = floorId;
    }

    public String getSlotId() {
        return slotId;
    }

    public VehicleType getSlotType() {
        return slotType;
    }

    public int getFloorId() {
        return floorId;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }
}

class Floor {

    private String id;
    private List<Slot> slots;
    private int capacity;

    public Floor(String id, int capacity) {
        this.id = id;
        this.capacity = capacity;
        this.slots = new ArrayList<>();
    }
}

class Ticket {

    private String id;
    private Date date;
    private Vehicle vehicle;
    private Slot slot;

    public Ticket(String id, Vehicle vehicle, Slot slot) {
        this.id = id;
        this.vehicle = vehicle;
        this.slot = slot;
        this.date = new Date();
    }
}

class ParkingLot {

    public Ticket getTicket(Vehicle vehicle) {

        Slot slot = SlotManager.getNearestSlot(vehicle);

        if (slot == null) {
            return null;
        }

        SlotManager.setOccupied(slot);

        return TicketManager.getTicket(vehicle, slot);
    }

    public void unPark(Slot slot) {
        SlotManager.setFree(slot);
    }
}

class SlotManager {

    static TreeMap<Integer, List<Slot>> floorWiseSlots = new TreeMap<>();
    static HashMap<String, Slot> totalSlots = new HashMap<>();
    static HashSet<Slot> vacantSlots = new HashSet<>();

    public static Slot getNearestSlot(Vehicle vehicle) {

        List<Slot> sortedSlots = new ArrayList<>(vacantSlots);

        sortedSlots.sort(
                Comparator.comparingInt(Slot::getFloorId)
                          .thenComparing(Slot::getSlotId)
        );

        for (Slot slot : sortedSlots) {
            if (slot.getSlotType() == vehicle.getType()) {
                return slot;
            }
        }

        return null;
    }

    public static void setOccupied(Slot slot) {
        slot.setOccupied(true);
        vacantSlots.remove(slot);
    }

    public static void setFree(Slot slot) {
        slot.setOccupied(false);
        vacantSlots.add(slot);
    }

    public static void displayFreeCount(VehicleType type) {

        for (Map.Entry<Integer, List<Slot>> entry : floorWiseSlots.entrySet()) {

            int count = 0;

            for (Slot slot : entry.getValue()) {
                if (slot.getSlotType() == type && !slot.isOccupied()) {
                    count++;
                }
            }

            System.out.println(
                    "No. of free slots for " + type +
                    " on Floor " + entry.getKey() + ": " + count);
        }
    }
}

class TicketManager {

    public static Ticket getTicket(Vehicle vehicle, Slot slot) {
        return new Ticket(UUID.randomUUID().toString(), vehicle, slot);
    }
}
