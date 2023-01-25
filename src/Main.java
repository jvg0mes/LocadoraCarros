import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);

        System.out.println("ID Funcionario: ");

        Funcionario funci = Funcionario.login(sc.nextInt());

        Carro car = new Carro("KEA-1245","Fiat","2022",
                "2022","Joana",30000);

//      Funcionario funci = new Funcionario("Joao",
//                LocalDate.of(1990,02,01),"Gerente");
//
//      funci.create();

        //car.create();

        //Cliente cliente = new Cliente("João Marcelo", LocalDate.of(1988,8,25),5000);

        //cliente.create();

        Cliente cliente = Cliente.load(3);

        AluguelService AS = new AluguelService(cliente,Carro.load(1),5);

        AS.create();

    }

}