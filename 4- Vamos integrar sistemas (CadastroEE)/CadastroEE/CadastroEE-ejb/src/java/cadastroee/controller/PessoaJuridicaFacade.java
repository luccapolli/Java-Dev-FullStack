package cadastroee.controller;

// importações
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import cadastroee.model.PessoaJuridica;


@Stateless
public class PessoaJuridicaFacade implements PessoaJuridicaFacadeLocal {

    @PersistenceContext(unitName = "CadastroEE-ejbPU")
    private EntityManager em;

    @Override
    public void create(PessoaJuridica pessoaJuridica) {
        em.persist(pessoaJuridica);
    }

    @Override
    public void edit(PessoaJuridica pessoaJuridica) {
        em.merge(pessoaJuridica);
    }

    @Override
    public void remove(PessoaJuridica pessoaJuridica) {
        em.remove(em.merge(pessoaJuridica));
    }

    @Override
    public PessoaJuridica find(Object id) {
        return em.find(PessoaJuridica.class, id);
    }

    @Override
    public List<PessoaJuridica> findAll() {
        return em.createQuery("select object(o) from PessoaJuridica as o").getResultList();
    }

    @Override
    public List<PessoaJuridica> findRange(int[] range) {
        jakarta.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(PessoaJuridica.class));
        jakarta.persistence.Query q = em.createQuery(cq);
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }

    @Override
    public int count() {
        jakarta.persistence.criteria.CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        jakarta.persistence.criteria.Root<PessoaJuridica> rt = cq.from(PessoaJuridica.class);
        cq.select(em.getCriteriaBuilder().count(rt));
        jakarta.persistence.Query q = em.createQuery(cq);
        return ((Long) q.getSingleResult()).intValue();
    }
}
