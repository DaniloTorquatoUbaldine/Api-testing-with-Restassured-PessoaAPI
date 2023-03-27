package tests.base;

import dataFactory.PessoaDataFactory;
import io.restassured.RestAssured;
import model.PessoaModel;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeAll;
import service.PessoaService;

public abstract class BaseTest {

    private static PessoaService pessoaService = new PessoaService();

    @BeforeAll
    public static void setUp() {

        RestAssured.baseURI = "http://vemser-dbc.dbccompany.com.br:39000/vemser/dbc-pessoa-api";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    public static PessoaModel cadastrarPessoa(){
        PessoaModel pessoaValida = PessoaDataFactory.pessoaValida();
        PessoaModel pessoaCadastrada = pessoaService.cadastrarPessoa(pessoaValida)
                .then()
                    .statusCode(HttpStatus.SC_OK)
                    .extract()
                    .as(PessoaModel.class)
                ;
        return pessoaCadastrada;
    }
}