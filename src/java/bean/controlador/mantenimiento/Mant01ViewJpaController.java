/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.controlador.mantenimiento;

import bean.controlador.mantenimiento.exceptions.NonexistentEntityException;
import bean.controlador.mantenimiento.exceptions.PreexistingEntityException;
import bean.modelo.entidad.Mant01View;
import bean.utilitario.controlador.JpaUtilEMF;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author henrypb
 */
public class Mant01ViewJpaController implements Serializable {

    /*
    public Mant01ViewJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    */
    
    public Mant01ViewJpaController() {
        this.emf = JpaUtilEMF.getEntityManagerFactory();
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mant01View mant01View) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(mant01View);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMant01View(mant01View.getC1CodMaquina()) != null) {
                throw new PreexistingEntityException("Mant01View " + mant01View + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mant01View mant01View) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            mant01View = em.merge(mant01View);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = mant01View.getC1CodMaquina();
                if (findMant01View(id) == null) {
                    throw new NonexistentEntityException("The mant01View with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Mant01View mant01View;
            try {
                mant01View = em.getReference(Mant01View.class, id);
                mant01View.getC1CodMaquina();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mant01View with id " + id + " no longer exists.", enfe);
            }
            em.remove(mant01View);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mant01View> findMant01ViewEntities() {
        return findMant01ViewEntities(true, -1, -1);
    }

    public List<Mant01View> findMant01ViewEntities(int maxResults, int firstResult) {
        return findMant01ViewEntities(false, maxResults, firstResult);
    }

    private List<Mant01View> findMant01ViewEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mant01View.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Mant01View findMant01View(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mant01View.class, id);
        } finally {
            em.close();
        }
    }

    public int getMant01ViewCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mant01View> rt = cq.from(Mant01View.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
