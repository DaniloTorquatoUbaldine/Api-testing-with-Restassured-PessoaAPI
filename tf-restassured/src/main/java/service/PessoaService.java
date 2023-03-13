package service;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.PessoaModel;
import utils.Autenticacao;

import static io.restassured.RestAssured.*;

public class PessoaService {

    public Response cadastrarPessoa(PessoaModel pessoa) {

        Response response =
                given()
                        .header("Authorization", Autenticacao.token())
                        .contentType(ContentType.JSON)
                        .body(pessoa)
                .when()
                        .post("/pessoa")
                ;

        return response;
    }

    public Response pesquisarPessoaPorCPF(String CPF) {
        Response response =
                given()
                        .header("Authorization", Autenticacao.token())
                        .pathParam("cpf", CPF)
                .when()
                        .get("/pessoa/{cpf}/cpf")
                ;

        return response;
    }

    public Response pesquisarPessoaPorNome(String nome) {

        Response response =
                given()
                        .header("Authorization", Autenticacao.token())
                        .queryParam("nome", nome)
                .when()
                        .get("/pessoa/byname")
                ;

        return response;
    }

    public Response listarPessoas() {

        Response response =
                given()
                        .header("Authorization", Autenticacao.token())
                .when()
                        .get("/pessoa")
                ;

        return response;
    }

    public Response listarPessoas(Integer pagina, Integer tamanhoDasPaginas) {

        Response response =
                given()
                        .header("Authorization", Autenticacao.token())
                        .queryParam("pagina", pagina)
                        .queryParam("tamanhoDasPaginas", tamanhoDasPaginas)
                .when()
                        .get("/pessoa")
                ;

        return response;
    }

    public Response listarPessoasEntreDatasDeNascimento(String data, String dtFinal) {

        Response response =
                given()
                        .header("Authorization", Autenticacao.token())
                        .queryParam("data", data)
                        .queryParam("dtFinal", dtFinal)
                .when()
                        .get("/pessoa")
                ;

        return response;
    }

    public Response AtualizarPessoa(Integer idPessoa) {

        Response response =
                given()
                        .header("Authorization", Autenticacao.token())
                        .pathParam("idPessoa", idPessoa)
                .when()
                        .delete("/pessoa/{idPessoa}")
                ;

        return response;
    }

    public Response deleterPessoa(Integer idPessoa) {

        Response response =
                given()
                        .header("Authorization", Autenticacao.token())
                        .pathParam("idPessoa", idPessoa)
                .when()
                        .delete("/pessoa/{idPessoa}")
                ;

        return response;
    }

}
