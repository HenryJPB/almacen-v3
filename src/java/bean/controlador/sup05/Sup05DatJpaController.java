/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean.controlador.sup05;

import bean.controlador.sup05.exceptions.NonexistentEntityException;
import bean.controlador.sup05.exceptions.PreexistingEntityException;
import bean.modelo.entidad.Sup05Dat;
import bean.modelo.entidad.Sup05DatPK;
import bean.utilitario.controlador.JpaUtilEMF;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class Sup05DatJpaController implements Serializable {

    /*
     public Sup05DatJpaController(EntityManagerFactory emf) {
     this.emf = emf;
     }
     */
    public Sup05DatJpaController() {
        this.emf = JpaUtilEMF.getEntityManagerFactory();
    }

    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sup05Dat sup05Dat) throws PreexistingEntityException, Exception {
        if (sup05Dat.getSup05DatPK() == null) {
            sup05Dat.setSup05DatPK(new Sup05DatPK());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(sup05Dat);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSup05Dat(sup05Dat.getSup05DatPK()) != null) {
                throw new PreexistingEntityException("Sup05Dat " + sup05Dat + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sup05Dat sup05Dat) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            sup05Dat = em.merge(sup05Dat);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Sup05DatPK id = sup05Dat.getSup05DatPK();
                if (findSup05Dat(id) == null) {
                    throw new NonexistentEntityException("The sup05Dat with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Sup05DatPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sup05Dat sup05Dat;
            try {
                sup05Dat = em.getReference(Sup05Dat.class, id);
                sup05Dat.getSup05DatPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sup05Dat with id " + id + " no longer exists.", enfe);
            }
            em.remove(sup05Dat);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sup05Dat> findSup05DatEntities() {
        return findSup05DatEntities(true, -1, -1);
    }

    public List<Sup05Dat> findSup05DatEntities(int maxResults, int firstResult) {
        return findSup05DatEntities(false, maxResults, firstResult);
    }

    private List<Sup05Dat> findSup05DatEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sup05Dat.class));
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

    public Sup05Dat findSup05Dat(Sup05DatPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sup05Dat.class, id);
        } finally {
            em.close();
        }
    }

    public int getSup05DatCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sup05Dat> rt = cq.from(Sup05Dat.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    //--------------------------------------------------------------------------
    // Autor: Henry Jose Pulgar Blanco
    // Bqto: 29 cotubre 2019 09:36.   
    // doc: utilizando JPA Typed Query() - Arquitectura Java. 
    //      www.arquitecturajava.com ...
    // NOTA IMPORTANTE: para utilizar el siguiente metodo se debe tener garantia
    //                  de que el Item posea existencia <-> costo / existencia,
    //                  -> existencia != 0.  (para no generar el error 'Operacion 
    //                                        Indefinida' )
    //--------------------------------------------------------------------------
    public BigDecimal getCostoPromedioItem(String codItem, Date fechaHasta) {
        EntityManager em = null;
        BigDecimal costoPromedio = BigDecimal.ZERO;
        java.sql.Date sqlFechaHasta = new java.sql.Date(fechaHasta.getTime());
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            //* Ejecutar QUERY:   ----------------------------------------------
            String txtQuery = "SELECT NVL( Sum( decode( c5_tipo_mov,"
                    + "                                 'E',c5_unidades * c5_costo, "
                    + "                                 'D',c5_unidades * c5_costo, "
                    + "                                 'S',(-1)*(c5_unidades * c5_costo) ) ),0 ) as totalCosto, "
                    + "               NVL( Sum( decode( c5_tipo_mov,"
                    + "                                 'E',c5_unidades, "
                    + "                                 'D',c5_unidades, "
                    + "                                 'S',(-1)*(c5_unidades) ) ),0 ) as existencia, "
                    + "               ( NVL( Sum( decode( c5_tipo_mov,"
                    + "                                 'E',c5_unidades * c5_costo, "
                    + "                                 'D',c5_unidades * c5_costo, "
                    + "                                 'S',(-1)*(c5_unidades * c5_costo ) ) ),0 ) / "
                    + "                 NVL( Sum( decode( c5_tipo_mov,"
                    + "                                 'E',c5_unidades, "
                    + "                                 'D',c5_unidades, "
                    + "                                 'S',(-1)*(c5_unidades) ) ),0 ) ) as costoPromedio "
                    + "        FROM   Sup05_dat "
                    + "        WHERE  c5_codigo = ? and c5_fecha <= ? ";
            Query query = em.createNativeQuery(txtQuery);
            query.setParameter(1, codItem);
            query.setParameter(2, sqlFechaHasta);
            //-----
            BigDecimal totalCosto = BigDecimal.ZERO;
            BigDecimal existencia = BigDecimal.ZERO;
            //------------------------------------------------------------------
            List<Object[]> listaObj = query.getResultList();
            for (Object[] obj : listaObj) {
                totalCosto = (BigDecimal) obj[0];
                existencia = (BigDecimal) obj[1];
                costoPromedio = (BigDecimal) obj[2];
            }  // for
            //*  ---------------------------------------------------------------
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.err.println("ERROR ( Sup05DatJpaController *Typed Query ( getCostoPromedioItem ) * ) : " + ex);
            try {
                throw ex;
            } catch (Exception ex1) {
                Logger.getLogger(Sup05DatJpaController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }  // finally
        return (costoPromedio);
    }  // getCostoPromedioItems().

    //--------------------------------------------------------------------------
    // Autor: Henry Jose Pulgar Blanco
    // Bqto: 04 noviembre 2019 09:36.   
    // doc: utilizando JPA Typed Query() - Arquitectura Java. 
    //      -www.arquitecturajava.com ...
    //      -https://www.programcreek.com/java-api-examples/?class=javax.persistence.Query&method=getSingleResult
    //  *NOTA IMPORTANTE*: 
    //  status:  eeeeexiiiiitoooo!!!!
    //  ( version getResultList )   <<---------OJO 
    //--------------------------------------------------------------------------
    //@SuppressWarnings("unchecked")    // ??? 
    //@Transactional       // 
    public BigDecimal getExistencia_1(String codItem) {
        BigDecimal existencia = BigDecimal.ZERO;
        EntityManager em = getEntityManager();
        // *Begin*
        em.getTransaction().begin();
        String txtQuery = "SELECT c5_codigo as codItem, "
                + "               nvl( sum( decode(c5_tipo_mov, "
                + "                                'S', (-1)*( nvl(c5_unidades,0) ), "
                + "                                 nvl(c5_unidades,0) ) ),0 ) as existencia "
                + "FROM   sup05_dat "
                + "WHERE  c5_codigo = ? "
                + "GROUP BY c5_codigo ";
        Query query = em.createNativeQuery(txtQuery);
        query.setParameter(1, codItem);
        //existencia = (BigDecimal) query.getSingleResult(); 
        //existencia = query.
        //System.out.println("***********Inside Query. Existencia="+existencia+"******"); 
        try {
            List<Object[]> lista = query.getResultList();  // Lista Array Obj. 
            if (lista.isEmpty()) {
                System.out.println("*****get Existencia --> Lista vacia ???????*******");
            } else {
                System.out.println("*****get Existencia --> Lista **NO** vacia ???????*******");
                for (Object[] obj : lista) {
                    //System.out.println("****Existencia=" + obj[1] + "****");
                    existencia = (BigDecimal) obj[1];
                }
            }
            // for
        } catch (NoResultException e) {
            //throw new BusinessException("No Result", e);
            System.err.println("ERROR ( Sup05DatJpaController *Typed Query ( getExistencia ) * ) : " + e);
        }  // try-catch
        em.getTransaction().commit();
        //  *End* 
        em.close();
        return (existencia);
    }  // getExistencia_1().  

    //--------------------------------------------------------------------------
    // Autor: Henry Jose Pulgar Blanco
    // Bqto: 04 noviembre 2019 09:36.   
    // doc: utilizando JPA Typed Query() - Arquitectura Java. 
    //      -www.arquitecturajava.com ...
    //      -https://www.programcreek.com/java-api-examples/?class=javax.persistence.Query&method=getSingleResult
    //      -http://chuwiki.chuidiang.org/index.php?title=Ejemplo_sencillo_de_JPA_con_Java_SE
    //  *NOTA IMPORTANTE*: 
    //  status:  eeeeexiiiiitoooo!!!!
    //  ( version getSingleResult() )   <<---OJO.  
    //--------------------------------------------------------------------------
    public BigDecimal getExistencia(String codItem) {
        BigDecimal existencia = BigDecimal.ZERO;
        EntityManager em = getEntityManager();
        // *Begin*
        em.getTransaction().begin();
        String txtQuery = "SELECT nvl( sum( decode(c5_tipo_mov, "
                + "                       'S', (-1)*( nvl(c5_unidades,0) ), "
                + "                        nvl(c5_unidades,0) ) ),0 ) as existencia "
                + "FROM   sup05_dat "
                + "WHERE  c5_codigo = ? ";
        Query query = em.createNativeQuery(txtQuery);
        //Query query = em.createQuery(txtQuery);  
        try {
            query.setParameter(1, codItem);
            existencia = (BigDecimal) query.getSingleResult();
            //System.out.println("***********Inside Query. Existencia="+query.getSingleResult()+"******"); 
        } catch (NoResultException e) {
            //throw new BusinessException("No Result", e);
            System.err.println("ERROR ( Sup05DatJpaController *Typed Query ( getExistencia ) * ) : " + e);
        }  // try-catch
        em.getTransaction().commit();
        //  *End* 
        em.close();
        return (existencia);
    }  // getExistencia().  

    //--------------------------------------------------------------------------
    // Autor: Henry Jose Pulgar Blanco
    // Bqto: 31 cotubre 2019 14:38.   
    // doc: utilizando JPA Typed Query() - Arquitectura Java. 
    //      www.arquitecturajava.com ...
    //  *NOTA IMPORTANTE*: 
    //--------------------------------------------------------------------------
    public Boolean poseeRegSucesores(String codItem, Date fecha) {
        EntityManager em = null;
        Boolean ok = Boolean.FALSE;
        java.sql.Date sqlFecha = new java.sql.Date(fecha.getTime());
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            //* Ejecutar QUERY:   ----------------------------------------------
            String txtQuery = "SELECT c5_codigo, c5_fecha "
                    + "        FROM   Sup05_dat "
                    + "        WHERE  c5_codigo = ? and c5_fecha > ? ";
            Query query = em.createNativeQuery(txtQuery);
            query.setParameter(1, codItem);
            query.setParameter(2, sqlFecha);
            //-----
            BigDecimal totalCosto = BigDecimal.ZERO;
            BigDecimal existencia = BigDecimal.ZERO;
            //-----
            List<Object[]> listaObj = query.getResultList();
            if (!listaObj.isEmpty()) {
                ok = Boolean.TRUE;
                //System.out.println("**************QUUERY EJECUTADO y lista c/valores ******CodItem="+codItem+"***fecha="+fecha+"***");
            } else {
                //System.out.println("**************QUUERY EJECUTADO y lista vacia ******CodItem="+codItem+"***fecha="+fecha+"***");
            }
            //*  ---------------------------------------------------------------
            em.getTransaction().commit();
        } catch (Exception ex) {
            System.err.println("ERROR ( Sup05DatJpaController *Typed Query ( poseeRegSucesores() ) * ) : " + ex);
            try {
                throw ex;
            } catch (Exception ex1) {
                Logger.getLogger(Sup05DatJpaController.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            if (em != null) {
                em.close();
            }
        }  // finally
        return (ok);
    }  // poseeRegSucesores().  

}
