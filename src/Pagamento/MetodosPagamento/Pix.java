package Pagamento.MetodosPagamento;

import Aluguel.Aluguel;
import Pagamento.IPagamentoStrategy;
import Pagamento.Validacoes.Validator;

import java.io.IOException;

public class Pix implements IPagamentoStrategy {

    private String cpf;

    public Pix(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public void validar(Object metodoPagamento) throws RuntimeException {

        if (!Validator.validaCpf(cpf)) {
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
