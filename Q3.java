public class Q3 {
    static class RaceEntry {
        protected final String bibNumber;
        protected final double entryFee;
        protected double paid;
        RaceEntry(String b,double f){if(b==null||b.trim().length()<4||f<=0)throw new IllegalArgumentException();bibNumber=b;entryFee=f;}
        void pay(double amount){paid+=amount;}
        double getBalanceDue(){return entryFee-paid;}
        protected void applyLateFee(double amount){paid-=amount;}
    }
    static class RunnerEntry extends RaceEntry {
        private final double[] history=new double[10];
        private int count;
        RunnerEntry(String b,double f,String c){super(b,f);}
        @Override protected void applyLateFee(double amount){
            super.applyLateFee(amount*2);
            history[count++]=amount*2;
        }
        double[] getLateFeeHistory(){return history.clone();}
    }
    public static void main(String[] args){
        RunnerEntry r=new RunnerEntry("BIB2001",80,"Open 10K");
        r.pay(30);r.applyLateFee(20);
        System.out.println(r.getBalanceDue());
        System.out.println(r.getLateFeeHistory()[0]);
    }
}
