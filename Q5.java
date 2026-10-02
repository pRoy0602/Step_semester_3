public class Q5 {
    static abstract class HomeDevice {
        private static int counter=1000;
        private final String serialNumber;
        HomeDevice(){serialNumber="HD-"+(++counter);}
        abstract String activate();
        String getSerialNumber(){return serialNumber;}
    }
    interface RemoteControllable { String connect(String appId); }
    interface EnergyTrackable { double getConsumptionWatts(); }

    static class WashingMachine extends HomeDevice implements RemoteControllable,EnergyTrackable {
        private final double consumptionWatts;
        WashingMachine(double watts){if(watts<=0)throw new IllegalArgumentException();consumptionWatts=watts;}
        String activate(){return "Washing machine "+getSerialNumber()+" started a cycle";}
        public String connect(String appId){return getSerialNumber()+" connected to "+appId;}
        public double getConsumptionWatts(){return consumptionWatts;}
    }
    static class Refrigerator extends HomeDevice implements EnergyTrackable {
        private final double consumptionWatts;
        Refrigerator(double watts){if(watts<=0)throw new IllegalArgumentException();consumptionWatts=watts;}
        String activate(){return "Refrigerator "+getSerialNumber()+" started cooling";}
        public double getConsumptionWatts(){return consumptionWatts;}
    }
    static class MobileApp implements RemoteControllable {
        private final String appName;
        MobileApp(String appName){if(appName==null||appName.trim().isEmpty())throw new IllegalArgumentException();this.appName=appName;}
        public String connect(String appId){return appName+" connected to "+appId;}
    }
    static void connectAll(RemoteControllable[] items,String appId){for(RemoteControllable x:items)System.out.println(x.connect(appId));}
    static double getConsumptionIfTrackable(HomeDevice d){
        if(d instanceof EnergyTrackable)return ((EnergyTrackable)d).getConsumptionWatts();
        return 0.0;
    }
    public static void main(String[] args){
        WashingMachine wm=new WashingMachine(500);System.out.println(wm.activate());System.out.println(wm.connect("HomeConnect"));
        Refrigerator fridge=new Refrigerator(150);System.out.println(getConsumptionIfTrackable(fridge));
        MobileApp app=new MobileApp("HomeConnect App");System.out.println(app.connect("HomeConnect"));
        System.out.println(getConsumptionIfTrackable(wm));
    }
}
