public class Q4 {
    interface Attackable {
        String attack();
        String attack(String weaponName);
    }
    interface Defendable { String defend(); }

    static abstract class GameCharacter {
        private static int counter=1000;
        private final String characterId;
        GameCharacter(){characterId="GC-"+(++counter);}
        abstract String getSpecialMove();
        String getCharacterId(){return characterId;}
    }
    static class Warrior extends GameCharacter implements Attackable,Defendable {
        private final String name;
        Warrior(String name){this.name=name;}
        public String attack(){return name+" strikes with a blade";}
        public String attack(String weaponName){return name+" strikes with an "+weaponName;}
        public String defend(){return name+" raises a shield";}
        String getSpecialMove(){return name+" unleashes Whirlwind Slash";}
    }
    static class Trap implements Defendable {
        private final String trapType;
        Trap(String trapType){this.trapType=trapType;}
        public String defend(){return trapType+" triggers automatically";}
    }
    static void resolveDefense(Defendable[] combatants){
        for(Defendable c:combatants)System.out.println(c.defend());
    }
    public static void main(String[] args){
        Warrior w=new Warrior("Kael");System.out.println(w.attack());
        System.out.println(w.attack("Iron Sword"));System.out.println(w.defend());
        System.out.println(w.getSpecialMove());
        Trap t=new Trap("Spike Pit");resolveDefense(new Defendable[]{w,t});
    }
}
