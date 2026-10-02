public class Q2 {
    static String classifyAccess(String modifier,String context){
        if(modifier.equals("private")) return context.equals("SAME_CLASS")?"ALLOWED":"DENIED";
        if(modifier.equals("default")) return context.equals("SAME_CLASS")||context.equals("SAME_PACKAGE")?"ALLOWED":"DENIED";
        if(modifier.equals("public")) return "ALLOWED";
        if(modifier.equals("protected"))
            return context.equals("SAME_CLASS")||context.equals("SAME_PACKAGE")
                    ||context.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE") ? "ALLOWED" : "DENIED";
        return "DENIED";
    }
    static String describeContext(String context){
        StringBuilder b=new StringBuilder();
        for(String p:context.toLowerCase().split("_")){
            if(b.length()>0)b.append(' ');
            b.append(Character.toUpperCase(p.charAt(0))).append(p.substring(1));
        }
        return b.toString();
    }
    public static void main(String[] args){
        System.out.println(classifyAccess("protected","SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));
        System.out.println(classifyAccess("protected","SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE"));
        System.out.println(describeContext("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE"));
    }
}
