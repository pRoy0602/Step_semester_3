import java.util.*;

public class Q3 {
    interface Capability { String name(); void apply(Device d,int value); }
    static class Device {
        final String name; private final Map<String,Capability> capabilities=new LinkedHashMap<>();
        Device(String name){this.name=name;}
        void addCapability(Capability c){capabilities.put(c.name(),c);}
        boolean has(String name){return capabilities.containsKey(name);}
        void apply(String capability,int value){Capability c=capabilities.get(capability);if(c!=null)c.apply(this,value);}
    }
    static class PowerCapability implements Capability {
        public String name(){return "Power";}
        public void apply(Device d,int value){if(value!=0&&value!=1)throw new IllegalArgumentException("Power must be 0 or 1");System.out.println(d.name+": "+(value==1?"ON":"OFF"));}
    }
    static class BrightnessCapability implements Capability {
        public String name(){return "Brightness";}
        public void apply(Device d,int value){if(value<0||value>100)throw new IllegalArgumentException(d.name+": Brightness must be between 0% and 100%.");System.out.println(d.name+": brightness set to "+value+"%.");}
    }
    static class TemperatureCapability implements Capability {
        public String name(){return "Temperature";}
        public void apply(Device d,int value){if(value<16||value>30)throw new IllegalArgumentException(d.name+": temperature must be between 16°C and 30°C.");System.out.println(d.name+": temperature set to "+value+"°C.");}
    }
    static class SceneStep {
        final String capability; final int value;
        SceneStep(String capability,int value){this.capability=capability;this.value=value;}
        int execute(List<Device> devices){
            int count=0;
            for(Device d:devices)if(d.has(capability)){d.apply(capability,value);count++;}
            return count;
        }
    }
    static class Scene {
        final String name; final List<SceneStep> steps=new ArrayList<>();
        Scene(String name){this.name=name;}
        void addStep(SceneStep s){steps.add(s);}
        void execute(List<Device> devices){
            System.out.println("Scene '"+name+"' started.");
            int total=0;for(SceneStep s:steps)total+=s.execute(devices);
            System.out.println("Scene '"+name+"' completed: "+total+" actions applied.");
        }
    }
    public static void main(String[] args){
        Device ac=new Device("Lab AC");ac.addCapability(new PowerCapability());ac.addCapability(new TemperatureCapability());
        Device lights=new Device("Ceiling Lights");lights.addCapability(new PowerCapability());lights.addCapability(new BrightnessCapability());
        Device projector=new Device("Projector");projector.addCapability(new PowerCapability());
        List<Device> devices=Arrays.asList(ac,lights,projector);
        Scene lecture=new Scene("Lecture Mode");lecture.addStep(new SceneStep("Power",1));lecture.addStep(new SceneStep("Brightness",40));lecture.addStep(new SceneStep("Temperature",24));
        lecture.execute(devices);
        try{ac.apply("Temperature",12);}catch(Exception e){System.out.println("Rejected: "+e.getMessage());}
        projector.addCapability(new BrightnessCapability());System.out.println("Projector: Brightness capability added.");projector.apply("Brightness",70);
    }
}
