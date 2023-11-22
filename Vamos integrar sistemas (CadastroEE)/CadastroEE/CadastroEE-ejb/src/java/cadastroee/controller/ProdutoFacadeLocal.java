package cadastroee.controller;

// importações 
import java.util.List;
import jakarta.ejb.Local;
import cadastroee.model.Produto;


@Local
public interface ProdutoFacadeLocal {

    void create(Produto produto);

    void edit(Produto produto);

    void remove(Produto produto);

    Produto find(Object idProduto);

    List<Produto> findAll();

    List<Produto> findRange(int[] range);

    int count();
}
