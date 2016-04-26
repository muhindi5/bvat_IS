/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 9:13:39 AM  : Apr 20, 2016
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author kelli
 */
@Entity
@Table(name = "purchase_req")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PurchaseReq.findAll", query = "SELECT p FROM PurchaseReq p"),
    @NamedQuery(name = "PurchaseReq.findByPrnum", query = "SELECT p FROM PurchaseReq p WHERE p.prnum = :prnum"),
    @NamedQuery(name = "PurchaseReq.findByPrepdate", query = "SELECT p FROM PurchaseReq p WHERE p.prepdate = :prepdate"),
    @NamedQuery(name = "PurchaseReq.findByComments", query = "SELECT p FROM PurchaseReq p WHERE p.comments = :comments"),
    @NamedQuery(name = "PurchaseReq.findByPreqStatus", query = "SELECT p FROM PurchaseReq p WHERE p.preqStatus = :preqStatus")})
public class PurchaseReq implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "prnum")
    private Integer prnum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "prepdate")
    @Temporal(TemporalType.DATE)
    private Date prepdate;
    @Size(max = 300)
    @Column(name = "comments")
    private String comments;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 18)
    @Column(name = "preq_status")
    private String preqStatus;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "preqId")
    private Collection<Item> itemCollection;
    @JoinColumn(name = "preparedby_id", referencedColumnName = "employee_id")
    @ManyToOne(optional = false)
    private Employee preparedbyId;
    @JoinColumn(name = "requestor_id", referencedColumnName = "employee_id")
    @ManyToOne(optional = false)
    private Employee requestorId;

    public PurchaseReq() {
    }

    public PurchaseReq(Integer prnum) {
        this.prnum = prnum;
    }

    public PurchaseReq(Integer prnum, Date prepdate, String preqStatus) {
        this.prnum = prnum;
        this.prepdate = prepdate;
        this.preqStatus = preqStatus;
    }

    public Integer getPrnum() {
        return prnum;
    }

    public void setPrnum(Integer prnum) {
        this.prnum = prnum;
    }

    public Date getPrepdate() {
        return prepdate;
    }

    public void setPrepdate(Date prepdate) {
        this.prepdate = prepdate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getPreqStatus() {
        return preqStatus;
    }

    public void setPreqStatus(String preqStatus) {
        this.preqStatus = preqStatus;
    }

    @XmlTransient
    public Collection<Item> getItemCollection() {
        return itemCollection;
    }

    public void setItemCollection(Collection<Item> itemCollection) {
        this.itemCollection = itemCollection;
    }

    public Employee getPreparedbyId() {
        return preparedbyId;
    }

    public void setPreparedbyId(Employee preparedbyId) {
        this.preparedbyId = preparedbyId;
    }

    public Employee getRequestorId() {
        return requestorId;
    }

    public void setRequestorId(Employee requestorId) {
        this.requestorId = requestorId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (prnum != null ? prnum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PurchaseReq)) {
            return false;
        }
        PurchaseReq other = (PurchaseReq) object;
        if ((this.prnum == null && other.prnum != null) || (this.prnum != null && !this.prnum.equals(other.prnum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PurchaseReq[ prnum=" + prnum + " ]";
    }
    
}
