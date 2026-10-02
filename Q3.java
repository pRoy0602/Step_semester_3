public class Q3 {
    static abstract class ServiceableVehicle {
        private double mileage;
        public abstract String performMaintenance();
        double getMileage(){return mileage;}
        void addMileage(double km){if(km<0)throw new IllegalArgumentException("Distance cannot be negative");mileage+=km;}
    }
    interface Insurable { String getInsuranceInfo(); }
    static class Forklift extends ServiceableVehicle implements Insurable {
        protected final String assetTag;
        Forklift(String assetTag){this.assetTag=assetTag;}
        public String performMaintenance(){return "Forklift "+assetTag+": hydraulic and fork inspection complete";}
        public String getInsuranceInfo(){return "Insured under fleet policy - Asset "+assetTag;}
    }
    static class HeavyDutyForklift extends Forklift {
        HeavyDutyForklift(String assetTag){super(assetTag);}
        @Override public String performMaintenance(){
            return super.performMaintenance()+" | high-pressure hydraulic check complete";
        }
    }
    static String getInsuranceIfApplicable(ServiceableVehicle v){
        if(v instanceof Insurable)return ((Insurable)v).getInsuranceInfo();
        return "No insurance record exists";
    }
    public static void main(String[] args){
        Forklift f=new Forklift("FL-22");f.addMileage(120);
        System.out.println(f.getMileage());System.out.println(f.performMaintenance());
        HeavyDutyForklift hd=new HeavyDutyForklift("HD-9");
        System.out.println(hd.performMaintenance());
        System.out.println(getInsuranceIfApplicable(f));
    }
}
