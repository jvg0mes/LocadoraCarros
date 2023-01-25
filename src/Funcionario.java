import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Funcionario implements ICrudClass{

    private final CsvService CsvS = new CsvService("Funcionarios.csv");

    private int id = -1;
    private String nome;
    private LocalDate dataNascimento;
    private String cargo;
    private String senha;

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

    private static String solicitarSenha(){
        System.out.print("Insira a senha: ");
        Scanner sc = new Scanner(System.in);
        String senhaEscrita = sc.next();
        return senhaEscrita;
    }

    private static boolean autenticarFuncionario() throws IOException {
        CsvService CsvS = new CsvService("Funcionarios.csv");
        CsvS.read();

        Scanner sc = new Scanner(System.in);

        System.out.println("Insira o ID funcionario: ");
        int id = sc.nextInt();
        System.out.println("Insira a senha: ");
        String senha = sc.next();
        String[] dataFunci = CsvS.get(id);
        if(senha != dataFunci[4]){
            System.out.println("Senha Incorreta");
            throw new IOException();
        } else {
            return true;
        }
    }

    private String[] findFuncionario(String id){
        return CsvS.searchValueInStringColumn(String.valueOf(getId()),1);
    }

    public static Funcionario login(int id) throws IOException {

        CsvService CsvS = new CsvService("Funcionarios.csv");
        CsvS.read();
        String[] dataFunci = CsvS.get(id);

        String ss = solicitarSenha();
        if(!ss.contentEquals(dataFunci[4])){
            System.out.println("Senha Incorreta");
            throw new IOException();
        } else {
            Funcionario funcionario = new Funcionario(dataFunci[1],
                    LocalDate.parse(dataFunci[2],DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                    ,dataFunci[3]);
            funcionario.setId(Integer.parseInt(dataFunci[0]));
            return funcionario;
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

        Scanner sc = new Scanner(System.in);

        System.out.print("Insira a senha do funcionario:");

        StringBuilder buffer = new StringBuilder();
        buffer.append(getId()).append(";")
                .append(getNome()).append(";")
                .append(getDataNascimento()).append(";")
                .append(getCargo()).append(";")
                .append(sc.next());

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

