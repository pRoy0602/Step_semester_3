public class Q3 {
    static class ParkingSlot {
        String slotNo;
        int capacity, occupiedCount;
        ParkingSlot(String slotNo, int capacity, int occupiedCount) {
            this.slotNo = slotNo; this.capacity = capacity; this.occupiedCount = occupiedCount;
        }
        boolean allot(String vehicleNo) {
            if (occupiedCount < capacity) { occupiedCount++; return true; }
            return false;
        }
    }
    static ParkingSlot findAvailableSlot(ParkingSlot[] slots) {
        for (ParkingSlot s : slots)
            if (s != null && s.occupiedCount < s.capacity) return s;
        return null;
    }
    static void safeAllot(ParkingSlot[] slots, String vehicleNo) {
        ParkingSlot slot = findAvailableSlot(slots);
        if (slot == null)
            System.out.println("No slots available for " + vehicleNo);
        else {
            slot.allot(vehicleNo);
            System.out.println(vehicleNo + " allotted to slot " + slot.slotNo);
        }
    }
    public static void main(String[] args) {
        ParkingSlot[] slots = {new ParkingSlot("A1",4,3), new ParkingSlot("A2",5,5)};
        safeAllot(slots, "TN09AB1234");
        safeAllot(slots, "TN09AB1234");
        // Java passes object references by value; the array slots still refer to the same objects.
    }
}
