import java.io.FileNotFoundException;
import java.io.IOException;

public class Carro extends Veiculo implements ICrudClass{

    private final CsvService CsvS = new CsvService("Carros.csv");

    public Carro(String placa, String fabricante, String anoModelo, String anoFabricacao, String modelo) throws IOException {
        super(placa, fabricante, anoModelo, anoFabricacao, modelo);
        super.setTipo("Carro");
        if(super.getId() == -1) {
            int maxId = -1;
            try{
                maxId = Integer.parseInt(CsvS.getMaxId()) + 1;
            } catch (NumberFormatException e){
                if(CsvS.countLines() == 1) {
                    maxId = 1;
                }
            }
            if (maxId != -1) {
                super.setId(maxId);
            }
        }
    }

    public static Carro load(int id) throws IOException {
        CsvService CsvS = new CsvService("Carros.csv");
        CsvS.read();
        String[] dataCar = CsvS.get(id);
        Carro car = new Carro(dataCar[1],dataCar[2],dataCar[3],dataCar[4],dataCar[5]);
        car.setId(Integer.parseInt(dataCar[0]));
        car.setTipo(dataCar[6]);
        return car;
    }

    @Override
    public void create() throws FileNotFoundException {
        StringBuilder buffer = new StringBuilder();
        buffer.append(getId()).append(";")
                .append(getPlaca()).append(";")
                .append(getFabricante()).append(";")
                .append(getAnoModelo()).append(";")
                .append(getAnoFabricacao()).append(";")
                .append(getModelo()).append(";")
                .append(getTipo());
        CsvS.write(buffer.toString());
    }

    @Override
    public void update() throws IOException {

        StringBuilder buffer = new StringBuilder();
        buffer.append(getId()).append(";")
                .append(getPlaca()).append(";")
                .append(getFabricante()).append(";")
                .append(getAnoModelo()).append(";")
                .append(getAnoFabricacao()).append(";")
                .append(getModelo()).append(";")
                .append(getTipo());
        String content = buffer.toString();
        CsvS.setLine(CsvS.getLineNumber(getId())+1,content);
    }

    @Override
    public void delete() throws IOException {
        CsvS.deleteLine(CsvS.getLineNumber(getId())+1);
    }

    @Override
    public String toString() {

        StringBuilder buffer = new StringBuilder();
        buffer.append("Carro ID: ")
                .append(getId()).append("\n\n")
                .append("Placa: ")
                .append(getPlaca()).append("\n")
                .append("Fabricante: ")
                .append(getFabricante()).append("\n")
                .append("Ano do Modelo: ")
                .append(getAnoModelo()).append("\n")
                .append("Ano de Fabricacao: : ")
                .append(getAnoFabricacao()).append("\n")
                .append("Modelo: ")
                .append(getModelo()).append("\n")
                .append("Tipo: ")
                .append(getTipo());

        return buffer.toString();
    }
}
