import manager.SlotManager;
import manager.TicketManager;
import model.*;
import strategy.FeeCalculationStrategy;
import strategy.HourlyCalculationStrategy;

public class ParkingLot {
    private SlotManager slotManager;
    private TicketManager ticketManager;
    private FeeCalculationStrategy feeCalculationStrategy;
    private static ParkingLot parkingLot;

    private ParkingLot() {
        slotManager = new SlotManager();
        feeCalculationStrategy = new HourlyCalculationStrategy();
        ticketManager = new TicketManager();
    }

    public static ParkingLot getParkingLot() {
        if (parkingLot == null) parkingLot = new ParkingLot();
        return parkingLot;
    }

    public Ticket entry(Vehicle vehicle) {
        Slot slot = slotManager.getSlot(vehicle.getVehicleType());
        return ticketManager.generateTicket(vehicle.getId(), slot);
    }

    public void Park(Vehicle vehicle, Slot slot) {
        slotManager.setOccupied(slot);
    }

    public void unPark(Vehicle vehicle, Slot slot) {
        slotManager.setVacant(slot);
    }

    public void exit(Vehicle vehicle) {
        Ticket ticket = ticketManager.getTicket(vehicle.getId());
        double amount = feeCalculationStrategy.calculateFee(vehicle.getVehicleType(), 5.0, ticket.getTime());
        System.out.println("Total fee: " + amount);
    }

    public void addSlots(VehicleType type, int floor, int totSlots) {
        for (int i = 0; i < totSlots; i++)
            slotManager.addSlot(type, floor);
    }
}
