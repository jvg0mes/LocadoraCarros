package Pagamento.MetodosPagamento;

import Aluguel.Aluguel;
import Pagamento.IPagamentoStrategy;
import Pagamento.Validacoes.Validator;

import java.io.IOException;

public class Transferencia implements IPagamentoStrategy {

    String nConta;

    public Transferencia(String nConta) {
        this.nConta = nConta;
    }

    @Override
    public void validar(Object metodoPagamento) {
        if (!Validator.checkIfAllCharsAreNumeric(nConta)) {
            throw new RuntimeException();
        }
    }

    @Override
    public void pagar(Object metodoPagamento, int idAluguel) throws IOException {

        Aluguel as = Aluguel.load(idAluguel);

        as.registrarPagamento();

        as.update();

        System.out.println("Pago com sucesso!");

    }


}
