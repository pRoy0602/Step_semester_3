public class Q5 {
    static class RaceEntry {
        private static int bibCounter;
        private final String entryCode;
        protected final String bibNumber;
        protected final double entryFee;
        protected double paid;
        RaceEntry(String b,double f){
            if(b==null||b.trim().length()<4||f<=0)throw new IllegalArgumentException();
            bibNumber=b;entryFee=f;entryCode="ENTRY-"+(++bibCounter);
        }
        void pay(double amount){paid+=amount;}
        void pay(double amount,String mode){pay(amount);System.out.println("Paying via "+mode);}
        static boolean isValidDiscountCode(String code){
            if(code==null||code.length()!=5)return false;
            if(code.charAt(0)!='M'||!Character.isUpperCase(code.charAt(4)))return false;
            for(int i=1;i<=3;i++)if(!Character.isDigit(code.charAt(i)))return false;
            return true;
        }
        static int getBibCounter(){return bibCounter;}
    }
    static class EliteRunnerEntry extends RaceEntry {
        EliteRunnerEntry(String b,double f){super(b,f);}
    }
    static class RelayTeamEntry extends RaceEntry {
        private final int teamSize;
        RelayTeamEntry(String b,double f,int s){super(b,f);if(s<=0)throw new IllegalArgumentException();teamSize=s;}
        int getTeamSize(){return teamSize;}
    }
    static String settleNight(RaceEntry[] entries){
        int processed=0,nulls=0,relay=0,individual=0;
        for(RaceEntry e:entries){
            if(e==null){nulls++;continue;}
            processed++;
            if(e instanceof RelayTeamEntry)relay++;else individual++;
        }
        return processed+" processed | "+nulls+" null skipped | "+relay+" relay | "+individual+" individual";
    }
    public static void main(String[] args){
        System.out.println(RaceEntry.isValidDiscountCode("M123A"));
        System.out.println(RaceEntry.isValidDiscountCode("M12A"));
        System.out.println(RaceEntry.isValidDiscountCode("X123A"));
        RaceEntry r=new RaceEntry("BIB5001",50);r.pay(10,"UPI");
        System.out.println(settleNight(new RaceEntry[]{new EliteRunnerEntry("BIB3001",150),null,new RelayTeamEntry("BIB4001",300,4)}));
    }
}
