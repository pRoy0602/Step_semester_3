public class Q2 {
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
        protected final String category;
        RunnerEntry(String b,double f,String c){super(b,f);category=c;}
        String announce(){return "Runner Entry | Bib: "+bibNumber+" | Category: "+category+" | Balance: "+getBalanceDue();}
    }
    static class EliteRunnerEntry extends RunnerEntry {
        private final double sponsorBonus;
        EliteRunnerEntry(String b,double f,String c,double bonus){super(b,f,c);sponsorBonus=bonus;}
        String announce(){return "Elite Runner | Bib: "+bibNumber+" | Category: "+category+" | Sponsor Bonus: "+sponsorBonus+" | Balance: "+getBalanceDue();}
    }
    static class RelayTeamEntry extends RaceEntry {
        private final int teamSize;
        RelayTeamEntry(String b,double f,int size){super(b,f);if(size<=0)throw new IllegalArgumentException();teamSize=size;}
        String announce(){return "Relay Team | Bib: "+bibNumber+" | Team Size: "+teamSize+" | Balance: "+getBalanceDue();}
    }
    static String classifyGeneration(RaceEntry e){
        if(e instanceof EliteRunnerEntry)return "Multilevel descendant (3 generations deep)";
        if(e instanceof RelayTeamEntry)return "Hierarchical sibling (independent branch)";
        if(e instanceof RunnerEntry)return "Single-inheritance descendant";
        return "Base class";
    }
    static double getTotalBalanceDue(RaceEntry[] entries){
        double total=0;
        for(RaceEntry e:entries) total+=e.getBalanceDue();
        return total;
    }
    public static void main(String[] args){
        RunnerEntry r=new RunnerEntry("BIB2001",80,"Open 10K");
        EliteRunnerEntry e=new EliteRunnerEntry("BIB3001",150,"Elite Full Marathon",500);
        RelayTeamEntry t=new RelayTeamEntry("BIB4001",300,4);
        System.out.println(r.announce());System.out.println(e.announce());System.out.println(t.announce());
        System.out.println(classifyGeneration(e));
        System.out.println(classifyGeneration(t));
        System.out.println(getTotalBalanceDue(new RaceEntry[]{r,e,t}));
    }
}
