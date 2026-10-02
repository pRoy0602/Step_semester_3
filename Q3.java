public class Q3 {
    static class BusRoute implements Comparable<BusRoute> {
        private String routeCode, routeName;
        private int priority;
        BusRoute(String routeCode,String routeName,int priority){
            this.routeCode=routeCode;this.routeName=routeName;this.priority=priority;
        }
        BusRoute(String routeCode,String routeName){this(routeCode,routeName,0);}
        String getRouteCode(){return routeCode;}
        public int compareTo(BusRoute other){
            int c=Integer.compare(other.priority,priority); // higher priority first
            if(c!=0)return c;
            c=routeCode.compareToIgnoreCase(other.routeCode);
            if(c!=0)return c;
            c=routeCode.compareTo(other.routeCode);
            if(c!=0)return c;
            return routeName.compareTo(other.routeName);
        }
    }
    static BusRoute[] rankRoutes(BusRoute[] routes){
        BusRoute[] a=routes.clone(); // stable insertion sort
        for(int i=1;i<a.length;i++){
            BusRoute key=a[i]; int j=i-1;
            while(j>=0 && a[j].compareTo(key)>0){a[j+1]=a[j];j--;}
            a[j+1]=key;
        }
        return a;
    }
    public static void main(String[] args){
        BusRoute[] r={new BusRoute("RT205L","Airport Express",3),
                      new BusRoute("rt201j","City Central",4),
                      new BusRoute("RT299T","Night Service")};
        for(BusRoute x:rankRoutes(r))System.out.println(x.routeCode);
    }
}
