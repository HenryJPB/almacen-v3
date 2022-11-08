/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.controlador.item;

import bean.controlador.item.exceptions.NonexistentEntityException;
import bean.controlador.item.exceptions.PreexistingEntityException;
import bean.modelo.entidad.Sup01Dat;
import bean.modelo.entidad.Sup01DatPK;
import bean.modelo.entidad.Sup05View;
import bean.utilitario.controlador.JpaUtilEMF;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author henrypb
 */
public class Sup01DatJpaController implements Serializable {

    /*
    public Sup01DatJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    */
    
    public Sup01DatJpaController() {
        this.emf = JpaUtilEMF.getEntityManagerFactory();
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sup01Dat sup01Dat) throws PreexistingEntityException, Exception {
        if (sup01Dat.getSup01DatPK() == null) {
            sup01Dat.setSup01DatPK(new Sup01DatPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(sup01Dat);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSup01Dat(sup01Dat.getSup01DatPK()) != null) {
                throw new PreexistingEntityException("Sup01Dat " + sup01Dat + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sup01Dat sup01Dat) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            sup01Dat = em.merge(sup01Dat);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Sup01DatPK id = sup01Dat.getSup01DatPK();
                if (findSup01Dat(id) == null) {
                    throw new NonexistentEntityException("The sup01Dat with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Sup01DatPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sup01Dat sup01Dat;
            try {
                sup01Dat = em.getReference(Sup01Dat.class, id);
                sup01Dat.getSup01DatPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sup01Dat with id " + id + " no longer exists.", enfe);
            }
            em.remove(sup01Dat);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sup01Dat> findSup01DatEntities() {
        return findSup01DatEntities(true, -1, -1);
    }

    public List<Sup01Dat> findSup01DatEntities(int maxResults, int firstResult) {
        return findSup01DatEntities(false, maxResults, firstResult);
    }

    private List<Sup01Dat> findSup01DatEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sup01Dat.class));
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

    public Sup01Dat findSup01Dat(Sup01DatPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sup01Dat.class, id);
        } finally {
            em.close();
        }
    }

    public int getSup01DatCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sup01Dat> rt = cq.from(Sup01Dat.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
   //--------------------------------------------------------------------------
    // Autor: Henry Jose Pulgar Blanco
    // Bqto: 14 octubre 2019 13:19  
    // doc: utilizando JPA Typed() - Arquitectura Java. 
    //      www.arquitecturajava.com ...   ;=)  Eeeeeeexxxxiiiiitoooooooo!!!!!!!
    //--------------------------------------------------------------------------
     public Sup01Dat findSup01DatEntidad(String codItem ) {
        EntityManager em = getEntityManager(); 
        TypedQuery<Sup01Dat> consultar = em.createNamedQuery("Sup01Dat.findByC1Codigo",  Sup01Dat.class );  // Named Query
        consultar.setParameter("c1Codigo", codItem ); 
        //consultar.setParameter("fecha", fecha.getTime() );    // Conversion java.util.Date to java.sql.Date .. 
        List<Sup01Dat> lista = consultar.getResultList();  
        //System.out.println("***Lista.size()="+lista.size()+"*********Cod Item="+codItem+"******");
        if ( lista.size() == 0 ) return (null); 
        else return ( lista.get(0) );
    } // findSup01Entidad().   
    
}
