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

public class BuscarPessoasEntreDatasTest extends BaseTest {
    private static PessoaService pessoaService = new PessoaService();
    private static Integer idPessoa;

    @Test
    @DisplayName("Teste para buscar pessoas entre datas com sucesso")
    public void testBuscarPessoasComSucesso() {
        PessoaModel pessoaValida = PessoaDataFactory.pessoaValida();
        String data1 = "01/01/1985";
        String data2 = "01/01/1995";
        PessoaModelListar pessoaCadastrada = pessoaService.listarPessoasEntreDatasDeNascimento(data1, data2)
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract()
                    .as(PessoaModelListar.class)
                ;
    }

    @Test
    @DisplayName("Teste para buscar pessoas entre datas com invertido")
    public void testBuscarPessoasComIntervaloInvertido() {
        PessoaModel pessoaValida = PessoaDataFactory.pessoaValida();
        String data1 = "01/01/1985";
        String data2 = "01/01/1995";
        PessoaModelListar pessoaCadastrada = pessoaService.listarPessoasEntreDatasDeNascimento(data2, data1)
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract()
                    .as(PessoaModelListar.class)
                ;
    }

    @Test
    @DisplayName("Teste para buscar pessoas entre datas com primeira data com caractere especial")
    public void testBuscarPessoasComDataInicialInvalida() {
        PessoaModel pessoaValida = PessoaDataFactory.pessoaValida();
        String data1 = "@";
        String data2 = "01/01/1995";
        PessoaModelListar pessoaCadastrada = pessoaService.listarPessoasEntreDatasDeNascimento(data2, data1)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract()
                    .as(PessoaModelListar.class)
                ;

    }

    @Test
    @DisplayName("Teste para buscar pessoas entre datas com segunda data com caractere especial")
    public void testBuscarPessoasComDataFinalInvalida() {
        PessoaModel pessoaValida = PessoaDataFactory.pessoaValida();
        String data1 = "@";
        String data2 = "01/01/1995";
        PessoaModelListar pessoaCadastrada = pessoaService.listarPessoasEntreDatasDeNascimento(data1, data2)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract()
                    .as(PessoaModelListar.class)
                ;
    }

    @Test
    @DisplayName("Teste para buscar pessoas entre datas com primeira data com valor negativo")
    public void testBuscarPessoasComDataInicialInvalidaComValorNegativo() {
        PessoaModel pessoaValida = PessoaDataFactory.pessoaValida();
        String data1 = "-1";
        String data2 = "01/01/1995";
        PessoaModelListar pessoaCadastrada = pessoaService.listarPessoasEntreDatasDeNascimento(data2, data1)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract()
                    .as(PessoaModelListar.class)
                ;
    }

    @Test
    @DisplayName("Teste para buscar pessoas entre datas com segunda data com valor negativo")
    public void testBuscarPessoasComDataFinalInvalidaComValorNegativo() {
        PessoaModel pessoaValida = PessoaDataFactory.pessoaValida();
        String data1 = "-1";
        String data2 = "01/01/1995";
        PessoaModelListar pessoaCadastrada = pessoaService.listarPessoasEntreDatasDeNascimento(data1, data2)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(PessoaModelListar.class)
                ;
    }

    @Test
    @DisplayName("Teste para buscar pessoas entre datas com primeira data em branco")
    public void testBuscarPessoasComDataInicialEmBranco() {
        PessoaModel pessoaValida = PessoaDataFactory.pessoaValida();
        String data1 = "";
        String data2 = "01/01/1995";
        PessoaModelListar pessoaCadastrada = pessoaService.listarPessoasEntreDatasDeNascimento(data2, data1)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract()
                    .as(PessoaModelListar.class)
                ;
    }

    @Test
    @DisplayName("Teste para buscar pessoas entre datas com segunda data em branco")
    public void testBuscarPessoasComDataFinalEmBranco() {
        PessoaModel pessoaValida = PessoaDataFactory.pessoaValida();
        String data1 = "";
        String data2 = "01/01/1995";
        PessoaModelListar pessoaCadastrada = pessoaService.listarPessoasEntreDatasDeNascimento(data1, data2)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .as(PessoaModelListar.class)
                ;
    }

    @Test
    @DisplayName("Teste para buscar pessoas entre datas com primeira com letras")
    public void testBuscarPessoasComDataInicialComLetras() {
        PessoaModel pessoaValida = PessoaDataFactory.pessoaValida();
        String data1 = "exemplo";
        String data2 = "01/01/1995";
        PessoaModelListar pessoaCadastrada = pessoaService.listarPessoasEntreDatasDeNascimento(data2, data1)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract()
                    .as(PessoaModelListar.class)
                ;
    }

    @Test
    @DisplayName("Teste para buscar pessoas entre datas com segunda data com letras")
    public void testBuscarPessoasComDataFinalComLetras() {
        PessoaModel pessoaValida = PessoaDataFactory.pessoaValida();
        String data1 = "exemplo";
        String data2 = "01/01/1995";
        PessoaModelListar pessoaCadastrada = pessoaService.listarPessoasEntreDatasDeNascimento(data1, data2)
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST)
                    .extract()
                    .as(PessoaModelListar.class)
                ;
    }
}