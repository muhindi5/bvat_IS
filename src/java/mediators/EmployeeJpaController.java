/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 9:06:11 PM  : Apr 25, 2016
 */
package mediators;

import entities.Employee;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import entities.TravelAdvanceRequest;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;
import mediators.exceptions.IllegalOrphanException;
import mediators.exceptions.NonexistentEntityException;
import mediators.exceptions.RollbackFailureException;

/**
 *
 * @author kelli
 */
public class EmployeeJpaController implements Serializable {

    public EmployeeJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Employee employee) throws RollbackFailureException, Exception {
        if (employee.getTravelAdvanceRequestCollection() == null) {
            employee.setTravelAdvanceRequestCollection(new ArrayList<TravelAdvanceRequest>());
        }
        if (employee.getTravelAdvanceRequestCollection1() == null) {
            employee.setTravelAdvanceRequestCollection1(new ArrayList<TravelAdvanceRequest>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<TravelAdvanceRequest> attachedTravelAdvanceRequestCollection = new ArrayList<TravelAdvanceRequest>();
            for (TravelAdvanceRequest travelAdvanceRequestCollectionTravelAdvanceRequestToAttach : employee.getTravelAdvanceRequestCollection()) {
                travelAdvanceRequestCollectionTravelAdvanceRequestToAttach = em.getReference(travelAdvanceRequestCollectionTravelAdvanceRequestToAttach.getClass(), travelAdvanceRequestCollectionTravelAdvanceRequestToAttach.getTradvNum());
                attachedTravelAdvanceRequestCollection.add(travelAdvanceRequestCollectionTravelAdvanceRequestToAttach);
            }
            employee.setTravelAdvanceRequestCollection(attachedTravelAdvanceRequestCollection);
            Collection<TravelAdvanceRequest> attachedTravelAdvanceRequestCollection1 = new ArrayList<TravelAdvanceRequest>();
            for (TravelAdvanceRequest travelAdvanceRequestCollection1TravelAdvanceRequestToAttach : employee.getTravelAdvanceRequestCollection1()) {
                travelAdvanceRequestCollection1TravelAdvanceRequestToAttach = em.getReference(travelAdvanceRequestCollection1TravelAdvanceRequestToAttach.getClass(), travelAdvanceRequestCollection1TravelAdvanceRequestToAttach.getTradvNum());
                attachedTravelAdvanceRequestCollection1.add(travelAdvanceRequestCollection1TravelAdvanceRequestToAttach);
            }
            employee.setTravelAdvanceRequestCollection1(attachedTravelAdvanceRequestCollection1);
            em.persist(employee);
            for (TravelAdvanceRequest travelAdvanceRequestCollectionTravelAdvanceRequest : employee.getTravelAdvanceRequestCollection()) {
                Employee oldPreparedByOfTravelAdvanceRequestCollectionTravelAdvanceRequest = travelAdvanceRequestCollectionTravelAdvanceRequest.getPreparedBy();
                travelAdvanceRequestCollectionTravelAdvanceRequest.setPreparedBy(employee);
                travelAdvanceRequestCollectionTravelAdvanceRequest = em.merge(travelAdvanceRequestCollectionTravelAdvanceRequest);
                if (oldPreparedByOfTravelAdvanceRequestCollectionTravelAdvanceRequest != null) {
                    oldPreparedByOfTravelAdvanceRequestCollectionTravelAdvanceRequest.getTravelAdvanceRequestCollection().remove(travelAdvanceRequestCollectionTravelAdvanceRequest);
                    oldPreparedByOfTravelAdvanceRequestCollectionTravelAdvanceRequest = em.merge(oldPreparedByOfTravelAdvanceRequestCollectionTravelAdvanceRequest);
                }
            }
            for (TravelAdvanceRequest travelAdvanceRequestCollection1TravelAdvanceRequest : employee.getTravelAdvanceRequestCollection1()) {
                Employee oldRequestorOfTravelAdvanceRequestCollection1TravelAdvanceRequest = travelAdvanceRequestCollection1TravelAdvanceRequest.getRequestor();
                travelAdvanceRequestCollection1TravelAdvanceRequest.setRequestor(employee);
                travelAdvanceRequestCollection1TravelAdvanceRequest = em.merge(travelAdvanceRequestCollection1TravelAdvanceRequest);
                if (oldRequestorOfTravelAdvanceRequestCollection1TravelAdvanceRequest != null) {
                    oldRequestorOfTravelAdvanceRequestCollection1TravelAdvanceRequest.getTravelAdvanceRequestCollection1().remove(travelAdvanceRequestCollection1TravelAdvanceRequest);
                    oldRequestorOfTravelAdvanceRequestCollection1TravelAdvanceRequest = em.merge(oldRequestorOfTravelAdvanceRequestCollection1TravelAdvanceRequest);
                }
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

    public void edit(Employee employee) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Employee persistentEmployee = em.find(Employee.class, employee.getEmployeeId());
            Collection<TravelAdvanceRequest> travelAdvanceRequestCollectionOld = persistentEmployee.getTravelAdvanceRequestCollection();
            Collection<TravelAdvanceRequest> travelAdvanceRequestCollectionNew = employee.getTravelAdvanceRequestCollection();
            Collection<TravelAdvanceRequest> travelAdvanceRequestCollection1Old = persistentEmployee.getTravelAdvanceRequestCollection1();
            Collection<TravelAdvanceRequest> travelAdvanceRequestCollection1New = employee.getTravelAdvanceRequestCollection1();
            List<String> illegalOrphanMessages = null;
            for (TravelAdvanceRequest travelAdvanceRequestCollectionOldTravelAdvanceRequest : travelAdvanceRequestCollectionOld) {
                if (!travelAdvanceRequestCollectionNew.contains(travelAdvanceRequestCollectionOldTravelAdvanceRequest)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TravelAdvanceRequest " + travelAdvanceRequestCollectionOldTravelAdvanceRequest + " since its preparedBy field is not nullable.");
                }
            }
            for (TravelAdvanceRequest travelAdvanceRequestCollection1OldTravelAdvanceRequest : travelAdvanceRequestCollection1Old) {
                if (!travelAdvanceRequestCollection1New.contains(travelAdvanceRequestCollection1OldTravelAdvanceRequest)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain TravelAdvanceRequest " + travelAdvanceRequestCollection1OldTravelAdvanceRequest + " since its requestor field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<TravelAdvanceRequest> attachedTravelAdvanceRequestCollectionNew = new ArrayList<TravelAdvanceRequest>();
            for (TravelAdvanceRequest travelAdvanceRequestCollectionNewTravelAdvanceRequestToAttach : travelAdvanceRequestCollectionNew) {
                travelAdvanceRequestCollectionNewTravelAdvanceRequestToAttach = em.getReference(travelAdvanceRequestCollectionNewTravelAdvanceRequestToAttach.getClass(), travelAdvanceRequestCollectionNewTravelAdvanceRequestToAttach.getTradvNum());
                attachedTravelAdvanceRequestCollectionNew.add(travelAdvanceRequestCollectionNewTravelAdvanceRequestToAttach);
            }
            travelAdvanceRequestCollectionNew = attachedTravelAdvanceRequestCollectionNew;
            employee.setTravelAdvanceRequestCollection(travelAdvanceRequestCollectionNew);
            Collection<TravelAdvanceRequest> attachedTravelAdvanceRequestCollection1New = new ArrayList<TravelAdvanceRequest>();
            for (TravelAdvanceRequest travelAdvanceRequestCollection1NewTravelAdvanceRequestToAttach : travelAdvanceRequestCollection1New) {
                travelAdvanceRequestCollection1NewTravelAdvanceRequestToAttach = em.getReference(travelAdvanceRequestCollection1NewTravelAdvanceRequestToAttach.getClass(), travelAdvanceRequestCollection1NewTravelAdvanceRequestToAttach.getTradvNum());
                attachedTravelAdvanceRequestCollection1New.add(travelAdvanceRequestCollection1NewTravelAdvanceRequestToAttach);
            }
            travelAdvanceRequestCollection1New = attachedTravelAdvanceRequestCollection1New;
            employee.setTravelAdvanceRequestCollection1(travelAdvanceRequestCollection1New);
            employee = em.merge(employee);
            for (TravelAdvanceRequest travelAdvanceRequestCollectionNewTravelAdvanceRequest : travelAdvanceRequestCollectionNew) {
                if (!travelAdvanceRequestCollectionOld.contains(travelAdvanceRequestCollectionNewTravelAdvanceRequest)) {
                    Employee oldPreparedByOfTravelAdvanceRequestCollectionNewTravelAdvanceRequest = travelAdvanceRequestCollectionNewTravelAdvanceRequest.getPreparedBy();
                    travelAdvanceRequestCollectionNewTravelAdvanceRequest.setPreparedBy(employee);
                    travelAdvanceRequestCollectionNewTravelAdvanceRequest = em.merge(travelAdvanceRequestCollectionNewTravelAdvanceRequest);
                    if (oldPreparedByOfTravelAdvanceRequestCollectionNewTravelAdvanceRequest != null && !oldPreparedByOfTravelAdvanceRequestCollectionNewTravelAdvanceRequest.equals(employee)) {
                        oldPreparedByOfTravelAdvanceRequestCollectionNewTravelAdvanceRequest.getTravelAdvanceRequestCollection().remove(travelAdvanceRequestCollectionNewTravelAdvanceRequest);
                        oldPreparedByOfTravelAdvanceRequestCollectionNewTravelAdvanceRequest = em.merge(oldPreparedByOfTravelAdvanceRequestCollectionNewTravelAdvanceRequest);
                    }
                }
            }
            for (TravelAdvanceRequest travelAdvanceRequestCollection1NewTravelAdvanceRequest : travelAdvanceRequestCollection1New) {
                if (!travelAdvanceRequestCollection1Old.contains(travelAdvanceRequestCollection1NewTravelAdvanceRequest)) {
                    Employee oldRequestorOfTravelAdvanceRequestCollection1NewTravelAdvanceRequest = travelAdvanceRequestCollection1NewTravelAdvanceRequest.getRequestor();
                    travelAdvanceRequestCollection1NewTravelAdvanceRequest.setRequestor(employee);
                    travelAdvanceRequestCollection1NewTravelAdvanceRequest = em.merge(travelAdvanceRequestCollection1NewTravelAdvanceRequest);
                    if (oldRequestorOfTravelAdvanceRequestCollection1NewTravelAdvanceRequest != null && !oldRequestorOfTravelAdvanceRequestCollection1NewTravelAdvanceRequest.equals(employee)) {
                        oldRequestorOfTravelAdvanceRequestCollection1NewTravelAdvanceRequest.getTravelAdvanceRequestCollection1().remove(travelAdvanceRequestCollection1NewTravelAdvanceRequest);
                        oldRequestorOfTravelAdvanceRequestCollection1NewTravelAdvanceRequest = em.merge(oldRequestorOfTravelAdvanceRequestCollection1NewTravelAdvanceRequest);
                    }
                }
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
                Integer id = employee.getEmployeeId();
                if (findEmployee(id) == null) {
                    throw new NonexistentEntityException("The employee with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Employee employee;
            try {
                employee = em.getReference(Employee.class, id);
                employee.getEmployeeId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The employee with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<TravelAdvanceRequest> travelAdvanceRequestCollectionOrphanCheck = employee.getTravelAdvanceRequestCollection();
            for (TravelAdvanceRequest travelAdvanceRequestCollectionOrphanCheckTravelAdvanceRequest : travelAdvanceRequestCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the TravelAdvanceRequest " + travelAdvanceRequestCollectionOrphanCheckTravelAdvanceRequest + " in its travelAdvanceRequestCollection field has a non-nullable preparedBy field.");
            }
            Collection<TravelAdvanceRequest> travelAdvanceRequestCollection1OrphanCheck = employee.getTravelAdvanceRequestCollection1();
            for (TravelAdvanceRequest travelAdvanceRequestCollection1OrphanCheckTravelAdvanceRequest : travelAdvanceRequestCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Employee (" + employee + ") cannot be destroyed since the TravelAdvanceRequest " + travelAdvanceRequestCollection1OrphanCheckTravelAdvanceRequest + " in its travelAdvanceRequestCollection1 field has a non-nullable requestor field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(employee);
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

    public List<Employee> findEmployeeEntities() {
        return findEmployeeEntities(true, -1, -1);
    }

    public List<Employee> findEmployeeEntities(int maxResults, int firstResult) {
        return findEmployeeEntities(false, maxResults, firstResult);
    }

    private List<Employee> findEmployeeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Employee.class));
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

    public Employee findEmployee(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Employee.class, id);
        } finally {
            em.close();
        }
    }

    public int getEmployeeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Employee> rt = cq.from(Employee.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
