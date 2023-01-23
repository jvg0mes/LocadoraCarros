import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Carro Carro = new Carro(
                "JOA-1231", "Ferrari", "2001", "2001", "Ford Ka Joaninha"
        );

//        Carro.create();

        //Carro.read();

        //Carro ferrari = Carro.loadCarro(Carro.getId());

        Carro ferrari = Carro.load(12);

          //ferrari.delete();
//
        ferrari.setFabricante("Hyundai");

        ferrari.update();

        Carro deletar = Carro.load(3);
        deletar.delete();
//
//        System.out.println(ferrari.toString());

    }
}