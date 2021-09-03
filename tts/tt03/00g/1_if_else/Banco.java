import java.util.*;

public class Banco {
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        double salario, prestacao;

        System.out.print("Salario bruto: R$");
        salario = sc.nextDouble();
        System.out.print("Valor da prestacao: R$");
        prestacao = sc.nextDouble();

        if (prestacao > salario * 0.4) {
            System.out.println("Empréstimo não autorizado.");
        } else {
            System.out.println("Empréstimo autorizado.");
        }
    }
}
