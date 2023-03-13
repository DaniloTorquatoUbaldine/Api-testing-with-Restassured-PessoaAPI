package tests.pessoa;

import dataFactory.PessoaDataFactory;
import model.PessoaModel;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.PessoaService;
import tests.base.BaseTest;

public class BuscarPessoaPorNomeTest extends BaseTest{

    private static PessoaService pessoaService = new PessoaService();
    private static Integer idPessoa;
    @Test
    @DisplayName("Teste para buscar pessoa por nome com sucesso")
    public void testBuscarPessoaPorNomeComSucesso() {
        PessoaModel pessoaCadastrada = cadastrarPessoa();
        PessoaModel[] pessoaRecebida = pessoaService.pesquisarPessoaPorNome(pessoaCadastrada.getNome())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(PessoaModel[].class)
                ;
        Assertions.assertEquals(pessoaCadastrada.getNome(), pessoaRecebida[0].getNome());
        Assertions.assertEquals(pessoaCadastrada.getDataNascimento(), pessoaRecebida[0].getDataNascimento());
        Assertions.assertEquals(pessoaCadastrada.getEmail(), pessoaRecebida[0].getEmail());
        Assertions.assertEquals(pessoaCadastrada.getCpf(), pessoaRecebida[0].getCpf());
        Assertions.assertEquals(pessoaCadastrada.getIdPessoa(), pessoaRecebida[0].getIdPessoa());
    }

    @Test
    @DisplayName("Teste para buscar pessoa por nome com nome inválido")
    public void testBuscarPessoaPorNomeComNomeInvalido() {
        PessoaModel pessoaCadastrada = cadastrarPessoa();
        PessoaModel[] pessoaRecebida = pessoaService.pesquisarPessoaPorNome("nomeInvalido")
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract()
                    .as(PessoaModel[].class)
                ;
    }

    @Test
    @DisplayName("Teste para buscar pessoa nome com nome em branco")
    public void testBuscarPessoaPorNomeComNomeEmBranco() {
        PessoaModel pessoaCadastrada = cadastrarPessoa();
        PessoaModel[] pessoaRecebida = pessoaService.pesquisarPessoaPorNome("")
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract()
                    .as(PessoaModel[].class)
                ;
    }
}