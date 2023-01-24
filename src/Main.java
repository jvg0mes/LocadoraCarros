import java.io.IOException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws IOException {

        Funcionario funci = new Funcionario("Joesley", LocalDate.of(1975,5,15),
                "Gerente");

        System.out.println(funci);

        funci.create();

        funci = Funcionario.load(funci.getId());

        System.out.println(funci);

        funci.setCargo("Agente Comercial");

        funci.update();

        System.out.println(funci);

        funci = Funcionario.load(funci.getId());

        System.out.println(funci);

        funci.delete();

    }
}