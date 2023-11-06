import java.util.*;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> list = new ArrayList<>();
        Arrays.asList(scanner.nextLine().split(" "))
                .forEach(element -> list.add(Integer.parseInt(element)));
        int swapsNum = scanner.nextInt();
        for (int i = 0; i < swapsNum; i++) {
            int firstInd = scanner.nextInt();
            int secInd = scanner.nextInt();
            Collections.swap(list, firstInd, secInd);
        }
        list.forEach(element -> System.out.print(element + " "));
    }
}