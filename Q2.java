import java.util.*;

public class Q2 {
    enum Status { BOOKED,PICKED_UP,IN_TRANSIT,OUT_FOR_DELIVERY,DELIVERED,CANCELLED }
    interface ShippingType { double charge(double kg); String name(); }
    static class Standard implements ShippingType {
        public double charge(double kg){return 40+10*kg;} public String name(){return "Standard";}
    }
    static class Express implements ShippingType {
        public double charge(double kg){return 80+15*kg;} public String name(){return "Express";}
    }
    static class Fragile implements ShippingType {
        private final ShippingType standard=new Standard();
        public double charge(double kg){return standard.charge(kg)+50;} public String name(){return "Fragile";}
    }
    interface NotificationChannel { void notify(String parcelId,Status status); }
    static class SmsChannel implements NotificationChannel {
        public void notify(String id,Status s){System.out.println("[SMS] "+id+" is now "+s);}
    }
    static class EmailChannel implements NotificationChannel {
        public void notify(String id,Status s){System.out.println("[Email] "+id+" is now "+s);}
    }
    static class Customer { final String name; Customer(String n){name=n;} }
    static class Parcel {
        final String id; final Customer customer; final double kg; final ShippingType shippingType;
        Status status=Status.BOOKED; final List<NotificationChannel> channels=new ArrayList<>();
        Parcel(String id,Customer c,double kg,ShippingType type){this.id=id;customer=c;this.kg=kg;shippingType=type;}
        void subscribe(NotificationChannel c){channels.add(c);}
        double charge(){return shippingType.charge(kg);}
        void changeStatus(Status next){
            boolean valid=(status==Status.BOOKED&&next==Status.PICKED_UP)||
                (status==Status.PICKED_UP&&next==Status.IN_TRANSIT)||
                (status==Status.IN_TRANSIT&&next==Status.OUT_FOR_DELIVERY)||
                (status==Status.OUT_FOR_DELIVERY&&next==Status.DELIVERED);
            if(!valid)throw new IllegalStateException("Invalid transition: "+status+" → "+next+" is not allowed.");
            status=next; for(NotificationChannel c:channels)c.notify(id,status);
        }
        void cancel(){if(status!=Status.BOOKED)throw new IllegalStateException(id+" can be cancelled only while BOOKED.");status=Status.CANCELLED;}
    }
    public static void main(String[] args){
        Parcel p=new Parcel("P101",new Customer("Customer"),2,new Express());
        p.subscribe(new SmsChannel());p.subscribe(new EmailChannel());
        System.out.printf("Parcel P101 booked (Express, 2 kg). Charge: ₹%.2f%n",p.charge());
        for(NotificationChannel c:p.channels)c.notify(p.id,p.status);
        p.changeStatus(Status.PICKED_UP);
        try{p.cancel();}catch(Exception e){System.out.println("Cancellation failed: "+e.getMessage());}
        p.changeStatus(Status.IN_TRANSIT);
        try{p.changeStatus(Status.DELIVERED);}catch(Exception e){System.out.println(e.getMessage());}
    }
}
