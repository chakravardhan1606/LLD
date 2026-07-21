import model.*;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        ParkingLot parkingLot = ParkingLot.getParkingLot();
        parkingLot.addSlots(VehicleType.CAR, 1, 3);
        parkingLot.addSlots(VehicleType.BIKE, 1, 3);

        Vehicle car = new Car(UUID.randomUUID().toString(), "AP217467");
        Ticket ticket = parkingLot.entry(car);
        parkingLot.Park(car, ticket.getSlot());
        parkingLot.unPark(car, ticket.getSlot());
        parkingLot.exit(car);
    }
}
