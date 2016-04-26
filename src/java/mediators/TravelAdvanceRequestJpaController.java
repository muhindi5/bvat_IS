/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 9:06:11 PM  : Apr 25, 2016
 */
package mediators;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.Employee;
import entities.TravelAdvanceRequest;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import mediators.exceptions.NonexistentEntityException;
import mediators.exceptions.RollbackFailureException;

/**
 *
 * @author kelli
 */
public class TravelAdvanceRequestJpaController implements Serializable {

    public TravelAdvanceRequestJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TravelAdvanceRequest travelAdvanceRequest) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Employee preparedBy = travelAdvanceRequest.getPreparedBy();
            if (preparedBy != null) {
                preparedBy = em.getReference(preparedBy.getClass(), preparedBy.getEmployeeId());
                travelAdvanceRequest.setPreparedBy(preparedBy);
            }
            Employee requestor = travelAdvanceRequest.getRequestor();
            if (requestor != null) {
                requestor = em.getReference(requestor.getClass(), requestor.getEmployeeId());
                travelAdvanceRequest.setRequestor(requestor);
            }
            em.persist(travelAdvanceRequest);
            if (preparedBy != null) {
                preparedBy.getTravelAdvanceRequestCollection().add(travelAdvanceRequest);
                preparedBy = em.merge(preparedBy);
            }
            if (requestor != null) {
                requestor.getTravelAdvanceRequestCollection().add(travelAdvanceRequest);
                requestor = em.merge(requestor);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TravelAdvanceRequest travelAdvanceRequest) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            TravelAdvanceRequest persistentTravelAdvanceRequest = em.find(TravelAdvanceRequest.class, travelAdvanceRequest.getTradvNum());
            Employee preparedByOld = persistentTravelAdvanceRequest.getPreparedBy();
            Employee preparedByNew = travelAdvanceRequest.getPreparedBy();
            Employee requestorOld = persistentTravelAdvanceRequest.getRequestor();
            Employee requestorNew = travelAdvanceRequest.getRequestor();
            if (preparedByNew != null) {
                preparedByNew = em.getReference(preparedByNew.getClass(), preparedByNew.getEmployeeId());
                travelAdvanceRequest.setPreparedBy(preparedByNew);
            }
            if (requestorNew != null) {
                requestorNew = em.getReference(requestorNew.getClass(), requestorNew.getEmployeeId());
                travelAdvanceRequest.setRequestor(requestorNew);
            }
            travelAdvanceRequest = em.merge(travelAdvanceRequest);
            if (preparedByOld != null && !preparedByOld.equals(preparedByNew)) {
                preparedByOld.getTravelAdvanceRequestCollection().remove(travelAdvanceRequest);
                preparedByOld = em.merge(preparedByOld);
            }
            if (preparedByNew != null && !preparedByNew.equals(preparedByOld)) {
                preparedByNew.getTravelAdvanceRequestCollection().add(travelAdvanceRequest);
                preparedByNew = em.merge(preparedByNew);
            }
            if (requestorOld != null && !requestorOld.equals(requestorNew)) {
                requestorOld.getTravelAdvanceRequestCollection().remove(travelAdvanceRequest);
                requestorOld = em.merge(requestorOld);
            }
            if (requestorNew != null && !requestorNew.equals(requestorOld)) {
                requestorNew.getTravelAdvanceRequestCollection().add(travelAdvanceRequest);
                requestorNew = em.merge(requestorNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = travelAdvanceRequest.getTradvNum();
                if (findTravelAdvanceRequest(id) == null) {
                    throw new NonexistentEntityException("The travelAdvanceRequest with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            TravelAdvanceRequest travelAdvanceRequest;
            try {
                travelAdvanceRequest = em.getReference(TravelAdvanceRequest.class, id);
                travelAdvanceRequest.getTradvNum();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The travelAdvanceRequest with id " + id + " no longer exists.", enfe);
            }
            Employee preparedBy = travelAdvanceRequest.getPreparedBy();
            if (preparedBy != null) {
                preparedBy.getTravelAdvanceRequestCollection().remove(travelAdvanceRequest);
                preparedBy = em.merge(preparedBy);
            }
            Employee requestor = travelAdvanceRequest.getRequestor();
            if (requestor != null) {
                requestor.getTravelAdvanceRequestCollection().remove(travelAdvanceRequest);
                requestor = em.merge(requestor);
            }
            em.remove(travelAdvanceRequest);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TravelAdvanceRequest> findTravelAdvanceRequestEntities() {
        return findTravelAdvanceRequestEntities(true, -1, -1);
    }

    public List<TravelAdvanceRequest> findTravelAdvanceRequestEntities(int maxResults, int firstResult) {
        return findTravelAdvanceRequestEntities(false, maxResults, firstResult);
    }

    private List<TravelAdvanceRequest> findTravelAdvanceRequestEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TravelAdvanceRequest.class));
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

    public TravelAdvanceRequest findTravelAdvanceRequest(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TravelAdvanceRequest.class, id);
        } finally {
            em.close();
        }
    }

    public int getTravelAdvanceRequestCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TravelAdvanceRequest> rt = cq.from(TravelAdvanceRequest.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
