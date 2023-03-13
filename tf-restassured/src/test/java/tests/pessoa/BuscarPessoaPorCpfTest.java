package tests.pessoa;

import dataFactory.PessoaDataFactory;
import io.restassured.http.ContentType;
import model.PessoaModel;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.PessoaService;
import tests.base.BaseTest;

import static org.hamcrest.Matchers.equalTo;

public class BuscarPessoaPorCpfTest extends BaseTest{
    private static PessoaService pessoaService = new PessoaService();
    private static Integer idPessoa;

    @Test
    @DisplayName("Teste para buscar pessoa por CPF com sucesso")
    public void testBuscarPessoaPorCpfComSucesso() {
        PessoaModel pessoaCadastrada = cadastrarPessoa();
        PessoaModel pessoaRecebida = pessoaService.pesquisarPessoaPorCPF(pessoaCadastrada.getCpf())
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract()
                    .as(PessoaModel.class)
                ;
        Assertions.assertEquals(pessoaCadastrada.getNome(), pessoaRecebida.getNome());
        Assertions.assertEquals(pessoaCadastrada.getDataNascimento(), pessoaRecebida.getDataNascimento());
        Assertions.assertEquals(pessoaCadastrada.getEmail(), pessoaRecebida.getEmail());
        Assertions.assertEquals(pessoaCadastrada.getCpf(), pessoaRecebida.getCpf());
        Assertions.assertEquals(pessoaCadastrada.getIdPessoa(), pessoaRecebida.getIdPessoa());
    }

    @Test
    @DisplayName("Teste para buscar pessoa por CPF com CPF inválido, que não está cadastrado")
    public void testBuscarComCpfInvalido() {
        PessoaModel pessoaCadastrada = cadastrarPessoa();
        var response = pessoaService.pesquisarPessoaPorCPF("654686416816818468486165168465168461568165168416516816846158641684")
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .contentType(ContentType.JSON)
                    .body("message", equalTo("A pesquisa deve ser feita com CPF válido"))
                ;
        response.log().body();
    }

    @Test
    @DisplayName("Teste para buscar pessoa por CPF com CPF inválido, com caractere especial")
    public void testBuscarComCpfInvalidoComCaractereEspecial() {
        PessoaModel pessoaCadastrada = cadastrarPessoa();
        var response = pessoaService.pesquisarPessoaPorCPF("@")
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .contentType(ContentType.JSON)
                    .body("message", equalTo("A pesquisa deve ser feita com CPF válido"))
                ;
        response.log().body();
    }

    @Test
    @DisplayName("Teste para buscar pessoa por CPF com CPF inválido, com letras")
    public void testBuscarComCpfInvalidoComLetras() {
        PessoaModel pessoaCadastrada = cadastrarPessoa();
        var response = pessoaService.pesquisarPessoaPorCPF("cpf")
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .contentType(ContentType.JSON)
                    .body("message", equalTo("A pesquisa deve ser feita com CPF válido"))
                ;
        response.log().body();
    }

    @Test
    @DisplayName("Teste para buscar pessoa por CPF com CPF em branco")
    public void testBuscarPessoaComCpfEmBranco() {
        PessoaModel pessoaCadastrada = cadastrarPessoa();
        var response = pessoaService.pesquisarPessoaPorCPF("")
                .then()
                .statusCode(HttpStatus.SC_METHOD_NOT_ALLOWED)
                ;
        response.log().body();
    }
}
