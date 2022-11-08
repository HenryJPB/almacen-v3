/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.controlador.sup09;

import bean.controlador.sup09.exceptions.NonexistentEntityException;
import bean.controlador.sup09.exceptions.PreexistingEntityException;
import bean.modelo.entidad.Sup09Dat;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author henrypb
 */
public class Sup09DatJpaController implements Serializable {

    public Sup09DatJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sup09Dat sup09Dat) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(sup09Dat);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSup09Dat(sup09Dat.getC9Um()) != null) {
                throw new PreexistingEntityException("Sup09Dat " + sup09Dat + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sup09Dat sup09Dat) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            sup09Dat = em.merge(sup09Dat);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = sup09Dat.getC9Um();
                if (findSup09Dat(id) == null) {
                    throw new NonexistentEntityException("The sup09Dat with id " + id + " no longer exists.");
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
            Sup09Dat sup09Dat;
            try {
                sup09Dat = em.getReference(Sup09Dat.class, id);
                sup09Dat.getC9Um();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sup09Dat with id " + id + " no longer exists.", enfe);
            }
            em.remove(sup09Dat);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sup09Dat> findSup09DatEntities() {
        return findSup09DatEntities(true, -1, -1);
    }

    public List<Sup09Dat> findSup09DatEntities(int maxResults, int firstResult) {
        return findSup09DatEntities(false, maxResults, firstResult);
    }

    private List<Sup09Dat> findSup09DatEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sup09Dat.class));
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

    public Sup09Dat findSup09Dat(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sup09Dat.class, id);
        } finally {
            em.close();
        }
    }

    public int getSup09DatCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sup09Dat> rt = cq.from(Sup09Dat.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
