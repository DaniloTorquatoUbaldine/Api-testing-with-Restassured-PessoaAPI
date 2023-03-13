package tests.pessoa;

import model.PessoaModel;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.PessoaService;
import tests.base.BaseTest;

public class AtualizarPessoaTest extends BaseTest {

    private static PessoaService pessoaService = new PessoaService();
    private static Integer idPessoa;

    @Test
    @DisplayName("Teste para atualizar pessoa por com id inválido")
    public void testAtualizarPessoaComSucesso() {
        PessoaModel pessoaCadastrada = cadastrarPessoa();
        var pessoaRecebida = pessoaService.AtualizarPessoa(pessoaCadastrada.getIdPessoa())
                .then()
                .statusCode(HttpStatus.SC_OK)
                //.extract()
                //.as(PessoaModel.class)
                ;
        pessoaRecebida.log().body();
    }

    @Test
    @DisplayName("Teste para atualizar pessoa com id inválido")
    public void testAtualizarPessoaComIdInvalido() {
        var pessoaRecebida = pessoaService.AtualizarPessoa(999999999)
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND)
                //.extract()
                //.as(PessoaModel.class)
                ;
        pessoaRecebida.log().body();
    }
}
