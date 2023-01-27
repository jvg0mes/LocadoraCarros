package Pagamento.MetodosPagamento;

import Aluguel.Aluguel;
import Pagamento.IPagamentoStrategy;

import java.io.IOException;

public class Dinheiro implements IPagamentoStrategy {

    @Override
    public void validar(Object metodoPagamento) {
    }

    @Override
    public void pagar(Object metodoPagamento, int idAluguel) throws IOException {

        Aluguel as = Aluguel.load(idAluguel);

        as.registrarPagamento();

        as.update();

        System.out.println("Pago com sucesso!");

    }

}
