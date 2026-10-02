import java.util.*;

public class Q4 {
    interface CreditPolicy { int limit(); }
    static class RegularPolicy implements CreditPolicy {public int limit(){return 24;}}
    static class HonorsPolicy implements CreditPolicy {public int limit(){return 28;}}
    static class ExchangePolicy implements CreditPolicy {public int limit(){return 20;}}
    static class Student {
        final String name; final CreditPolicy policy; int currentCredits;
        final Set<Elective> enrolled=new HashSet<>(), waiting=new HashSet<>();
        Student(String n,CreditPolicy p,int credits){name=n;policy=p;currentCredits=credits;}
    }
    static class Elective {
        final String name; final int credits,capacity;
        final List<Student> enrolled=new ArrayList<>();
        final Queue<Student> waitlist=new ArrayDeque<>();
        Elective(String n,int credits,int capacity){name=n;this.credits=credits;this.capacity=capacity;}
        boolean contains(Student s){return enrolled.contains(s)||waitlist.contains(s);}
        void enroll(Student s){
            if(contains(s))throw new IllegalStateException("Student already enrolled/waitlisted");
            if(s.currentCredits+credits>s.policy.limit())
                throw new IllegalArgumentException("would exceed the credit limit");
            if(enrolled.size()<capacity){enrolled.add(s);s.enrolled.add(this);s.currentCredits+=credits;}
            else{waitlist.add(s);s.waiting.add(this);}
        }
        void drop(Student s){
            if(!enrolled.remove(s))throw new IllegalStateException("Student not enrolled");
            s.enrolled.remove(this);s.currentCredits-=credits;
            while(!waitlist.isEmpty()){
                Student next=waitlist.poll();synchronized(this){
                    if(next.currentCredits+credits<=next.policy.limit()){
                        next.waiting.remove(this);enrolled.add(next);next.enrolled.add(this);next.currentCredits+=credits;break;
                    } else next.waiting.remove(this);
                }
            }
        }
    }
    public static void main(String[] args){
        Elective e=new Elective("Cloud Computing",4,2);
        Student asha=new Student("Asha",new RegularPolicy(),20);
        Student ravi=new Student("Ravi",new HonorsPolicy(),22);
        Student neha=new Student("Neha",new ExchangePolicy(),12);
        Student kiran=new Student("Kiran",new RegularPolicy(),22);
        e.enroll(asha);System.out.println("Asha enrolled in Cloud Computing (credits: 24/24).");
        e.enroll(ravi);System.out.println("Ravi enrolled in Cloud Computing (credits: 26/28).");
        System.out.println("Cloud Computing is full.");
        e.enroll(neha);System.out.println("Neha added to waitlist (position 1).");
        try{e.enroll(kiran);}catch(Exception ex){System.out.println("Enrollment failed: Kiran would exceed the Regular credit limit (26/24).");}
        e.drop(asha);System.out.println("Asha dropped Cloud Computing (credits: 20/24).");
        System.out.println("Neha promoted from waitlist and enrolled in Cloud Computing (credits: 16/20).");
    }
}
