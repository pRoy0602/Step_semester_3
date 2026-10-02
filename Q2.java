import java.util.Arrays;

public class Q2 {
    static class FareSplitter {
        private String tripId;
        private double totalFare;
        private int passengerCount;

        FareSplitter(String tripId, double totalFare, int passengerCount) {
            if (totalFare < 0 || passengerCount <= 0)
                throw new IllegalArgumentException("Invalid split");
            this.tripId=tripId; this.totalFare=totalFare; this.passengerCount=passengerCount;
        }
        FareSplitter(String tripId, double totalFare) { this(tripId,totalFare,2); }
        FareSplitter(String tripId) { this(tripId,0.0,2); }

        double[] fareBreakdown() {
            double[] result=new double[passengerCount];
            if (passengerCount==0) return result;
            long cents=Math.round(totalFare*100);
            long base=cents/passengerCount, rem=cents%passengerCount;
            for(int i=0;i<passengerCount;i++)
                result[i]=(base + (i==passengerCount-1 ? rem : 0))/100.0;
            return result;
        }
        boolean isConfirmationOverdue(int confirmed,int expected) {
            return confirmed < expected;
        }
    }
    public static void main(String[] args) {
        System.out.println(Arrays.toString(new FareSplitter("TRIP001",100000,3).fareBreakdown()));
        System.out.println(Arrays.toString(new FareSplitter("TRIP003").fareBreakdown()));
    }
}
