import java.util.*;


class MapFunctions {

    public static void printWithSameLetter(Map<String, String> map) {
        // write your code here
        for (String key: map.keySet()) {
            String value = map.get(key);
            if (key.charAt(0) == value.charAt(0)) {
                System.out.println(key + " " + value);
            }
        }

    }
}

/* Do not change code below */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, String> map = new HashMap<>();

        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            String[] pair = s.split(" ");
            map.put(pair[0], pair[1]);
        }

        MapFunctions.printWithSameLetter(map);
    }
}