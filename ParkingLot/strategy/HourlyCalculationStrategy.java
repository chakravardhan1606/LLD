package strategy;

import model.VehicleType;

import java.time.LocalDateTime;

public class HourlyCalculationStrategy implements FeeCalculationStrategy {
    public double calculateFee(VehicleType type, double chargePerHour, LocalDateTime dateTime) {
        LocalDateTime curr = LocalDateTime.now();
        double hours = (curr.getHour() - dateTime.getHour()) + 3;
        return hours * chargePerHour;
    }
}
