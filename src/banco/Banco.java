package banco;

import conta.Conta;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Banco {

    private String nome;
    private List<Conta> contas;

    public Banco(String nome) {
        this.nome = nome;
        this.contas = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Conta> getContas() {
        return contas;
    }

    public void adicionarConta(Conta conta) {
        this.contas.add(conta);
    }

    public void listarContas() {
        for (int i = 0; i < contas.size(); i++) {
            Conta conta = contas.get(i);
            System.out.println(String.format("%d - Cliente: %s, Agência: %d, Número: %d, Saldo: %.2f",
                    i + 1, conta.getCliente().getNome(), conta.getAgencia(), conta.getNumero(), conta.getSaldo()));
        }
    }

    public void operarConta() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Selecione a conta desejada:");
        listarContas();

        int escolhaConta = scanner.nextInt() - 1;
        if (escolhaConta < 0 || escolhaConta >= contas.size()) {
            System.out.println("Conta inválida!");
            return;
        }

        Conta contaSelecionada = contas.get(escolhaConta);

        System.out.println("Escolha a operação:");
        System.out.println("1 - Depositar");
        System.out.println("2 - Sacar");
        System.out.println("3 - Transferir");
        System.out.println("4 - Imprimir Extrato");
        int operacao = scanner.nextInt();

        switch (operacao) {
            case 1:
                System.out.print("Digite o valor para depósito: ");
                double valorDeposito = scanner.nextDouble();
                contaSelecionada.depositar(valorDeposito);
                System.out.println("Depósito realizado com sucesso!");
                break;
            case 2:
                System.out.print("Digite o valor para saque: ");
                double valorSaque = scanner.nextDouble();
                contaSelecionada.sacar(valorSaque);
                System.out.println("Saque realizado com sucesso!");
                break;
            case 3:
                System.out.println("Selecione a conta de destino:");
                listarContas();
                int escolhaContaDestino = scanner.nextInt() - 1;
                if (escolhaContaDestino < 0 || escolhaContaDestino >= contas.size()) {
                    System.out.println("Conta de destino inválida!");
                    return;
                }
                Conta contaDestino = contas.get(escolhaContaDestino);
                System.out.print("Digite o valor para transferência: ");
                double valorTransferencia = scanner.nextDouble();
                contaSelecionada.transferir(valorTransferencia, contaDestino);
                System.out.println("Transferência realizada com sucesso!");
                break;
            case 4:
                contaSelecionada.imprimirExtrato();
                break;
            default:
                System.out.println("Operação inválida!");
                break;
        }
    }
}
