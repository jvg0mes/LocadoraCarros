package Pagamento;

import java.io.IOException;

public class PagamentoService {
    private IPagamentoStrategy strategy;

    public void setMetodoPagamento(IPagamentoStrategy strategy){
        this.strategy = strategy;
    }
    public void pagar(int idAluguel) throws IOException {
        strategy.validar(this.strategy);
        strategy.pagar(this.strategy,idAluguel);
    }

}
