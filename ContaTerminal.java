import java.util.Scanner;

public class ContaTerminal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Por favor, digite o numero da conta!");
        int Numero = sc.nextInt();
        sc.nextLine();

        System.out.println("Por favor, digite o nome do usuario!");
        String Nome = sc.nextLine();

        System.out.println("Por favor, digite o numero da agencia!");
        String Agencia = sc.nextLine();

        System.out.println("Por favor, digite o saldo disponivel!");
        float Saldo = sc.nextFloat();

        System.out.println("Olá " + Nome + ", obrigado por criar uma conta em nosso banco, sua agência é " + Agencia + ", conta " + Numero + " e seu saldo " + Saldo + " já está disponível para saque.");

        sc.close();
    }
}
