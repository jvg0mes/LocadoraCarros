import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Funcionario implements ICrudClass{

    private final CsvService CsvS = new CsvService("Funcionarios.csv");

    int id = -1;
    String nome;
    LocalDate dataNascimento;
    String cargo;

    public Funcionario(String nome, LocalDate dataNascimento, String cargo) throws IOException {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.cargo = cargo;
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

    public String getCargo() {
        return this.cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public static Funcionario load(int id) throws IOException {
        CsvService CsvS = new CsvService("Funcionarios.csv");
        CsvS.read();
        String[] dataFuncionario = CsvS.get(id);
        Funcionario funcionario= new Funcionario(dataFuncionario[1],
                LocalDate.parse(dataFuncionario[2], DateTimeFormatter.ofPattern("yyyy-MM-dd")),dataFuncionario[3]);
        funcionario.setId(Integer.parseInt(dataFuncionario[0]));
        return funcionario;
    }

    @Override
    public void create() throws FileNotFoundException {
        StringBuilder buffer = new StringBuilder();
        buffer.append(getId()).append(";")
                .append(getNome()).append(";")
                .append(getDataNascimento()).append(";")
                .append(getCargo()).append(";");
        CsvS.write(buffer.toString());
    }

    @Override
    public void update() throws IOException {
        StringBuilder buffer = new StringBuilder();
        buffer.append(getId()).append(";")
                .append(getNome()).append(";")
                .append(getDataNascimento()).append(";")
                .append(getCargo()).append(";");
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
                .append("Cargo: ")
                .append(getCargo()).append("\n");

        return buffer.toString();
    }

}

