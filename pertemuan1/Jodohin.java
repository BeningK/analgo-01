import java.util.*;
 
public class Jodohin {
    static List<String> cowo = Arrays.asList (new String [] {
        "Victor", "Wyatt", "Xavier", "Yancey", "Zeus"});
    static List<String> cewe = Arrays.asList (new String [] {
        "Amy", "Bertha", "Clare", "Diane", "Erika"});
    static Map<String, List<String>> prioritasCowo = new HashMap<String, List<String>>(){{
        put("Victor",
            Arrays.asList("Bertha", "Amy", "Diane", "Erika", "Clare"));
        put("Wyatt",
            Arrays.asList("Diane", "Bertha", "Amy", "Clare", "Erika"));
        put("Xavier",
            Arrays.asList("Bertha", "Erika", "Clare", "Diane", "Amy"));
        put("Yancey",
            Arrays.asList("Amy", "Diane", "Clare", "Bertha", "Erika"));
        put("Zeus",
            Arrays.asList("Bertha", "Diane", "Amy", "Erika", "Clare"));
    }};
    static Map<String, List<String>> prioritasCewe = new HashMap<String, List<String>>(){{
        put("Amy",
            Arrays.asList("Zeus", "Victor", "Wyatt", "Yancey", "Xavier"));
        put("Bertha",
            Arrays.asList("Xavier", "Wyatt", "Yancey", "Victor", "Zeus"));
        put("Clare",
            Arrays.asList("Wyatt", "Xavier", "Yancey", "Zeus", "Victor"));
        put("Diane",
            Arrays.asList("Victor", "Zeus", "Yancey", "Xavier", "Wyatt"));
        put("Erika",
            Arrays.asList("Yancey", "Wyatt", "Zeus", "Xavier", "Victor"));
    }};
    public static void main(String[] args){
        Map<String, String> matches = match(cowo, prioritasCowo, prioritasCewe);
        for(Map.Entry<String, String> couple:matches.entrySet()){
            System.out.println(
                    couple.getKey() + " is engaged to " + couple.getValue());
        }
        if(checkMatches(cowo, cewe, matches, prioritasCowo, prioritasCewe)){
            System.out.println("Marriages are stable");
        }else{
            System.out.println("Marriages are unstable");
        }
        String tmp = matches.get(cewe.get(0));
        matches.put(cewe.get(0), matches.get(cewe.get(1)));
        matches.put(cewe.get(1), tmp);
        System.out.println(
                cewe.get(0) +" and " + cewe.get(1) + " have switched partners");
        if(checkMatches(cowo, cewe, matches, prioritasCowo, prioritasCewe)){
            System.out.println("Marriages are stable");
        }else{
            System.out.println("Marriages are unstable");
        }
    }
 
    private static Map<String, String> match(List<String> cowo,
            Map<String, List<String>> prioritasCowo,
            Map<String, List<String>> prioritasCewe){
        Map<String, String> engagedTo = new TreeMap<String, String>();
        List<String> freecowo = new LinkedList<String>();
        freecowo.addAll(cowo);
        while(!freecowo.isEmpty()){
            String thisGuy = freecowo.remove(0); //get a load of THIS guy
            List<String> thisprioritasCowo = prioritasCowo.get(thisGuy);
            for(String girl:thisprioritasCowo){
                if(engagedTo.get(girl) == null){//girl is free
                    engagedTo.put(girl, thisGuy); //awww
                    break;
                }else{
                    String otherGuy = engagedTo.get(girl);
                    List<String> thisprioritasCewe = prioritasCewe.get(girl);
                    if(thisprioritasCewe.indexOf(thisGuy) <
                            thisprioritasCewe.indexOf(otherGuy)){
                        //this girl prefers this guy to the guy she's engaged to
                        engagedTo.put(girl, thisGuy);
                        freecowo.add(otherGuy);
                        break;
                    }//else no change...keep looking for this guy
                }
            }
        }
        return engagedTo;
    }
 
    private static boolean checkMatches(List<String> cowo, List<String> cewe,
            Map<String, String> matches, Map<String, List<String>> prioritasCowo,
            Map<String, List<String>> prioritasCewe) {
        if(!matches.keySet().containsAll(cewe)){
            return false;
        }
 
        if(!matches.values().containsAll(cowo)){
            return false;
        }
 
        Map<String, String> invertedMatches = new TreeMap<String, String>();
        for(Map.Entry<String, String> couple:matches.entrySet()){
            invertedMatches.put(couple.getValue(), couple.getKey());
        }
 
        for(Map.Entry<String, String> couple:matches.entrySet()){
            List<String> shePrefers = prioritasCewe.get(couple.getKey());
            List<String> sheLikesBetter = new LinkedList<String>();
            sheLikesBetter.addAll(shePrefers.subList(0, shePrefers.indexOf(couple.getValue())));
            List<String> hePrefers = prioritasCowo.get(couple.getValue());
            List<String> heLikesBetter = new LinkedList<String>();
            heLikesBetter.addAll(hePrefers.subList(0, hePrefers.indexOf(couple.getKey())));
 
            for(String guy : sheLikesBetter){
                String cowoFinace = invertedMatches.get(guy);
                List<String> thisprioritasCowo = prioritasCowo.get(guy);
                if(thisprioritasCowo.indexOf(cowoFinace) >
                        thisprioritasCowo.indexOf(couple.getKey())){
                    System.out.printf("%s likes %s better than %s and %s"
                            + " likes %s better than their current partner\n",
                       couple.getKey(), guy, couple.getValue(),
                       guy, couple.getKey());
                    return false;
                }
            }
 
            for(String girl : heLikesBetter){
                String ceweFinace = matches.get(girl);
                List<String> thisprioritasCewe = prioritasCewe.get(girl);
                if(thisprioritasCewe.indexOf(ceweFinace) >
                        thisprioritasCewe.indexOf(couple.getValue())){
                    System.out.printf("%s likes %s better than %s and %s"
                            + " likes %s better than their current partner\n",
                       couple.getValue(), girl, couple.getKey(),
                       girl, couple.getValue());
                    return false;
                }
            }
        }
        return true;
    }
}