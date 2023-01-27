package Pagamento;

import java.io.IOException;

public interface IPagamentoStrategy<T> {

    public void validar(T metodoPagamento) throws RuntimeException;
    public void pagar(T metodoPagamento,int idAluguel) throws IOException;

}
