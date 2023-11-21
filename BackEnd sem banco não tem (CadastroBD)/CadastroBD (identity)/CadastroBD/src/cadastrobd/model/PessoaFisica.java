package cadastrobd.model;


public class PessoaFisica extends Pessoa {
    private String cpf;
    
    public PessoaFisica() {
        super();
    }

    public PessoaFisica(int idPessoa, String nome, String logradouro, String cidade, String estado, String telefone, String email, String cpf) {
        super(idPessoa, nome, logradouro, cidade, estado, telefone, email); // Chama o construtor completo da classe Pessoa
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    @Override
    public void exibir() {
        super.exibir();
        System.out.println("CPF: " + cpf);
    }
}
