import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Cliente implements ICrudClass{

    private final CsvService CsvS = new CsvService("Clientes.csv");

    int id = -1;
    String nome;
    LocalDate dataNascimento;
    double renda;

    public Cliente(String nome, LocalDate dataNascimento, double renda) throws IOException {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.renda = renda;
        if(getId() == -1) {
            int maxId = -1;
            try{
                maxId = Integer.parseInt(CsvS.getMaxId()) + 1;
            } catch (NumberFormatException e){
                if(CsvS.countLines() == 1) {
                    maxId = 1;
                }
            }
            if (maxId != -1) {
                setId(maxId);
            }
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public double getRenda() {
        return renda;
    }

    public void setRenda(double renda) {
        this.renda = renda;
    }

    public static Cliente load(int id) throws IOException {
        CsvService CsvS = new CsvService("Clientes.csv");
        CsvS.read();
        String[] dataCliente = CsvS.get(id);
        Cliente cliente = new Cliente(dataCliente[1],LocalDate.parse(dataCliente[2], DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                Double.valueOf(dataCliente[3]));
        cliente.setId(Integer.parseInt(dataCliente[0]));
        return cliente;
    }

    @Override
    public void create() throws FileNotFoundException {
        StringBuilder buffer = new StringBuilder();
        buffer.append(getId()).append(";")
                .append(getNome()).append(";")
                .append(getDataNascimento()).append(";")
                .append(getRenda());
        CsvS.write(buffer.toString());
    }

    @Override
    public void update() throws IOException {
        StringBuilder buffer = new StringBuilder();
        buffer.append(getId()).append(";")
                .append(getNome()).append(";")
                .append(getDataNascimento()).append(";")
                .append(getRenda());
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
        buffer.append("Cliente ID: ")
                .append(getId()).append("\n\n")
                .append("Nome: ")
                .append(getNome()).append("\n")
                .append("Data Nascimento: ")
                .append(getDataNascimento()).append("\n")
                .append("Renda: ")
                .append(getRenda()).append("\n");

        return buffer.toString();
    }

}
