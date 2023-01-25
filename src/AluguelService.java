import java.io.IOException;

public class AluguelService implements ICrudClass{

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

    public AluguelService(Cliente locatario, Carro carro, int numeroDiarias) {
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

    private void registrarPagamento(){
        this.pago = true;
    }

    public void finalizarAluguel(){
        this.finalizado = true;
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

    @Override
    public void create() throws IOException {
        StringBuilder buffer = new StringBuilder();
        buffer.append(getId()).append(";")
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
            System.out.println("Carro alugado com sucesso!");
        } else {
            System.out.println("Carro já alugado!");
        }
    }

    private String[] findIdCarro(String idCarro) throws IOException {

        CsvS.read();

        return CsvS.searchValueInStringColumn(idCarro,2);
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

        StringBuilder buffer = new StringBuilder();
        buffer.append(getId()).append(";")
                .append(getId()).append(";")
                .append(getLocatario().getId()).append(";")
                .append(getCarro().getId()).append(";")
                .append(getNumeroDiarias()).append(";")
                .append(getValorDiaria()).append(";")
                .append(getStatusPagamento()).append(";")
                .append(getStatusFinal()).append(";")
                .append(getValorTotal());
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
