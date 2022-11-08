/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.controlador.sup05;

import bean.controlador.item.exceptions.NonexistentEntityException;
import bean.controlador.item.exceptions.PreexistingEntityException;
import bean.modelo.entidad.Sup05View;
import bean.modelo.entidad.Sup05Vista;
import bean.utilitario.controlador.JpaUtilEMF;
import bean.utilitario.libreria.LibreriaHP;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Sup05ViewJpaController implements Serializable {

    /*
     public Sup05ViewJpaController(EntityManagerFactory emf) {
     this.emf = emf;
     }
     */
    public Sup05ViewJpaController() {
        this.emf = JpaUtilEMF.getEntityManagerFactory();
    }
    
    private EntityManagerFactory emf = null;
    
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public void create(Sup05View sup05View) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(sup05View);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSup05View(sup05View.getCoditem()) != null) {
                throw new PreexistingEntityException("Sup05View " + sup05View + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public void edit(Sup05View sup05View) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            sup05View = em.merge(sup05View);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = sup05View.getCoditem();
                if (findSup05View(id) == null) {
                    throw new NonexistentEntityException("The sup05View with id " + id + " no longer exists.");
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
            Sup05View sup05View;
            try {
                sup05View = em.getReference(Sup05View.class, id);
                sup05View.getCoditem();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sup05View with id " + id + " no longer exists.", enfe);
            }
            em.remove(sup05View);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    
    public List<Sup05View> findSup05ViewEntities() {
        return findSup05ViewEntities(true, -1, -1);
    }
    
    public List<Sup05View> findSup05ViewEntities(int maxResults, int firstResult) {
        return findSup05ViewEntities(false, maxResults, firstResult);
    }
    
    private List<Sup05View> findSup05ViewEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sup05View.class));
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
    
    public Sup05View findSup05View(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sup05View.class, id);
        } finally {
            em.close();
        }
    }
    
    public int getSup05ViewCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sup05View> rt = cq.from(Sup05View.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    //--------------------------------------------------------------------------
    // Autor: Henry Jose Pulgar Blanco
    // Bqto: 10 octubre 2019 13:19  
    // doc: utilizando JPA Typed/NamedQuery() - Arquitectura Java. 
    //      www.arquitecturajava.com ...
    //--------------------------------------------------------------------------
    public List<Sup05View> findItemsSup05View(Date fecha1, Date fecha2) {
        EntityManager em = null;
        List<Sup05View> lista = new ArrayList<Sup05View>();
        java.sql.Date sqlFecha1 = new java.sql.Date(fecha1.getTime());        
        java.sql.Date sqlFecha2 = new java.sql.Date(fecha2.getTime());        
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            //* Ejecutar QUERY:   ----------------------------------------------
            String txtQuery = "SELECT coditem, descripcion, fecha, tipomov, "
                    + "        costo, cantrequerida, unidades, referencia, "
                    + "        centroCosto, codproceso, codmaquina, origen "
                    + "        FROM   Sup05_view "
                    + "        WHERE  fecha BETWEEN ? AND ? "
                    + "        ORDER  BY fecha, codItem, tipoMov asc ";
            Query query = em.createNativeQuery(txtQuery);
            query.setParameter(1, sqlFecha1);
            query.setParameter(2, sqlFecha2);
            //-----
            String codItem;
            String descripcion;
            java.util.Date fecha;            
            String tipoMov;            
            BigDecimal costo;            
            BigDecimal cantRequerida;            
            BigDecimal unidades;            
            String referencia;            
            String centroCosto;            
            String codProceso;            
            String codMaquina;            
            String origen;
            //-----
            List<Object[]> listaObj = query.getResultList();
            for (Object[] obj : listaObj) {
                codItem = (String) obj[0];                
                descripcion = (String) obj[1];                
                fecha = (java.util.Date) obj[2];                
                tipoMov = (String) obj[3];                
                costo = (BigDecimal) obj[4];                
                cantRequerida = (BigDecimal) obj[5];                
                unidades = (BigDecimal) obj[6];                
                referencia = (String) obj[7];                
                centroCosto = (String) obj[8];                
                codProceso = (String) obj[9];                
                codMaquina = (String) obj[10];                
                origen = (String) obj[11];                
                lista.add(new Sup05View(codItem, descripcion, fecha, tipoMov, costo, cantRequerida, unidades, referencia, centroCosto, codProceso, codMaquina, origen));
            }  // for
            //*  ---------------------------------------------------------------
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.err.println("ERROR ( Sup05ViewJpaController *Named Query* ) : " + ex);
            try {
                throw ex;
            } catch (Exception ex1) {
                Logger.getLogger(Sup05ViewJpaController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }  // finally
        return (lista);
    }  // findItemsSup05View().

    //--------------------------------------------------------------------------
    // Autor: Henry Jose Pulgar Blanco
    // Bqto: 14 octubre 2019 13:19  
    // doc: utilizando JPA Typed/NamedQuery() - Arquitectura Java. 
    //      www.arquitecturajava.com ...
    //--------------------------------------------------------------------------
    public List<Sup05Vista> findItemsSup05Vista(Date fecha1, Date fecha2) {
        EntityManager em = null;
        List<Sup05Vista> lista = new ArrayList<Sup05Vista>();
        java.sql.Date sqlFecha1 = new java.sql.Date(fecha1.getTime());        
        java.sql.Date sqlFecha2 = new java.sql.Date(fecha2.getTime());        
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            //* Ejecutar QUERY:   ----------------------------------------------
            String txtQuery = "SELECT coditem, descripcion, fecha, tipomov, "
                    + "        costo, cantrequerida, unidades, referencia, "
                    + "        centroCosto, codproceso, codmaquina, origen, observacion "
                    + "        FROM   Sup05_view "
                    + "        WHERE  fecha BETWEEN ? AND ? "
                    + "        ORDER  BY fecha, codItem, tipoMov asc ";
            Query query = em.createNativeQuery(txtQuery);
            query.setParameter(1, sqlFecha1);
            query.setParameter(2, sqlFecha2);
            //-----
            String codItem;
            String descripcion;
            java.util.Date fecha;                 // usado para trasbase de valores internos
            String fechaS;            
            String tipoMov;
            //------------
            BigDecimal valor = BigDecimal.ZERO;   // usado para trasbase de valores internos
            //------------
            String costoS;            
            String cantRequeridaS;            
            String unidadesS;            
            String referencia;            
            String centroCosto;            
            String codProceso;            
            String codMaquina;            
            String origen;
            String observacion;  
            //-----
            LibreriaHP libHP = new LibreriaHP();            
            List<Object[]> listaObj = query.getResultList();
            for (Object[] obj : listaObj) {
                codItem = (String) obj[0];                
                descripcion = (String) obj[1];
                // ----OJO-----
                fecha = (java.util.Date) obj[2];                
                fechaS = ( fecha==null?"":libHP.formatoFecha.format(fecha) );    // (*) 
                tipoMov = (String) obj[3];
                //-----
                valor = (BigDecimal) obj[4];
                costoS = ( valor==null?"":libHP.formatoDecimal.format(valor) );
                // ----OJO-----
                valor = (BigDecimal) obj[5];
                cantRequeridaS = ( valor==null?"":libHP.formatoDecimal.format(valor) );
                // ----OJO-----
                valor = (BigDecimal) obj[6];                
                unidadesS = ( valor==null?"":libHP.formatoDecimal.format(valor) );
                //-----
                referencia = (String) obj[7];                
                centroCosto = (String) obj[8];                
                codProceso = (String) obj[9];                
                codMaquina = (String) obj[10];                
                origen = (String) obj[11];    
                observacion = (String) obj[12];  
                lista.add(new Sup05Vista(codItem, descripcion, fechaS, tipoMov, costoS, cantRequeridaS, unidadesS, referencia, centroCosto, codProceso, codMaquina, origen,observacion));
            }  // for
            //*  ---------------------------------------------------------------
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.err.println("ERROR ( Sup05ViewJpaController *Named Query* ) : " + ex);
            try {
                throw ex;
            } catch (Exception ex1) {
                Logger.getLogger(Sup05ViewJpaController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }  // finally
        return (lista);
    }  // findItemsSup05Vista().
    
    //-------------------------------------------------------------------------
    // Autor: Henry Jose Pulgar Blanco
    // Bqto: 16 octubre 2019 08:56 am  
    // doc: utilizando JPA Typed/NamedQuery() - Arquitectura Java. 
    //      www.arquitecturajava.com ...
    // ** Adaptado y configurado aplicando Poliformismo :   ...  **
    //---------------------------*TypedQuery*----------------------------------
    public Sup05View findSup05ViewEntitie(String codItem, java.util.Date fecha, String tipoMov, String referencia) {
        EntityManager em = getEntityManager(); 
        TypedQuery<Sup05View> consultar = em.createNamedQuery("Sup05View.findByClaveCompuesta",  Sup05View.class );  // Named Query de mi autoria.  
        consultar.setParameter("codItem", codItem ); 
        //consultar.setParameter("fecha", fecha.getTime() );    // Conversion java.util.Date to java.sql.Date .. 
        consultar.setParameter("fecha", fecha );       
        consultar.setParameter("tipoMov", tipoMov ); 
        consultar.setParameter("referencia", referencia ); 
        List<Sup05View> lista = consultar.getResultList();  
        //
        // Sup05View sup05View = lista.get(0);  
        //
        return (lista.get(0));
    } // findSup05ViewEntities().    
}
