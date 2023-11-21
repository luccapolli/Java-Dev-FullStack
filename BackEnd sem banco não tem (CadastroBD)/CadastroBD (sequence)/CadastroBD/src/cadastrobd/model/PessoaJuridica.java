package cadastrobd.model;


public class PessoaJuridica extends Pessoa {
    private String cnpj;

    public PessoaJuridica() {
        super();
    }

    public PessoaJuridica(int idPessoa, String nome, String logradouro, String cidade, String estado, String telefone, String email, String cnpj) {
        super(idPessoa, nome, logradouro, cidade, estado, telefone, email); // Chama o construtor completo da classe Pessoa
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    @Override
    public void exibir() {
        super.exibir(); // Chama o método exibir da classe Pessoa para exibir os atributos herdados
        System.out.println("CNPJ: " + cnpj);
    }
}
