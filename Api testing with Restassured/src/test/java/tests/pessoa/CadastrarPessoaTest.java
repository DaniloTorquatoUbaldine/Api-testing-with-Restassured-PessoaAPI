package tests.pessoa;

import dataFactory.PessoaDataFactory;
//import model.JSONFailureResponse;
import io.restassured.http.ContentType;
import model.JSONFailureResponse;
import model.PessoaModel;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.PessoaService;
import tests.base.BaseTest;

import static org.hamcrest.Matchers.equalTo;

public class CadastrarPessoaTest extends BaseTest{

    private static PessoaService pessoaService = new PessoaService();
    private static Integer idPessoa;

    @Test
    @DisplayName("Teste para cadastrar pessoa com sucesso")
    public void testCadastrarPessoaComSucesso() {
        PessoaModel pessoaValida = PessoaDataFactory.pessoaValida();

        PessoaModel pessoaCadastrada = pessoaService.cadastrarPessoa(pessoaValida)
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract()
                    .as(PessoaModel.class)
                ;
        Assertions.assertNotNull(pessoaCadastrada.getIdPessoa());
        Assertions.assertEquals(pessoaValida.getNome(), pessoaCadastrada.getNome());
        Assertions.assertEquals(pessoaValida.getCpf(), pessoaCadastrada.getCpf());
        Assertions.assertEquals(pessoaValida.getDataNascimento(), pessoaCadastrada.getDataNascimento());
    }

    @Test
    @DisplayName("Teste para cadastrar pessoa com nome em branco")
    public void testCadastrarPessoaComNomeEmBranco() {
        PessoaModel pessoa = PessoaDataFactory.pessoaComNomeEmBranco();

        JSONFailureResponse pessoaCadastrada = pessoaService.cadastrarPessoa(pessoa)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract()
                    .as(JSONFailureResponse.class)
                ;
        Assertions.assertEquals("nome: must not be blank", pessoaCadastrada.getErrors().get(0));
    }

    @Test
    @DisplayName("Teste para cadastrar pessoa com data de nascimento em branco")
    public void testCadastrarPessoaComDataEmBranco() {
        PessoaModel pessoa = PessoaDataFactory.pessoaComDataEmBranco();
        JSONFailureResponse pessoaCadastrada = pessoaService.cadastrarPessoa(pessoa)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract()
                    .as(JSONFailureResponse.class)
                ;
        Assertions.assertEquals("dataNascimento: must not be null", pessoaCadastrada.getErrors().get(0));
    }

    @Test
    @DisplayName("Teste para cadastrar pessoa com data de nascimento inválida, que não existe no calendário")
    public void testCadastrarPessoaComDataInexistente() {
        PessoaModel pessoa = PessoaDataFactory.pessoaComDataInvalida();
        var response = pessoaService.cadastrarPessoa(pessoa)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                ;
    }

    @Test
    @DisplayName("Teste para cadastrar pessoa com data de nascimento inválida, com caractere especial")
    public void testCadastrarPessoaComDataInvalidaComCaractereEspecial() {
        PessoaModel pessoa = PessoaDataFactory.pessoaComDataInvalida();
        pessoa.setDataNascimento("@");
        var response = pessoaService.cadastrarPessoa(pessoa)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .body("message", equalTo("DataNascismento: não deve conter caractere especial"))
                ;
        response.log().body();
    }

    @Test
    @DisplayName("Teste para cadastrar pessoa com email no formato inválido")
    public void testCadastrarPessoaComEmailInvalido() {
        PessoaModel pessoa = PessoaDataFactory.pessoaComEmailInvalido();
        JSONFailureResponse pessoaCadastrada = pessoaService.cadastrarPessoa(pessoa)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract()
                    .as(JSONFailureResponse.class)
                ;
        Assertions.assertEquals("email: must be a well-formed email address", pessoaCadastrada.getErrors().get(0));
    }

    @Test
    @DisplayName("Teste para cadastrar pessoa com email em branco")
    public void testCadastrarPessoaComEmailEmBranco() {
        PessoaModel pessoa = PessoaDataFactory.pessoaComEmailEmBranco();
        JSONFailureResponse pessoaCadastrada = pessoaService.cadastrarPessoa(pessoa)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract()
                    .as(JSONFailureResponse.class)
                ;
        Assertions.assertEquals("email: não pode estar em branco", pessoaCadastrada.getErrors().get(0));
    }

    @Test
    @DisplayName("Teste para cadastrar pessoa com CPF inválido")
    public void testCadastrarPessoaComCpfInvalido() {
        PessoaModel pessoa = PessoaDataFactory.pessoaComCpfInvalido();
        JSONFailureResponse pessoaCadastrada = pessoaService.cadastrarPessoa(pessoa)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract()
                    .as(JSONFailureResponse.class)
                ;
        Assertions.assertEquals("CPF: deve contar 11 dígitos, sem caractere especial", pessoaCadastrada.getErrors().get(0));
    }

    @Test
    @DisplayName("Teste para cadastrar pessoa com CPF com caractere especial")
    public void testCadastrarPessoaComCpfInvalidoComCaractereEspecial() {
        PessoaModel pessoa = PessoaDataFactory.pessoaComCpfInvalido();
        pessoa.setCpf("@");
        JSONFailureResponse pessoaCadastrada = pessoaService.cadastrarPessoa(pessoa)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract()
                    .as(JSONFailureResponse.class)
                ;
        Assertions.assertEquals("CPF: deve contar 11 dígitos, sem caractere especial", pessoaCadastrada.getErrors().get(0));
    }

    @Test
    @DisplayName("Teste para cadastrar pessoa com CPF em branco")
    public void testCadastrarPessoaComCpfEmBranco() {
        PessoaModel pessoa = PessoaDataFactory.pessoaComCpfEmBranco();

        var pessoaCadastrada = pessoaService.cadastrarPessoa(pessoa)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .contentType(ContentType.JSON)
                    .body("message", equalTo("CPF: o CPF não pode estar em branco"))
                ;
        pessoaCadastrada.log().body();
    }
}
