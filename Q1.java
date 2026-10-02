import java.util.*;

public class Q1 {
    interface ScoringRule { double calculate(double idea,double execution,double presentation); }
    static class InnovationRule implements ScoringRule {
        public double calculate(double i,double e,double p){return i*.50+e*.30+p*.20;}
    }
    static class OpenRule implements ScoringRule {
        public double calculate(double i,double e,double p){return (i+e+p)/3.0;}
    }
    enum State { OPEN, JUDGING, PUBLISHED }
    static class Student {
        final String name; Student(String name){this.name=name;}
    }
    static class Project {
        final String name; final Team team;
        Project(String name,Team team){this.name=name;this.team=team;}
    }
    static class Score {
        double idea,execution,presentation;
        Score(double i,double e,double p){idea=i;execution=e;presentation=p;}
    }
    static class Judge { final String name; Judge(String name){this.name=name;} }
    static class Team {
        final String name; final List<Student> members=new ArrayList<>(); final ScoringRule rule;
        Project project; Score score;
        Team(String name,ScoringRule rule){this.name=name;this.rule=rule;}
        void addMember(Student s){members.add(s);}
        void submit(String projectName){if(project!=null)throw new IllegalStateException("Only one project");project=new Project(projectName,this);}
    }
    static class Hackathon {
        final String name; final List<Team> teams=new ArrayList<>(); final Set<Student> usedStudents=new HashSet<>();
        State state=State.OPEN;
        Hackathon(String name){this.name=name;}
        void register(Team t){
            if(state!=State.OPEN)throw new IllegalStateException("Registration closed");
            if(t.members.size()<2||t.members.size()>4)throw new IllegalArgumentException("A team must have 2 to 4 members");
            for(Student s:t.members)if(usedStudents.contains(s))throw new IllegalArgumentException("Student already belongs to a team");
            usedStudents.addAll(t.members);teams.add(t);
        }
        void startJudging(){if(state!=State.OPEN)throw new IllegalStateException();state=State.JUDGING;}
        void score(Project p,double i,double e,double pr){
            if(state!=State.JUDGING)throw new IllegalStateException("Not judging");
            if(p.team.score!=null)throw new IllegalStateException("Score already recorded");
            p.team.score=new Score(i,e,pr);
        }
        void publish(){if(state!=State.JUDGING)throw new IllegalStateException();state=State.PUBLISHED;}
        double finalScore(Team t){
            if(t.score==null)throw new IllegalStateException();
            return t.rule.calculate(t.score.idea,t.score.execution,t.score.presentation);
        }
    }
    public static void main(String[] args){
        Hackathon h=new Hackathon("Code Sprint");
        Team t=new Team("ByteBusters",new InnovationRule());
        t.addMember(new Student("Asha"));t.addMember(new Student("Ravi"));t.addMember(new Student("Neha"));
        h.register(t);System.out.println("Team ByteBusters registered (3 members, Innovation track).");
        try{
            Team bad=new Team("SoloCoder",new OpenRule());bad.addMember(new Student("Kiran"));h.register(bad);
        }catch(Exception e){System.out.println("Registration failed: A team must have 2 to 4 members.");}
        t.submit("SmartAttend");h.startJudging();h.score(t.project,8,7,9);
        System.out.printf("Final score: %.2f%n",h.finalScore(t));h.publish();System.out.println("Results published.");
        try{h.score(t.project,10,7,9);}catch(Exception e){System.out.println("Rescore rejected: Results have already been published.");}
    }
}
