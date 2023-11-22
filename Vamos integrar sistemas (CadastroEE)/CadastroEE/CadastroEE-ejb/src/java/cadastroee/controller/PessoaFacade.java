package cadastroee.controller;

// importações
import cadastroee.model.Pessoa;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;


@Stateless
public class PessoaFacade implements PessoaFacadeLocal {

    @PersistenceContext(unitName = "CadastroEE-ejbPU")
    private EntityManager em;

    @Override
    public void criar(Pessoa pessoa) {
        em.persist(pessoa);
    }

    @Override
    public void atualizar(Pessoa pessoa) {
        em.merge(pessoa);
    }

    @Override
    public void remover(Pessoa pessoa) {
        em.remove(em.merge(pessoa));
    }

    @Override
    public Pessoa encontrar(Object id) {
        return em.find(Pessoa.class, id);
    }

    @Override
    public List<Pessoa> encontrarTodos() {
        return em.createNamedQuery("Pessoa.findAll").getResultList();
    }

    @Override
    public List<Pessoa> encontrarRange(int[] range) {
        jakarta.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Pessoa.class));
        jakarta.persistence.Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    @Override
    public int contar() {
        jakarta.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        jakarta.persistence.criteria.Root<Pessoa> rt = cq.from(Pessoa.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        jakarta.persistence.Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}
