public abstract class Veiculo{

    protected int id = -1;
    protected String placa;
    protected String fabricante;
    protected String anoModelo;
    protected String anoFabricacao;
    protected String modelo;
    protected String tipo;

    public Veiculo(String placa, String fabricante, String anoModelo, String anoFabricacao, String modelo) {
        this.placa = placa;
        this.fabricante = fabricante;
        this.anoModelo = anoModelo;
        this.anoFabricacao = anoFabricacao;
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getAnoModelo() {
        return anoModelo;
    }

    public void setAnoModelo(String anoModelo) {
        this.anoModelo = anoModelo;
    }

    public String getAnoFabricacao() {
        return anoFabricacao;
    }

    public void setAnoFabricacao(String anoFabricacao) {
        this.anoFabricacao = anoFabricacao;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
