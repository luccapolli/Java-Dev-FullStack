package model;


import java.io.Serializable;

public class PessoaJ extends Pessoa implements Serializable {
    private String cnpj;

    public PessoaJ() {
    }

    public PessoaJ(int id, String nome, String cnpj) {
        super(id, nome);
        this.cnpj = cnpj;
    }

    @Override
    public void exibir() {
        super.exibir();
        System.out.println("CNPJ: " + cnpj);
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
