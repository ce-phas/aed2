import java.util.*;

public class Futebol {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int man, vis;

        System.out.print("Gols mandante: ");
        man = sc.nextInt();
        System.out.print("Gols visitante: ");
        vis = sc.nextInt();

        if (man > vis) {
            System.out.println("Vitória do mandante");
        } else if (vis > man) {
            System.out.println("Vitória do visitante");
        } else {
            System.out.println("Empate");
        }
    }
}
