package dataFactory;

import model.PessoaModel;
import net.datafaker.Faker;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Random;

public class PessoaDataFactory {
    private static Faker faker = new Faker();
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static PessoaModel pessoaValida() {
        return novaPessoa();
    }

    public static PessoaModel pessoaComNomeEmBranco() {
        PessoaModel pessoaComNomeEmBranco = novaPessoa();
        pessoaComNomeEmBranco.setNome("");
        return pessoaComNomeEmBranco;
    }

    public static PessoaModel pessoaComDataInvalida() {
        PessoaModel pessoaComNomeEmBranco = novaPessoa();
        pessoaComNomeEmBranco.setDataNascimento("31/02/1970");
        return pessoaComNomeEmBranco;
    }

    public static PessoaModel pessoaComDataEmBranco() {
        PessoaModel pessoaComNomeEmBranco = novaPessoa();
        pessoaComNomeEmBranco.setDataNascimento("");
        return pessoaComNomeEmBranco;
    }

    public static PessoaModel pessoaComEmailInvalido() {
        PessoaModel pessoaComNomeEmBranco = novaPessoa();
        pessoaComNomeEmBranco.setEmail("teste.com");
        return pessoaComNomeEmBranco;
    }

    public static PessoaModel pessoaComEmailEmBranco() {
        PessoaModel pessoaComNomeEmBranco = novaPessoa();
        pessoaComNomeEmBranco.setEmail("");
        return pessoaComNomeEmBranco;
    }

    public static PessoaModel pessoaComCpfInvalido() {
        PessoaModel pessoaComNomeEmBranco = novaPessoa();
        pessoaComNomeEmBranco.setCpf("1");
        return pessoaComNomeEmBranco;
    }

    public static PessoaModel pessoaComCpfEmBranco() {
        PessoaModel pessoaComNomeEmBranco = novaPessoa();
        pessoaComNomeEmBranco.setCpf("");
        return pessoaComNomeEmBranco;
    }

    public static Integer listarPessoasValido() {
        Random random = new Random();
        int numero = random.nextInt(100) + 1;
        return numero;
    }



    private static PessoaModel novaPessoa() {
        PessoaModel pessoa = new PessoaModel();
        pessoa.setNome(faker.name().nameWithMiddle());
        pessoa.setDataNascimento(dateFormat.format(faker.date().birthday()));
        pessoa.setCpf(faker.cpf().valid(false));
        pessoa.setEmail(faker.internet().emailAddress());

        return pessoa;
    }
}
