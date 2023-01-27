import Aluguel.Aluguel;
import Entities.Carro;
import Entities.Cliente;
import Entities.Funcionario;
import Pagamento.MetodosPagamento.Cartao;
import Pagamento.MetodosPagamento.Dinheiro;
import Pagamento.MetodosPagamento.Pix;
import Pagamento.MetodosPagamento.Transferencia;
import Pagamento.PagamentoService;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        boolean[] modoSelecionado;

        boolean[] modoCriarFuncionario = {true, false, false, false, false, false, false, false, false, false};

        boolean[] modoCriarCliente = {false, false, true, false, false, false, false, false, false, false};

        boolean[] modoCriarCarro = {false, false, false, false, true, false, false, false, false, false};

        boolean[] modoAlugar = {false, false, false, true, false, true, true, false, false, false};

        boolean[] modoPagarAluguel = {false, false, false, true, false, true, false, true, true, false};

        boolean[] modoFinalizarAluguel = {false, false, false, true, false, true, false, true, false, true};

        boolean criarFuncionario;
        boolean loginFuncionario;

        boolean criarCliente;
        boolean carregarCliente;

        boolean criarCarro;
        boolean carregarCarro;

        boolean criarAluguel;
        boolean carregarAluguel;
        boolean pagarAluguel;
        boolean finalizarAluguel;

        String menuAplicacao = """
                \n-- Menu Aplicacao
                                    
                1- Criar Funcionario
                2 - Criar Cliente
                3 - Criar Carro
                4- Alugar Carro
                5- Pagar Aluguel
                6- Finalizar Aluguel
                0- Sair
                 
                Selecione a opcao desejada: """;

        int valorSelecionado = -1;

        modoSelecionado = null;

        Cliente cliente = null;
        Funcionario funci = null;
        Carro car = null;
        Aluguel aluguel = null;

        // Logando fora do while para so logar uma vez
        System.out.print("\n-- Login Funcionario");
        System.out.print("\nID Funcionario: ");

        funci = Funcionario.login(sc.nextInt());

        System.out.print("\nLogin efetuado com sucesso!");

        while (valorSelecionado != 0) {

            System.out.println();
            System.out.print(menuAplicacao);
            valorSelecionado = sc.nextInt();

            switch (valorSelecionado){
                case 1: modoSelecionado = modoCriarFuncionario;
                break;
                case 2: modoSelecionado = modoCriarCliente;
                break;
                case 3: modoSelecionado = modoCriarCarro;
                break;
                case 4: modoSelecionado = modoAlugar;
                break;
                case 5: modoSelecionado = modoPagarAluguel;
                break;
                case 6: modoSelecionado = modoFinalizarAluguel;
            }

            System.out.println("O valor selecionado foi " + valorSelecionado);

            criarFuncionario = modoSelecionado[0];
            loginFuncionario = modoSelecionado[1];

            criarCliente = modoSelecionado[2];
            carregarCliente = modoSelecionado[3];

            criarCarro = modoSelecionado[4];
            carregarCarro = modoSelecionado[5];

            criarAluguel = modoSelecionado[6];
            carregarAluguel = modoSelecionado[7];
            pagarAluguel = modoSelecionado[8];
            finalizarAluguel = modoSelecionado[9];


            if (criarFuncionario) {

                System.out.print("\n-- Criar funcionario");

                System.out.print("\nDigite o nome do funcionario:");
                String input1 = sc.next();

                System.out.print("\nDigite a data de nascimento (YYYY-MM-DD):");
                String input2 = sc.next();


                System.out.print("\nDigite o cargo:");
                String input3 = sc.next();

                funci = new Funcionario(input1,
                        LocalDate.parse(input2, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                        , input3);

                funci.create();

                System.out.println("\nFuncionario criado com sucesso!");

            }

            if (loginFuncionario) {

                System.out.print("\n-- Login Funcionario");
                System.out.print("\nID Funcionario: ");

                funci = Funcionario.login(sc.nextInt());

                System.out.print("\nLogin efetuado com sucesso!");
            }

            if (criarCliente) {

                System.out.print("\n-- Criar Cliente");

                System.out.print("\nDigite o nome do cliente:");
                String input1 = sc.next();

                System.out.print("\nDigite a data de nascimento (YYYY-MM-DD):");
                String input2 = sc.next();


                System.out.print("\nDigite a renda:");
                double input3 = sc.nextDouble();

                cliente = new Cliente(input1, LocalDate.parse(input2, DateTimeFormatter.ofPattern("yyyy-MM-dd")), input3);

                cliente.create();
            }

            if (carregarCliente) {

                System.out.print("\n-- Carregando Cliente");
                System.out.print("\nInsira o ID do Cliente:");
                cliente = Cliente.load(sc.nextInt());
                System.out.print("\nCliente carregado com sucesso!");
            }

            if (criarCarro) {
                System.out.print("\n-- Criando Carro");
                System.out.print("\nInsira a placa:");
                String input1 = sc.next();
                System.out.print("\nInsira o fabricante:");
                String input2 = sc.next();
                System.out.print("\nInsira o ano do modelo:");
                String input3 = sc.next();
                System.out.print("\nInsira o ano de fabricacao:");
                String input4 = sc.next();
                System.out.print("\nInsira o modelo:");
                String input5 = sc.next();
                System.out.print("\nInsira o valor do carro:");
                double input6 = sc.nextDouble();

                car = new Carro(input1, input2, input3, input4, input5, input6);

                car.create();

                System.out.print("\nCarro criado com sucesso!");
            }

            if (carregarCarro) {
                System.out.print("\n-- Carregando Carro");
                System.out.print("\nInsira o ID do Carro:");
                car = car.load(sc.nextInt());
                System.out.print("\nCarro carregado com sucesso!");
            }

            if (criarAluguel) {

                System.out.print("\n-- Criar Aluguel");
                System.out.print("\nDigite o número de diárias:");
                int input1 = sc.nextInt();

                aluguel = new Aluguel(cliente, car, input1);

                aluguel.create();

                System.out.print("\nCarro alugado com sucesso!");
            }

            if (carregarAluguel) {
                System.out.print("\n-- Carregando Aluguel");
                System.out.print("\nInsira o ID do Aluguel:");
                aluguel = Aluguel.load(sc.nextInt());
                System.out.print("\nAluguel carregado com sucesso!");
            }


            if (pagarAluguel) {
                PagamentoService ps = new PagamentoService();

                System.out.print("\n-- Pagamento Aluguel");
                System.out.print("\nSelecione o meio de pagamento:");
                System.out.print("\n1 - Cartao");
                System.out.print("\n2 - Pix");
                System.out.print("\n3 - Transferencia");
                System.out.print("\n4 - Dinheiro");
                System.out.print("\n\n Opcao escolhida:");

                switch (sc.nextInt()) {
                    case 1:
                        System.out.print("\n-- Pagamento Aluguel -> Cartao");
                        System.out.print("\nInsira o numero do cpf:");
                        String input1 = sc.next();
                        System.out.print("\nInsira o numero do cartao (16 digitos):");
                        String input2 = sc.next();
                        System.out.print("\nInsira o codigo verificador (3 digitos):");
                        String input3 = sc.next();
                        ps.setMetodoPagamento(new Cartao(input1, input2, input3));
                        System.out.println();
                        break;
                    case 2:
                        System.out.print("\n-- Pagamento Aluguel -> Pix");
                        System.out.print("\nInsira o numero do cpf:");
                        ps.setMetodoPagamento(new Pix(sc.next()));
                        System.out.println();
                        break;
                    case 3:
                        System.out.print("\n-- Pagamento Aluguel -> Transferencia");
                        System.out.print("\nInsira o numero da conta:");
                        ps.setMetodoPagamento(new Transferencia(sc.next()));
                        System.out.println();
                        break;
                    case 4:
                        ps.setMetodoPagamento(new Dinheiro());
                        System.out.println();
                        break;
                }

                ps.pagar(aluguel.getId());

            }

            if (finalizarAluguel) {

                System.out.print("\n-- Finalizar Aluguel\n");
                System.out.println("Status: ");
                aluguel.finalizarAluguel();
                aluguel.update();
            }

        }
    }

}