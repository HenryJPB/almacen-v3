/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean.controlador.sup05;

import bean.controlador.sup05.exceptions.NonexistentEntityException;
import bean.controlador.sup05.exceptions.PreexistingEntityException;
import bean.modelo.entidad.Sup05existView;
import bean.modelo.entidad.Sup05existViewS;
import bean.utilitario.controlador.JpaUtilEMF;
import bean.utilitario.libreria.LibreriaHP;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author henrypb
 */
public class Sup05existViewJpaController implements Serializable {

    /*
    public Sup05existViewJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    */
    
    public Sup05existViewJpaController() {
        this.emf = JpaUtilEMF.getEntityManagerFactory();
    }
    
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sup05existView sup05existView) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(sup05existView);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSup05existView(sup05existView.getCoditem()) != null) {
                throw new PreexistingEntityException("Sup05existView " + sup05existView + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sup05existView sup05existView) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            sup05existView = em.merge(sup05existView);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = sup05existView.getCoditem();
                if (findSup05existView(id) == null) {
                    throw new NonexistentEntityException("The sup05existView with id " + id + " no longer exists.");
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
            Sup05existView sup05existView;
            try {
                sup05existView = em.getReference(Sup05existView.class, id);
                sup05existView.getCoditem();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sup05existView with id " + id + " no longer exists.", enfe);
            }
            em.remove(sup05existView);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sup05existView> findSup05existViewEntities() {
        return findSup05existViewEntities(true, -1, -1);
    }

    public List<Sup05existView> findSup05existViewEntities(int maxResults, int firstResult) {
        return findSup05existViewEntities(false, maxResults, firstResult);
    }

    private List<Sup05existView> findSup05existViewEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sup05existView.class));
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

    public Sup05existView findSup05existView(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sup05existView.class, id);
        } finally {
            em.close();
        }
    }

    public int getSup05existViewCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sup05existView> rt = cq.from(Sup05existView.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    //--------------------------------------------------------------------------
    // Autor: Henry Jose Pulgar Blanco
    // Bqto: 04 noviembre 2019 09:36.   
    // doc: utilizando JPA Typed Query() - Arquitectura Java. 
    //      -www.arquitecturajava.com ...
    //      -https://www.programcreek.com/java-api-examples/?class=javax.persistence.Query&method=getSingleResult
    //      -http://chuwiki.chuidiang.org/index.php?title=Ejemplo_sencillo_de_JPA_con_Java_SE
    //  *NOTA IMPORTANTE*: 
    //  status:  eeeeexiiiiitoooo!!!!
    //--------------------------------------------------------------------------
    public List<Sup05existViewS> findSup05existViewSentidades() {
        List<Sup05existViewS> lista = new ArrayList<Sup05existViewS>();   
        EntityManager em =  getEntityManager();  
        //------------------*Begin transaccion*---------------------------------
        em.getTransaction().begin();
        String txtQuery = "SELECT codItem, descripcion, ubicacion, um, existencia "
                        + "FROM sup05Exist_view "
                        + "ORDER BY codItem ";  
        Query query = em.createNativeQuery(txtQuery);  
        try {
            List<Object[]> listaObj = query.getResultList();
            if ( !listaObj.isEmpty()  ) {
              String codItem;
              String descripcion; 
              String ubicacion; 
              String um;
              BigDecimal valor;  
              String existenciaS;  
              for (Object[] aObj : listaObj ) {
                  codItem = (String) aObj[0]; 
                  descripcion = (String) aObj[1]; 
                  ubicacion = (String) aObj[2]; 
                  um = (String) aObj[3]; 
                  valor = (BigDecimal) aObj[4]; 
                  existenciaS = ( valor==null ? "": new LibreriaHP().formatoDecimal.format(valor) );  
                  lista.add( new Sup05existViewS(codItem,descripcion,ubicacion,um,existenciaS) );  
              }  // for 
            }  // !listaObj.isEmpty()
        } catch (NoResultException e) {
            //throw new BusinessException("No Result", e);
            System.err.println("ERROR ( Sup05existViewJpaController *Typed Query ( findSup05existViewSentidades() ) * ) : " + e);
        }  // try-catch
        em.getTransaction().commit();
        //------------------*End transacciion*----------------------------------
        em.close();
        return (lista);
    }  // findSup05existViewSentidades().  
    
}
