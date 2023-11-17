package model;


import java.io.*;
import java.util.ArrayList;

public class PessoaF_Repo {
    private ArrayList<PessoaF> pessoasFisicas = new ArrayList<>();

    public void inserir(PessoaF pessoa) {
        pessoasFisicas.add(pessoa);
    }

    public void alterar(PessoaF pessoa) {
        for (int i = 0; i < pessoasFisicas.size(); i++) {
            PessoaF p = pessoasFisicas.get(i);
            if (p.getId() == pessoa.getId()) {
                pessoasFisicas.set(i, pessoa);
                break;
            }
        }
    }

    public void excluir(int id) {
        for (int i = 0; i < pessoasFisicas.size(); i++) {
            PessoaF p = pessoasFisicas.get(i);
            if (p.getId() == id) {
                pessoasFisicas.remove(i);
                break;
            }
        }
    }

    public PessoaF obter(int id) {
        for (PessoaF pessoa : pessoasFisicas) {
            if (pessoa.getId() == id) {
                return pessoa;
            }
        }
        return null;
    }

    public ArrayList<PessoaF> obterTodos() {
        return pessoasFisicas;
    }

    public void persistir(String arquivo) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(arquivo));
        out.writeObject(pessoasFisicas);
        out.close();
    }

    public void recuperar(String arquivo) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(arquivo));
        pessoasFisicas = (ArrayList<PessoaF>) in.readObject();
        in.close();
    }
}
