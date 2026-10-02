import java.util.*;

public class Q5 {
    interface PricingPlan { double price(double fullPrice); String name(); }
    static class DayScholarPlan implements PricingPlan {public double price(double p){return p;}public String name(){return "Day Scholar";}}
    static class HostellerPlan implements PricingPlan {public double price(double p){return p*0.90;}public String name(){return "Hosteller";}}
    static class StaffPlan implements PricingPlan {public double price(double p){return p*0.80;}public String name(){return "Staff";}}
    static class Transaction {
        final double amount; final String description;
        Transaction(double amount,String description){this.amount=amount;this.description=description;}
    }
    static class SmartCard {
        final String id; final PricingPlan plan; private double balance; private boolean blocked;
        private final List<Transaction> transactions=new ArrayList<>();
        private final Set<String> refunded=new HashSet<>();
        SmartCard(String id,PricingPlan plan){this.id=id;this.plan=plan;}
        void topUp(double amount){
            if(blocked)throw new IllegalStateException("Card is blocked");
            if(amount<100)throw new IllegalArgumentException("Minimum top-up is ₹100");
            if(balance+amount>5000)throw new IllegalArgumentException("Maximum balance is ₹5000");
            addTransaction(amount,"Top up");
        }
        double purchase(String item,double fullPrice){
            if(blocked)throw new IllegalStateException("Card is blocked");
            double charged=plan.price(fullPrice);
            if(charged>balance)throw new IllegalStateException("Insufficient balance (required ₹"+String.format("%.2f",charged)+", available ₹"+String.format("%.2f",balance)+")");
            addTransaction(-charged,item);
            return charged;
        }
        void refund(String item,double charged){
            if(refunded.contains(item))throw new IllegalStateException(item+" has already been refunded.");
            refunded.add(item);addTransaction(charged,"Refund "+item);
        }
        void block(){blocked=true;} void unblock(){blocked=false;}
        void addTransaction(double amount,String description){balance+=amount;transactions.add(new Transaction(amount,description));}
        String miniStatement(){
            StringBuilder b=new StringBuilder("Mini-statement for "+id+": ");
            for(int i=0;i<transactions.size();i++){
                if(i>0)b.append(", ");
                b.append(String.format("%+.2f",transactions.get(i).amount));
            }
            b.append(" = ₹").append(String.format("%.2f",balance));
            return b.toString();
        }
    }
    public static void main(String[] args){
        SmartCard c=new SmartCard("C-2045",new HostellerPlan());
        c.topUp(500);System.out.println("C-2045 topped up with ₹500.00. Balance: ₹500.00.");
        double v=c.purchase("Veg Thali",120);System.out.printf("Veg Thali purchased for ₹%.2f. Balance: ₹%.2f.%n",v,c.balance);
        double coffee=c.purchase("Cold Coffee",60);System.out.printf("Cold Coffee purchased for ₹%.2f. Balance: ₹%.2f.%n",coffee,c.balance);
        try{c.purchase("items",400);}catch(Exception e){System.out.println("Purchase failed: "+e.getMessage()+".");}
        c.refund("Veg Thali",v);System.out.printf("Refund of ₹%.2f for Veg Thali processed. Balance: ₹%.2f.%n",v,c.balance);
        try{c.refund("Veg Thali",v);}catch(Exception e){System.out.println("Refund rejected: "+e.getMessage());}
        System.out.println(c.miniStatement());
    }
}
