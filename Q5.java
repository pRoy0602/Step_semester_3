public class Q5 {
    static class BusTicketAccount {
        private static int nextId;
        static { nextId = 1000; }
        protected final String bookingId;
        protected final double ticketFare;
        protected double balancePaid;
        BusTicketAccount(String bookingId,double ticketFare){
            if(bookingId==null || bookingId.trim().isEmpty() || ticketFare<=0)
                throw new IllegalArgumentException("Invalid account");
            this.bookingId=bookingId;this.ticketFare=ticketFare;
        }
        BusTicketAccount(String bookingId){this(bookingId,0.01);}
        final double calculatePenalty(int minutesLate){
            if(minutesLate<0)throw new IllegalArgumentException("Negative minutes");
            return minutesLate * 10.0; // flat fallback allowed by the assignment
        }
        void processAccount(BusTicketAccount account,double amount,int minutesLate){
            if(account==null) return;
            if(amount<0)throw new IllegalArgumentException("Negative amount");
            account.balancePaid += amount;
            double penalty=account.calculatePenalty(minutesLate);
            System.out.println("Processed "+account.bookingId+" | penalty="+penalty);
        }
        static void processBatch(BusTicketAccount[] accounts,double[] amounts,int[] minutesLateArray){
            if(accounts==null || amounts==null || minutesLateArray==null ||
               accounts.length!=amounts.length || accounts.length!=minutesLateArray.length)
                throw new IllegalArgumentException("Parallel arrays must have equal lengths");
            int processed=0,nulls=0,sleeper=0,regular=0; double total=0;
            BusTicketAccount processor=new BusTicketAccount("PROCESSOR",0.01);
            for(int i=0;i<accounts.length;i++){
                if(accounts[i]==null){nulls++;continue;}
                processor.processAccount(accounts[i],amounts[i],minutesLateArray[i]);
                total+=accounts[i].calculatePenalty(minutesLateArray[i]);
                processed++;
                if(accounts[i] instanceof Sleeper) sleeper++; else regular++;
            }
            System.out.printf("%d processed | %d null skipped | %d sleeper | %d regular | grand total penalties = %.2f%n",
                    processed,nulls,sleeper,regular,total);
        }
    }
    static class Sleeper extends BusTicketAccount {
        Sleeper(String id,double fare){super(id,fare);}
        @Override void processAccount(BusTicketAccount account,double amount,int minutesLate){
            // The source specifies a separate sleeper settlement path but gives no
            // different numeric rate, so the documented penalty rule is retained.
            super.processAccount(account,amount,minutesLate);
        }
    }
    public static void main(String[] args){
        BusTicketAccount[] a={new Sleeper("BK001",2000),null,new BusTicketAccount("BK002",1200)};
        double[] amounts={1200,900,700}; int[] late={10,5,0};
        BusTicketAccount.processBatch(a,amounts,late);
    }
}
