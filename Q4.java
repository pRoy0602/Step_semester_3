public class Q4 {
    static class RaceEntry {
        protected final String bibNumber;
        protected final double entryFee;
        protected double paid;
        RaceEntry(String b,double f){if(b==null||b.trim().length()<4||f<=0)throw new IllegalArgumentException();bibNumber=b;entryFee=f;}
        void pay(double amount){paid+=amount;}
        double getBalanceDue(){return entryFee-paid;}
        String announce(){return "Race Entry | Bib: "+bibNumber+" | Balance: "+getBalanceDue();}
    }
    static class RunnerEntry extends RaceEntry {
        private final String category;
        RunnerEntry(String b,double f,String c){super(b,f);category=c;}
        @Override String announce(){return "Runner Entry | Bib: "+bibNumber+" | Category: "+category+" | Balance: "+getBalanceDue();}
    }
    static class RelayTeamEntry extends RaceEntry {
        private final int teamSize;
        RelayTeamEntry(String b,double f,int s){super(b,f);teamSize=s;}
        int getTeamSize(){return teamSize;}
        @Override String announce(){return "Relay Team | Bib: "+bibNumber+" | Team Size: "+teamSize+" | Balance: "+getBalanceDue();}
    }
    static String announceAll(RaceEntry[] entries){
        StringBuilder report=new StringBuilder();
        for(RaceEntry e:entries){
            report.append(e.announce()).append(" ");
            if(e instanceof RelayTeamEntry){
                int size=((RelayTeamEntry)e).getTeamSize();
                report.append("[Team size via downcast: ").append(size).append("]");
            }
            report.append(" | ");
        }
        return report.toString();
    }
    public static void main(String[] args){
        RaceEntry[] fleet={new RunnerEntry("BIB2001",80,"Open 10K"),new RelayTeamEntry("BIB4001",300,4)};
        System.out.println(announceAll(fleet));
    }
}
