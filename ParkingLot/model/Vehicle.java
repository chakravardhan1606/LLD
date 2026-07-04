package model;

public abstract class Vehicle {
    private String id;
    private String address;
    private VehicleType vehicleType;

    public Vehicle(String id, String address, VehicleType vehicleType) {
        this.id = id;
        this.address = address;
        this.vehicleType = vehicleType;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public VehicleType getVehicleType() { return vehicleType; }
    public void setVehicleType(VehicleType vehicleType) { this.vehicleType = vehicleType; }
}
