import banco.Banco;
import cliente.Cliente;
import conta.Conta;
import conta.ContaCorrente;
import conta.ContaPoupanca;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Banco banco = new Banco("Banco Exemplo");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== Menu ===");
            System.out.println("1 - Adicionar Conta");
            System.out.println("2 - Listar Contas");
            System.out.println("3 - Operar em Conta");
            System.out.println("4 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    adicionarConta(banco, scanner);
                    break;
                case 2:
                    banco.listarContas();
                    break;
                case 3:
                    banco.operarConta();
                    break;
                case 4:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        }
    }

    private static void adicionarConta(Banco banco, Scanner scanner) {
        scanner.nextLine(); // Consumir a nova linha deixada pelo nextInt()
        System.out.print("Digite o nome do cliente: ");
        String nomeCliente = scanner.nextLine();
        Cliente cliente = new Cliente(nomeCliente);

        System.out.println("Escolha o tipo de conta:");
        System.out.println("1 - Conta Corrente");
        System.out.println("2 - Conta Poupança");
        int tipoConta = scanner.nextInt();

        Conta conta;
        if (tipoConta == 1) {
            conta = new ContaCorrente(cliente);
        } else if (tipoConta == 2) {
            conta = new ContaPoupanca(cliente);
        } else {
            System.out.println("Tipo de conta inválido!");
            return;
        }

        banco.adicionarConta(conta);
        System.out.println("Conta adicionada com sucesso!");
    }
}
