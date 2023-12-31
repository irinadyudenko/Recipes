import java.time.LocalDate;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int year = scanner.nextInt();
        int[] days = new int[3];
        for (int i = 0; i < 3; i++) {
            days[i] = scanner.nextInt();
        }
        for (int i = 0; i < 3; i++) {
            System.out.println(LocalDate.ofYearDay(year, days[i]));
        }
    }
}