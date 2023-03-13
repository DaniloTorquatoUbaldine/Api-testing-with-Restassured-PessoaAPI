package tests.pessoa;

import dataFactory.PessoaDataFactory;
import model.PessoaModel;
import model.PessoaModelListar;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import service.PessoaService;
import tests.base.BaseTest;

public class ListarPessoasTest extends BaseTest {

    private static PessoaService pessoaService = new PessoaService();
    private static Integer idPessoa;

    @Test
    @DisplayName("Teste para listar pessoas com sucesso")
    public void testListarPessoasComSucesso() {

        Integer num1 = PessoaDataFactory.listarPessoasValido();
        Integer num2 = PessoaDataFactory.listarPessoasValido();
        PessoaModelListar listaRecebida = pessoaService.listarPessoas(num1,num2)
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract()
                    .as(PessoaModelListar.class)
                ;
        Assertions.assertEquals(num1.toString(), listaRecebida.getPage());
        Assertions.assertEquals(num2.toString(), listaRecebida.getSize());
    }

    @Test
    @DisplayName("Teste para listar pessoas sem argumento")
    public void testListarPessoasSemArgumento() {
        Integer default1 = 0;
        Integer default2 = 20;
        PessoaModelListar listaRecebida = pessoaService.listarPessoas()
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract()
                    .as(PessoaModelListar.class)
                ;
        Assertions.assertEquals(default1.toString(), listaRecebida.getPage());
        Assertions.assertEquals(default2.toString(), listaRecebida.getSize());
    }


    @Test
    @DisplayName("Teste para listar pessoas com primeiro argumento negativo")
    public void testListarPessoasComValorInicialNegativo() {
        PessoaModelListar listaRecebida = pessoaService.listarPessoas(-1,10)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract()
                    .as(PessoaModelListar.class)
                ;
    }
    @Test
    @DisplayName("Teste para listar pessoas com segundo argumento negativo")
    public void testListarPessoasComValorFinalNegativo() {
        PessoaModelListar listaRecebida = pessoaService.listarPessoas(10,-1)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract()
                    .as(PessoaModelListar.class)
                ;
    }

    @Test
    @DisplayName("Teste para listar pessoas com valor da página igual a 0")
    public void testListarPessoasComValorInvalidoPagina() {
        Integer num1 = PessoaDataFactory.listarPessoasValido();
        PessoaModelListar listaRecebida = pessoaService.listarPessoas(0,num1)
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract()
                    .as(PessoaModelListar.class)
                ;
        Assertions.assertEquals("0", listaRecebida.getPage());
        Assertions.assertEquals(num1.toString(), listaRecebida.getSize());
    }

    @Test
    @DisplayName("Teste para listar pessoas com valor do tamanho das páginas igual a 0")
    public void testListarPessoasComValorInvalidoTamanho() {
        Integer num1 = PessoaDataFactory.listarPessoasValido();
        PessoaModelListar listaRecebida = pessoaService.listarPessoas(num1,0)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract()
                    .as(PessoaModelListar.class)
                ;
    }
}
