
public class Q1 {
    static class RaceEntry {
        protected final String bibNumber;
        protected final double entryFee;
        protected double paid;
        private final double[] lateFeeHistory = new double[10];
        private int feeCount;

        RaceEntry(String bibNumber, double entryFee) {
            if (bibNumber == null || bibNumber.trim().length() < 4 || entryFee <= 0)
                throw new IllegalArgumentException("Invalid entry");
            this.bibNumber = bibNumber;
            this.entryFee = entryFee;
        }
        void pay(double amount) { if (amount < 0) throw new IllegalArgumentException(); paid += amount; }
        double getBalanceDue() { return entryFee - paid; }
        protected void applyLateFee(double amount) {
            if (feeCount >= lateFeeHistory.length) throw new IllegalStateException("History full");
            paid -= amount;
            lateFeeHistory[feeCount++] = amount;
        }
        double[] getLateFeeHistory() {
            double[] copy = new double[feeCount];
            System.arraycopy(lateFeeHistory, 0, copy, 0, feeCount);
            return copy;
        }
    }

    static class RunnerEntry extends RaceEntry {
        private final String category;
        RunnerEntry(String bibNumber, double entryFee, String category) {
            super(bibNumber, entryFee);
            this.category = category;
        }
        @Override protected void applyLateFee(double amount) {
            super.applyLateFee(amount * 2);
        }
    }

    public static void main(String[] args) {
        RunnerEntry r = new RunnerEntry("BIB2001", 80, "Open 10K");
        r.pay(30);
        r.applyLateFee(20);
        System.out.println(r.getBalanceDue()); // 90.0
        double[] history = r.getLateFeeHistory();
        history[0] = 999;
        System.out.println(r.getLateFeeHistory()[0]); // 40.0
    }
}
