package tests.pessoa;

import model.JSONFailureResponse;
import model.PessoaModel;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.PessoaService;
import tests.base.BaseTest;

public class DeletarPessoaTest extends BaseTest {
    private static PessoaService pessoaService = new PessoaService();

    @Test
    @DisplayName("Teste para deletar pessoa com sucesso")
    public void testDeletarPessoaComSucesso() {
        PessoaModel pessoaCadastrada = cadastrarPessoa();
        var pessoaDeletada = pessoaService.deleterPessoa(pessoaCadastrada.getIdPessoa())
                .then()
                    .statusCode(HttpStatus.SC_OK)
                ;
    }

    @Test
    @DisplayName("Teste para deletar pessoa com id inválido")
    public void testDeletarPessoaComIdInvalido() {
        JSONFailureResponse pessoaDeletada = pessoaService.deleterPessoa(555555555)
                .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND)
                    .extract()
                    .as(JSONFailureResponse.class)
                ;
        Assertions.assertEquals("ID da pessoa nao encontrada", pessoaDeletada.getMessage());
    }



    @Test
    @DisplayName("Teste para deletar pessoa com id com valor negativo")
    public void testDeletarPessoaComIdComValorNegativo() {
        JSONFailureResponse pessoaDeletada = pessoaService.deleterPessoa(-1)
                .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND)
                    .extract()
                    .as(JSONFailureResponse.class)
                ;
    }
}
