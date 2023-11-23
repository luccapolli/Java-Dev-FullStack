package cadastroee.controller;

// importações
import cadastroee.model.PessoaFisica;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;


@Stateless
public class PessoaFisicaFacade implements PessoaFisicaFacadeLocal {

    @PersistenceContext(unitName = "CadastroEE-ejbPU")
    private EntityManager em;

    @Override
    public void create(PessoaFisica pessoaFisica) {
        em.persist(pessoaFisica);
    }

    @Override
    public void edit(PessoaFisica pessoaFisica) {
        em.merge(pessoaFisica);
    }

    @Override
    public void remove(PessoaFisica pessoaFisica) {
        em.remove(em.merge(pessoaFisica));
    }

    @Override
    public PessoaFisica find(Object id) {
        return em.find(PessoaFisica.class, id);
    }

    @Override
    public List<PessoaFisica> findAll() {
        return em.createNamedQuery("PessoaFisica.findAll").getResultList();
    }

    @Override
    public List<PessoaFisica> findRange(int[] range) {
        jakarta.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(PessoaFisica.class));
        jakarta.persistence.Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    @Override
    public int count() {
        jakarta.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        jakarta.persistence.criteria.Root<PessoaFisica> rt = cq.from(PessoaFisica.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        jakarta.persistence.Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}
