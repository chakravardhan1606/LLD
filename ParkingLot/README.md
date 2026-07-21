# Parking Lot LLD

Multi-floor parking lot supporting multiple vehicle types (bike, car, truck).
Vehicles are issued a ticket on entry, parked/un-parked from slots, and
charged a fee based on a pluggable strategy on exit.

## Package Structure

```
ParkingLot/
├── Main.java                              # Entry point - demo flow (entry, park, unpark, exit)
├── ParkingLot.java                         # Facade/singleton - orchestrates slot, ticket & fee logic
├── manager/
│   ├── SlotManager.java                    # Owns floor-wise slots, tracks availability
│   └── TicketManager.java                  # Issues and looks up tickets per vehicle
├── model/
│   ├── Vehicle.java (abstract), Car/Bike/Truck.java
│   ├── VehicleType.java                    # Enum: BIKE, CAR, TRUCK
│   ├── Slot.java                            # Parking slot state
│   └── Ticket.java                          # Entry ticket (time + slot)
└── strategy/
    ├── FeeCalculationStrategy.java          # Strategy interface
    ├── HourlyCalculationStrategy.java       # Flat hourly rate
    └── VehicleTypeCalculationStrategy.java  # Rate multiplier per vehicle type
```

## Class Diagram

![Parking Lot Class Diagram](class-diagram.png)

<details>
<summary>Mermaid source</summary>

```mermaid
classDiagram
    class Main {
        +main(String[] args) void
    }

    class ParkingLot {
        -SlotManager slotManager
        -TicketManager ticketManager
        -FeeCalculationStrategy feeCalculationStrategy
        -ParkingLot parkingLot$
        +getParkingLot()$ ParkingLot
        +entry(Vehicle vehicle) Ticket
        +Park(Vehicle vehicle, Slot slot) void
        +unPark(Vehicle vehicle, Slot slot) void
        +exit(Vehicle vehicle) void
        +addSlots(VehicleType type, int floor, int totSlots) void
    }

    class SlotManager {
        -Map~Integer, List~Slot~~ floorWiseSlots
        -Set~Slot~ availableSlots
        +addSlot(VehicleType type, int floor) void
        +removeSlot(Slot slot) void
        +getSlot(VehicleType type) Slot
        +setOccupied(Slot slot) void
        +setVacant(Slot slot) void
    }

    class TicketManager {
        -Map~String, Ticket~ tickets
        +generateTicket(String vehicleId, Slot slot) Ticket
        +getTicket(String vehicleId) Ticket
    }

    class FeeCalculationStrategy {
        <<interface>>
        +calculateFee(VehicleType type, double chargePerHour, LocalDateTime dateTime) double
    }

    class HourlyCalculationStrategy {
        +calculateFee(VehicleType type, double chargePerHour, LocalDateTime dateTime) double
    }

    class VehicleTypeCalculationStrategy {
        +calculateFee(VehicleType type, double chargePerHour, LocalDateTime dateTime) double
    }

    class Vehicle {
        <<abstract>>
        -String id
        -String address
        -VehicleType vehicleType
        +getId() String
        +getAddress() String
        +getVehicleType() VehicleType
    }

    class Car {
        +Car(String id, String address)
    }

    class Bike {
        +Bike(String id, String address)
    }

    class Truck {
        +Truck(String id, String address)
    }

    class VehicleType {
        <<enumeration>>
        BIKE
        CAR
        TRUCK
    }

    class Slot {
        -String id
        -VehicleType slotType
        -boolean isOccupied
        -int floor
        +getId() String
        +getSlotType() VehicleType
        +isOccupied() boolean
        +getFloor() int
    }

    class Ticket {
        -String id
        -LocalDateTime time
        -Slot slot
        +getId() String
        +getTime() LocalDateTime
        +getSlot() Slot
    }

    Main --> ParkingLot : uses
    ParkingLot --> SlotManager : owns
    ParkingLot --> TicketManager : owns
    ParkingLot --> FeeCalculationStrategy : uses
    HourlyCalculationStrategy ..|> FeeCalculationStrategy : implements
    VehicleTypeCalculationStrategy ..|> FeeCalculationStrategy : implements
    SlotManager --> Slot : manages
    TicketManager --> Ticket : manages
    Ticket --> Slot : references
    Car --|> Vehicle : extends
    Bike --|> Vehicle : extends
    Truck --|> Vehicle : extends
    Vehicle --> VehicleType : has
    Slot --> VehicleType : has
```

</details>

## Key Design Points

- **`ParkingLot`** is a singleton facade that coordinates `SlotManager`,
  `TicketManager`, and the fee calculation strategy.
- **`SlotManager`** (manager) owns floor-wise slot allocation and availability.
- **`TicketManager`** (manager) issues and retrieves tickets per vehicle.
- **`FeeCalculationStrategy`** (strategy pattern) decouples fee calculation
  from `ParkingLot`, allowing `HourlyCalculationStrategy` or
  `VehicleTypeCalculationStrategy` to be swapped in.
- **`Vehicle`** is an abstract base for `Car`, `Bike`, and `Truck`.
