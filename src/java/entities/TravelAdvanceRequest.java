/*
 * Copyright 2015
 *  http://wazza.co.ke
 * 8:54:19 PM  : Apr 25, 2016
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author kelli
 */
@Entity
@Table(name = "tadv_request")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TravelAdvanceRequest.findAll", query = "SELECT t FROM TravelAdvanceRequest t"),
    @NamedQuery(name = "TravelAdvanceRequest.findByTradvNum", query = "SELECT t FROM TravelAdvanceRequest t WHERE t.tradvNum = :tradvNum"),
    @NamedQuery(name = "TravelAdvanceRequest.findBySubmitDate", query = "SELECT t FROM TravelAdvanceRequest t WHERE t.submitDate = :submitDate"),
    @NamedQuery(name = "TravelAdvanceRequest.findByFromDate", query = "SELECT t FROM TravelAdvanceRequest t WHERE t.fromDate = :fromDate"),
    @NamedQuery(name = "TravelAdvanceRequest.findByToDate", query = "SELECT t FROM TravelAdvanceRequest t WHERE t.toDate = :toDate"),
    @NamedQuery(name = "TravelAdvanceRequest.findByTravelPurpose", query = "SELECT t FROM TravelAdvanceRequest t WHERE t.travelPurpose = :travelPurpose"),
    @NamedQuery(name = "TravelAdvanceRequest.findByTravelType", query = "SELECT t FROM TravelAdvanceRequest t WHERE t.travelType = :travelType"),
    @NamedQuery(name = "TravelAdvanceRequest.findByRequestAmount", query = "SELECT t FROM TravelAdvanceRequest t WHERE t.requestAmount = :requestAmount"),
    @NamedQuery(name = "TravelAdvanceRequest.findByStatus", query = "SELECT t FROM TravelAdvanceRequest t WHERE t.status = :status"),
    @NamedQuery(name = "TravelAdvanceRequest.findByDestination", query = "SELECT t FROM TravelAdvanceRequest t WHERE t.destination = :destination"),
    @NamedQuery(name = "TravelAdvanceRequest.findByCountry", query = "SELECT t FROM TravelAdvanceRequest t WHERE t.country = :country"),
    @NamedQuery(name = "TravelAdvanceRequest.findByReqNotes", query = "SELECT t FROM TravelAdvanceRequest t WHERE t.reqNotes = :reqNotes")})
public class TravelAdvanceRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tradv_num")
    private Integer tradvNum;
    @Basic(optional = false)
    @NotNull
    @Column(name = "submit_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date submitDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "from_date")
    @Temporal(TemporalType.DATE)
    private Date fromDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "to_date")
    @Temporal(TemporalType.DATE)
    private Date toDate;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 300)
    @Column(name = "travel_purpose")
    private String travelPurpose;
    @Size(max = 25)
    @Column(name = "travel_type")
    private String travelType;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "request_amount")
    private BigDecimal requestAmount;
    @Size(max = 14)
    @Column(name = "status")
    private String status;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "destination")
    private String destination;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "country")
    private String country;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "req_notes")
    private String reqNotes;
    @JoinColumn(name = "prepared_by", referencedColumnName = "employee_id")
    @ManyToOne(optional = false)
    private Employee preparedBy;
    @JoinColumn(name = "requestor", referencedColumnName = "employee_id")
    @ManyToOne(optional = false)
    private Employee requestor;

    public TravelAdvanceRequest() {
    }

    public TravelAdvanceRequest(Integer tradvNum) {
        this.tradvNum = tradvNum;
    }

    public TravelAdvanceRequest(Integer tradvNum, Date submitDate, Date fromDate, Date toDate, String travelPurpose, BigDecimal requestAmount, String destination, String country, String reqNotes) {
        this.tradvNum = tradvNum;
        this.submitDate = submitDate;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.travelPurpose = travelPurpose;
        this.requestAmount = requestAmount;
        this.destination = destination;
        this.country = country;
        this.reqNotes = reqNotes;
    }

    public Integer getTradvNum() {
        return tradvNum;
    }

    public void setTradvNum(Integer tradvNum) {
        this.tradvNum = tradvNum;
    }

    public Date getSubmitDate() {
        return submitDate;
    }

    public void setSubmitDate(Date submitDate) {
        this.submitDate = submitDate;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getTravelPurpose() {
        return travelPurpose;
    }

    public void setTravelPurpose(String travelPurpose) {
        this.travelPurpose = travelPurpose;
    }

    public String getTravelType() {
        return travelType;
    }

    public void setTravelType(String travelType) {
        this.travelType = travelType;
    }

    public BigDecimal getRequestAmount() {
        return requestAmount;
    }

    public void setRequestAmount(BigDecimal requestAmount) {
        this.requestAmount = requestAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getReqNotes() {
        return reqNotes;
    }

    public void setReqNotes(String reqNotes) {
        this.reqNotes = reqNotes;
    }

    public Employee getPreparedBy() {
        return preparedBy;
    }

    public void setPreparedBy(Employee preparedBy) {
        this.preparedBy = preparedBy;
    }

    public Employee getRequestor() {
        return requestor;
    }

    public void setRequestor(Employee requestor) {
        this.requestor = requestor;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tradvNum != null ? tradvNum.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TravelAdvanceRequest)) {
            return false;
        }
        TravelAdvanceRequest other = (TravelAdvanceRequest) object;
        if ((this.tradvNum == null && other.tradvNum != null) || (this.tradvNum != null && !this.tradvNum.equals(other.tradvNum))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.TravelAdvanceRequest[ tradvNum=" + tradvNum + " ]";
    }
    
}
