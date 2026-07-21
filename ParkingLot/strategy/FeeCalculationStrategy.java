package strategy;

import model.VehicleType;

import java.time.LocalDateTime;

public interface FeeCalculationStrategy {
    double calculateFee(VehicleType type, double chargePerHour, LocalDateTime dateTime);
}
