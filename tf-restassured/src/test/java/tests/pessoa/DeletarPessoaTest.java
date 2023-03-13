package tests.pessoa;

import model.JSONFailureResponse;
import model.JSONFailureResponseDelete;
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
        JSONFailureResponseDelete pessoaDeletada = pessoaService.deleterPessoa(555555555)
                .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND)
                    .extract()
                    .as(JSONFailureResponseDelete.class)
                ;
        Assertions.assertEquals("ID da pessoa nao encontrada", pessoaDeletada.getMessage());
    }

    @Test
    @DisplayName("Teste para deletar pessoa com id em branco")
    public void testDeletarPessoaComIdEmbranco() {
        var pessoaDeletada = pessoaService.deleterPessoa()
                .then()
                    .statusCode(HttpStatus.SC_METHOD_NOT_ALLOWED)
                ;
    }

    @Test
    @DisplayName("Teste para deletar pessoa com id com caractere especial")
    public void testDeletarPessoaComIdComCaractereEspecial() {
        var pessoaDeletada = pessoaService.deleterPessoa("@")
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                ;
        pessoaDeletada.log().body();
    }

    @Test
    @DisplayName("Teste para deletar pessoa com id com valor negativo")
    public void testDeletarPessoaComIdComValorNegativo() {
        JSONFailureResponseDelete pessoaDeletada = pessoaService.deleterPessoa(-1)
                .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND)
                    .extract()
                    .as(JSONFailureResponseDelete.class)
                ;
    }
}
