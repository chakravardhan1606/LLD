package manager;

import model.Slot;
import model.VehicleType;

import java.util.*;

public class SlotManager {
    private Map<Integer, List<Slot>> floorWiseSlots;
    private Set<Slot> availableSlots;

    public SlotManager() {
        floorWiseSlots = new HashMap<>();
        availableSlots = new HashSet<>();
    }

    public void addSlot(VehicleType type, int floor) {
        Slot slot = new Slot(UUID.randomUUID().toString(), type, floor);
        if (floorWiseSlots.containsKey(floor)) {
            List<Slot> slots = floorWiseSlots.get(floor);
            slots.add(slot);
            floorWiseSlots.put(floor, slots);
        } else {
            List<Slot> res = new ArrayList<>();
            res.add(slot);
            floorWiseSlots.put(floor, res);
        }
        availableSlots.add(slot);
    }

    public void removeSlot(Slot slot) {
        List<Slot> slots = floorWiseSlots.get(slot.getFloor());
        slots.removeIf(s -> s.getId().equals(slot.getId()));
        floorWiseSlots.put(slot.getFloor(), slots);
        availableSlots.remove(slot);
    }

    public Slot getSlot(VehicleType type) {
        for (Slot s : availableSlots) {
            if (s.getSlotType() == type) return s;
        }
        return null;
    }

    public void setOccupied(Slot slot) {
        slot.setOccupied(true);
        availableSlots.remove(slot);
    }

    public void setVacant(Slot slot) {
        availableSlots.add(slot);
        slot.setOccupied(false);
    }
}
