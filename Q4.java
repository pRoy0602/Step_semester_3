public class Q4 {
    static final class BoardingPenaltyCalculator {
        private final double minimumPenaltyPercent;
        BoardingPenaltyCalculator(double minimumPenaltyPercent){
            if(minimumPenaltyPercent<0)throw new IllegalArgumentException("Invalid minimum rate");
            this.minimumPenaltyPercent=minimumPenaltyPercent;
        }
        public final double calculatePenalty(double ticketFare,int minutesLate){
            if(ticketFare<0 || minutesLate<0)throw new IllegalArgumentException("Negative input");
            if(minutesLate==0)return 0.0;
            int m1=Math.min(minutesLate,5);
            int m2=Math.min(Math.max(minutesLate-5,0),10);
            int m3=Math.max(minutesLate-15,0);
            double tiered=ticketFare*(m1*0.005+m2*0.01+m3*0.02);
            double floor=ticketFare*minimumPenaltyPercent/100.0;
            return Math.max(tiered,floor);
        }
    }
    public static void main(String[] args){
        BoardingPenaltyCalculator c=new BoardingPenaltyCalculator(1.0);
        System.out.println("Rs "+c.calculatePenalty(1000,0));
        System.out.println("Rs "+c.calculatePenalty(1000,1));
        System.out.println("Rs "+c.calculatePenalty(1000,16));
    }
}
