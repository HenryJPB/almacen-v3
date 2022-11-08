/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.controlador.mantenimiento;

import bean.controlador.mantenimiento.exceptions.NonexistentEntityException;
import bean.controlador.mantenimiento.exceptions.PreexistingEntityException;
import bean.modelo.entidad.Mant00Dat;
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
public class Mant00DatJpaController implements Serializable {

    /*
    public Mant00DatJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    */
    
    public Mant00DatJpaController() {
        this.emf = JpaUtilEMF.getEntityManagerFactory();
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Mant00Dat mant00Dat) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(mant00Dat);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findMant00Dat(mant00Dat.getCodMaq()) != null) {
                throw new PreexistingEntityException("Mant00Dat " + mant00Dat + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Mant00Dat mant00Dat) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            mant00Dat = em.merge(mant00Dat);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = mant00Dat.getCodMaq();
                if (findMant00Dat(id) == null) {
                    throw new NonexistentEntityException("The mant00Dat with id " + id + " no longer exists.");
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
            Mant00Dat mant00Dat;
            try {
                mant00Dat = em.getReference(Mant00Dat.class, id);
                mant00Dat.getCodMaq();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The mant00Dat with id " + id + " no longer exists.", enfe);
            }
            em.remove(mant00Dat);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Mant00Dat> findMant00DatEntities() {
        return findMant00DatEntities(true, -1, -1);
    }

    public List<Mant00Dat> findMant00DatEntities(int maxResults, int firstResult) {
        return findMant00DatEntities(false, maxResults, firstResult);
    }

    private List<Mant00Dat> findMant00DatEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Mant00Dat.class));
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

    public Mant00Dat findMant00Dat(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Mant00Dat.class, id);
        } finally {
            em.close();
        }
    }

    public int getMant00DatCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Mant00Dat> rt = cq.from(Mant00Dat.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
