/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.controlador.sup08;

import bean.controlador.sup08.exceptions.NonexistentEntityException;
import bean.controlador.sup08.exceptions.PreexistingEntityException;
import bean.modelo.entidad.Sup08Dat;
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
public class Sup08DatJpaController implements Serializable {

    /*
    public Sup08DatJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    */
    
    public Sup08DatJpaController() {
        this.emf = JpaUtilEMF.getEntityManagerFactory();
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sup08Dat sup08Dat) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(sup08Dat);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSup08Dat(sup08Dat.getC8CodProceso()) != null) {
                throw new PreexistingEntityException("Sup08Dat " + sup08Dat + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sup08Dat sup08Dat) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            sup08Dat = em.merge(sup08Dat);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = sup08Dat.getC8CodProceso();
                if (findSup08Dat(id) == null) {
                    throw new NonexistentEntityException("The sup08Dat with id " + id + " no longer exists.");
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
            Sup08Dat sup08Dat;
            try {
                sup08Dat = em.getReference(Sup08Dat.class, id);
                sup08Dat.getC8CodProceso();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sup08Dat with id " + id + " no longer exists.", enfe);
            }
            em.remove(sup08Dat);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sup08Dat> findSup08DatEntities() {
        return findSup08DatEntities(true, -1, -1);
    }

    public List<Sup08Dat> findSup08DatEntities(int maxResults, int firstResult) {
        return findSup08DatEntities(false, maxResults, firstResult);
    }

    private List<Sup08Dat> findSup08DatEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sup08Dat.class));
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

    public Sup08Dat findSup08Dat(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sup08Dat.class, id);
        } finally {
            em.close();
        }
    }

    public int getSup08DatCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sup08Dat> rt = cq.from(Sup08Dat.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
