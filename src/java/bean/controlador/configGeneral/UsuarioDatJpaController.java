/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.controlador.configGeneral;

import bean.controlador.configGeneral.exceptions.NonexistentEntityException;
import bean.controlador.configGeneral.exceptions.PreexistingEntityException;
import bean.modelo.entidad.UsuarioDat;
import bean.utilitario.controlador.JpaUtilEMF;
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
 * @author hpulgar
 */
public class UsuarioDatJpaController implements Serializable {

    /*
    public UsuarioDatJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    */
    
    public UsuarioDatJpaController() {
        this.emf = JpaUtilEMF.getEntityManagerFactory();
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(UsuarioDat usuarioDat) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(usuarioDat);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuarioDat(usuarioDat.getUsuario()) != null) {
                throw new PreexistingEntityException("UsuarioDat " + usuarioDat + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(UsuarioDat usuarioDat) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            usuarioDat = em.merge(usuarioDat);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = usuarioDat.getUsuario();
                if (findUsuarioDat(id) == null) {
                    throw new NonexistentEntityException("The usuarioDat with id " + id + " no longer exists.");
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
            UsuarioDat usuarioDat;
            try {
                usuarioDat = em.getReference(UsuarioDat.class, id);
                usuarioDat.getUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuarioDat with id " + id + " no longer exists.", enfe);
            }
            em.remove(usuarioDat);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<UsuarioDat> findUsuarioDatEntities() {
        return findUsuarioDatEntities(true, -1, -1);
    }

    public List<UsuarioDat> findUsuarioDatEntities(int maxResults, int firstResult) {
        return findUsuarioDatEntities(false, maxResults, firstResult);
    }

    private List<UsuarioDat> findUsuarioDatEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(UsuarioDat.class));
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

    public UsuarioDat findUsuarioDat(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(UsuarioDat.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioDatCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<UsuarioDat> rt = cq.from(UsuarioDat.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
