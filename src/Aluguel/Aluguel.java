package Aluguel;

import Data.CsvService;
import Entities.Carro;
import Entities.Cliente;
import Entities.ICrudClass;

import java.io.IOException;



public class Aluguel implements ICrudClass {

    private final CsvService CsvS = new CsvService("Alugueis.csv");

    int id;
    Cliente locatario;
    Carro carro;

    private int numeroDiarias;
    private double valorDiaria;
    private double valorTotal;
    private final double markup = 0.005;
    private boolean pago = false;
    private boolean finalizado = false;

    public Aluguel(Cliente locatario, Carro carro, int numeroDiarias) {
        this.locatario = locatario;
        this.carro = carro;
        this.numeroDiarias = numeroDiarias;
        this.valorDiaria = (carro.getValor() * markup);
        this.valorTotal = valorDiaria * numeroDiarias;
        if(getId() == -1) {
            int maxId = -1;
            try{
                maxId = Integer.parseInt(CsvS.getMaxId()) + 1;
            } catch (NumberFormatException | IOException e){
                if(CsvS.countLines() == 1) {
                    maxId = 1;
                }
            }
            if (maxId != -1) {
                setId(maxId);
            }
        }
    }

    private void setPago(boolean status){
        this.pago = status;
    }

    private void setFinalizado(boolean finalizado){
        this.finalizado = finalizado;
    }

    public void registrarPagamento(){
        this.pago = true;
    }

    public void finalizarAluguel(){
        if (this.pago) {
            this.finalizado = true;
            System.out.println("Aluguel finalizado com sucesso!");
        } else {
            System.out.println("O aluguel ainda nao foi pago!");
        }
    }

    public boolean getStatusPagamento(){
        return this.pago;
    }

    public boolean getStatusFinal(){
        return this.finalizado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getLocatario() {
        return locatario;
    }

    public void setLocatario(Cliente locatario) {
        this.locatario = locatario;
    }

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public int getNumeroDiarias() {
        return numeroDiarias;
    }

    public void setNumeroDiarias(int numeroDiarias) {
        this.numeroDiarias = numeroDiarias;
    }

    public double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public double getMarkup() {
        return markup;
    }

    public static Aluguel load(int id) throws IOException {
        CsvService CsvS = new CsvService("Alugueis.csv");
        CsvS.read();
        String[] dataAluguel = CsvS.get(id);


        Aluguel aluguel = new Aluguel(Cliente.load(Integer.parseInt(dataAluguel[1])),
                Carro.load(Integer.parseInt(dataAluguel[2])),
                Integer.parseInt(dataAluguel[3]));
        aluguel.setId(Integer.parseInt(dataAluguel[0]));
        aluguel.setValorDiaria(Double.parseDouble(dataAluguel[4]));
        aluguel.setValorTotal(Double.parseDouble(dataAluguel[5]));
        aluguel.setPago(Boolean.parseBoolean(dataAluguel[6]));
        aluguel.setFinalizado(Boolean.parseBoolean(dataAluguel[7]));

        return aluguel;
    }

    @Override
    public void create() throws IOException {
        StringBuilder buffer = new StringBuilder();
        buffer.append(Integer.valueOf(CsvS.getMaxId())+1).append(";")
                .append(getLocatario().getId()).append(";")
                .append(getCarro().getId()).append(";")
                .append(getNumeroDiarias()).append(";")
                .append(getValorDiaria()).append(";")
                .append(getValorTotal()).append(";")
                .append(getStatusPagamento()).append(";")
                .append(getStatusFinal());


        boolean check = CheckIfCarHasAlreadyRented();
        System.out.println(check);

        if(!check) {
            CsvS.write(buffer.toString());
            System.out.println("\nCarro alugado com sucesso!");
        } else {
            System.out.println("\nCarro já alugado!");
        }
    }

    private String[] findIdCarro(String idCarro) throws IOException {

        CsvS.read();

        return CsvS.searchValueInStringColumn(idCarro,2);
    }

    public static String[] findIdAluguel(String idAluguel) throws IOException {

        CsvService CsvS = new CsvService("Alugueis.csv");

        CsvS.read();

        return CsvS.searchValueInStringColumn(idAluguel,0);
    }

    private boolean CheckIfCarHasAlreadyRented() throws IOException {

        if (findIdCarro(String.valueOf(getCarro().getId())).length > 0){
            return true;
        }
        else{
            return false;
        }

    }

    @Override
    public void update() throws IOException {

        CsvS.read();

        StringBuilder buffer = new StringBuilder();
        buffer.append(getId()).append(";")
                .append(getLocatario().getId()).append(";")
                .append(getCarro().getId()).append(";")
                .append(getNumeroDiarias()).append(";")
                .append(getValorDiaria()).append(";")
                .append(getValorTotal()).append(";")
                .append(getStatusPagamento()).append(";")
                .append(getStatusFinal());
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
        buffer.append("Locacao ID: ")
                .append(getId()).append("\n\n")
                .append("Cliente ID: ")
                .append(getLocatario().getId()).append("\n")
                .append("Carro ID: ")
                .append(getCarro().getId()).append("\n")
                .append("Numero Diarias: ")
                .append(getNumeroDiarias()).append("\n")
                .append("Valor Diaria: ")
                .append(getValorDiaria()).append("\n")
                .append("Valor Total: ")
                .append(getValorTotal()).append("\n")
                .append("Pago: ")
                .append(getStatusPagamento()).append("\n")
                .append("Finalizado: ")
                .append(getStatusFinal()).append("\n");

        return buffer.toString();
    }

}
