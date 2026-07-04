package strategy;

import model.VehicleType;

import java.time.LocalDateTime;

public class VehicleTypeCalculationStrategy implements FeeCalculationStrategy {
    public double calculateFee(VehicleType type, double chargePerHour, LocalDateTime dateTime) {
        LocalDateTime curr = LocalDateTime.now();
        double hours = curr.getHour() - dateTime.getHour();
        if (type == VehicleType.CAR) return hours * chargePerHour * 2;
        if (type == VehicleType.TRUCK) return hours * chargePerHour * 3;
        return hours * chargePerHour * 1;
    }
}
