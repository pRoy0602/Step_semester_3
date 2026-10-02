public class Q1 {
    static class BusTicket {
        private final String passengerName, destination;
        private boolean checkedIn;
        BusTicket(String passengerName, String destination) {
            if (!validName(passengerName) || !validText(destination))
                throw new IllegalArgumentException("Invalid booking");
            this.passengerName = passengerName.trim();
            this.destination = destination.trim();
        }
        private static boolean validText(String s) {
            return s != null && !s.trim().isEmpty();
        }
        private static boolean validName(String s) {
            if (!validText(s)) return false;
            for (char c : s.trim().toCharArray())
                if (!Character.isLetter(c) && c != ' ') return false;
            return true;
        }
        void markCheckedIn() {
            if (!checkedIn) checkedIn = true; // idempotent
        }
        static void processBatch(String[][] rawBookings) {
            int valid=0,rejected=0,duplicates=0;
            String[][] accepted=new String[rawBookings.length][2];
            for (String[] row:rawBookings) {
                try {
                    if(row==null || row.length<2) throw new IllegalArgumentException();
                    BusTicket t=new BusTicket(row[0],row[1]);
                    boolean duplicate=false;
                    for(int i=0;i<valid;i++)
                        if(accepted[i][0].equals(t.passengerName) && accepted[i][1].equals(t.destination))
                            duplicate=true;
                    if(duplicate) duplicates++;
                    else {accepted[valid][0]=t.passengerName;accepted[valid][1]=t.destination;valid++;}
                } catch(IllegalArgumentException e) { rejected++; }
            }
            System.out.println("Valid: "+valid+" | Rejected: "+rejected+" | Duplicates skipped: "+duplicates);
        }
    }
    public static void main(String[] args) {
        BusTicket.processBatch(new String[][]{
            {"Divya","Chennai"},{"","Bangalore"},{"Ravi123","Pune"},{"Divya","Chennai"},{"    ","   "}
        });
    }
}
