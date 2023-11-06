import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> list1 = new ArrayList<>();
        Arrays.asList(scanner.nextLine().split(" "))
                .forEach(element -> list1.add(Integer.parseInt(element)));

        List<Integer> list2 = new ArrayList<>();
        Arrays.asList(scanner.nextLine().split(" "))
                .forEach(element -> list2.add(Integer.parseInt(element)));

        System.out.println(Collections.indexOfSubList(list1, list2) + " " +
                Collections.lastIndexOfSubList(list1, list2));
    }
}