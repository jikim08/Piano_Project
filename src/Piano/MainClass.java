package Piano;


import java.util.HashMap;
import java.util.Map;

public class MainClass {

    public static Map<String, String> map = new HashMap<>();


    public static void main(String[] args){
        map.put("Hello", "World");
        new StartPage(map);
    }
}
