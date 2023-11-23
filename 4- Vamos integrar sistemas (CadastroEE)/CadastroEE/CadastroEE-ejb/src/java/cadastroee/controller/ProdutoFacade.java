package cadastroee.controller;

// importações 
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import cadastroee.model.Produto;
import java.util.List;


@Stateless
public class ProdutoFacade implements ProdutoFacadeLocal {

    @PersistenceContext(unitName = "CadastroEE-ejbPU")
    private EntityManager em;

    @Override
    public void create(Produto produto) {
        em.persist(produto);
    }

    @Override
    public void edit(Produto produto) {
        em.merge(produto);
    }

    @Override
    public void remove(Produto produto) {
        em.remove(em.merge(produto));
    }

    @Override
    public Produto find(Object idProduto) {
        return em.find(Produto.class, idProduto);
    }

    @Override
    public List<Produto> findAll() {
        return em.createNamedQuery("Produto.findAll", Produto.class).getResultList();
    }


    @Override
    public List<Produto> findRange(int[] range) {
        jakarta.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Produto.class));
        jakarta.persistence.Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    @Override
    public int count() {
        jakarta.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        jakarta.persistence.criteria.Root<Produto> rt = cq.from(Produto.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        jakarta.persistence.Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}
