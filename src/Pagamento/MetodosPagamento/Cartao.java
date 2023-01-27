package Pagamento.MetodosPagamento;

import Aluguel.Aluguel;
import Pagamento.IPagamentoStrategy;
import Pagamento.Validacoes.Validator;

import java.io.IOException;

public class Cartao implements IPagamentoStrategy {

    private String cpf;
    private String numero;
    private String cvv;

    public Cartao(String cpf, String numero, String cvv) {
        this.cpf = cpf;
        this.numero = numero;
        this.cvv = cvv;
    }

    @Override
    public void validar(Object metodoPagamento) throws RuntimeException {

        if (!Validator.validaCpf(cpf)) {
            throw new RuntimeException();
        }

        if (numero.length() != 16) {
            throw new RuntimeException();
        }

        if (cvv.length() != 3) {
            throw new RuntimeException();
        }

        if (!Validator.checkIfAllCharsAreNumeric(numero)) {
            throw new RuntimeException();
        }

        if (!Validator.checkIfAllCharsAreNumeric(cvv)) {
            throw new RuntimeException();
        }

    }

    @Override
    public void pagar(Object metodoPagamento, int idAluguel) throws IOException {

        Aluguel as = Aluguel.load(idAluguel);

        as.registrarPagamento();

        System.out.println("Registrei o pagamento, agora vou persistir");

        as.update();

        System.out.println("Pago com sucesso!");

    }

}
