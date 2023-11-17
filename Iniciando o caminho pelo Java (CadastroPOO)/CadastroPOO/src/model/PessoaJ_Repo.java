package model;


import java.io.*;
import java.util.ArrayList;

public class PessoaJ_Repo {
    private ArrayList<PessoaJ> pessoasJuridicas = new ArrayList<>();

    public void inserir(PessoaJ pessoa) {
        pessoasJuridicas.add(pessoa);
    }

    public void alterar(PessoaJ pessoa) {
        for (int i = 0; i < pessoasJuridicas.size(); i++) {
            PessoaJ p = pessoasJuridicas.get(i);
            if (p.getId() == pessoa.getId()) {
                pessoasJuridicas.set(i, pessoa);
                break;
            }
        }
    }

    public void excluir(int id) {
        for (int i = 0; i < pessoasJuridicas.size(); i++) {
            PessoaJ p = pessoasJuridicas.get(i);
            if (p.getId() == id) {
                pessoasJuridicas.remove(i);
                break;
            }
        }
    }

    public PessoaJ obter(int id) {
        for (PessoaJ pessoa : pessoasJuridicas) {
            if (pessoa.getId() == id) {
                return pessoa;
            }
        }
        return null;
    }

    public ArrayList<PessoaJ> obterTodos() {
        return pessoasJuridicas;
    }

    public void persistir(String arquivo) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo));
        out.writeObject(pessoasJuridicas);
        out.close();
    }

    public void recuperar(String arquivo) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo));
        pessoasJuridicas = (ArrayList<PessoaJ>) in.readObject();
        in.close();
    }
}
