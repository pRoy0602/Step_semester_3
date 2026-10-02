public class Q5 {
    static class Employee {
        private String empId, name;
        private double salary;
        Employee(String empId, String name, double salary) {
            this.empId = empId; this.name = name; this.salary = salary;
        }
        double getSalary() { return salary; }
    }
    static class ManagerEmployee extends Employee {
        private double bonus;
        ManagerEmployee(String id, String name, double salary, double bonus) {
            super(id,name,salary); this.bonus = bonus;
        }
        double effectiveSalary() { return getSalary() + bonus; }
    }
    static class InternEmployee extends Employee {
        private double cap;
        InternEmployee(String id, String name, double salary, double cap) {
            super(id,name,salary); this.cap = cap;
        }
        double effectiveSalary() { return Math.min(getSalary(), cap); }
    }
    static class ParkingSlot {
        String slotNo; int capacity, occupiedCount;
        ParkingSlot(String no,int cap,int used){slotNo=no;capacity=cap;occupiedCount=used;}
        boolean allot(){if(occupiedCount<capacity){occupiedCount++;return true;}return false;}
    }
    static ParkingSlot findAvailableSlot(ParkingSlot[] slots){
        for(ParkingSlot s:slots) if(s!=null && s.occupiedCount<s.capacity) return s;
        return null;
    }
    static class CompanyEmployeeRecord {
        String name, empId; Employee employee; ParkingSlot slot;
        static int totalRecords=0;
        CompanyEmployeeRecord(String name,String empId,Employee employee){
            this.name=name;this.empId=empId;this.employee=employee;totalRecords++;
        }
        String fullProfile(){
            double pay = employee instanceof ManagerEmployee
                    ? ((ManagerEmployee)employee).effectiveSalary()
                    : employee instanceof InternEmployee
                    ? ((InternEmployee)employee).effectiveSalary()
                    : employee.getSalary();
            return name+" | Pay: Rs "+pay+" | Slot: "+(slot==null?"no parking assigned":slot.slotNo);
        }
    }
    public static void main(String[] args) {
        ParkingSlot[] slots={new ParkingSlot("A1",1,0),new ParkingSlot("A2",1,0)};
        CompanyEmployeeRecord r1=new CompanyEmployeeRecord("Divya","E1",new ManagerEmployee("E1","Divya",70000,8000));
        CompanyEmployeeRecord r2=new CompanyEmployeeRecord("Karan","E2",new Employee("E2","Karan",40000));
        CompanyEmployeeRecord r3=new CompanyEmployeeRecord("Meera","E3",new InternEmployee("E3","Meera",12000,10000));
        r1.slot=findAvailableSlot(slots); if(r1.slot!=null)r1.slot.allot();
        r2.slot=findAvailableSlot(slots); if(r2.slot!=null)r2.slot.allot();
        System.out.println(r1.fullProfile()); System.out.println(r2.fullProfile()); System.out.println(r3.fullProfile());
        System.out.println("Total records: "+CompanyEmployeeRecord.totalRecords);
    }
}
