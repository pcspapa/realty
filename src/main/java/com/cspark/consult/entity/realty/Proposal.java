/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.entity.realty;

import com.cspark.consult.entity.CreatedAndUpdatedDateEntityListener;
import com.cspark.consult.entity.HasCreatedAndUpdatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cspark on 2017. 2. 8..
 */
@Entity
@EntityListeners({ CreatedAndUpdatedDateEntityListener.class })
public class Proposal implements HasCreatedAndUpdatedDate {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    private Contact contact;

    @ManyToOne(optional = false)
    @JoinColumn(name = "consultant_username", referencedColumnName = "username")
    private RealtyUser consultant;

    @OneToMany(mappedBy = "proposal")
    private Set<Consulting> consultings = new HashSet<>();

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "type", column = @Column(name = "item_type")),
            @AttributeOverride(name = "deal", column = @Column(name = "item_deal"))
    })
    private Office.Item item;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "fromValue", column = @Column(name = "area_from")),
            @AttributeOverride(name = "toValue", column = @Column(name = "area_to"))
    })
    private Area area;

    private String state;

    @ManyToOne(optional = false)
    @JoinColumn(name = "created_username", referencedColumnName = "username")
    private RealtyUser createdUser;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date createdDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "updated_username", referencedColumnName = "username")
    private RealtyUser updatedUser;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date updatedDate;

    public Proposal() {
    }

    public Proposal(long id) {
        this.id= id;
    }

    public Proposal(long id, Contact contact, Office.Item item, Area area) {
        this.id = id;
        this.contact = contact;
        this.item = item;
        this.area = area;
    }

    public Proposal(Contact contact, Office.Item item, Area area) {
        this.contact = contact;
        this.item = item;
        this.area = area;
    }

    public Proposal(Contact contact, RealtyUser consultant, Office.Item item, Area area, RealtyUser createdUser, RealtyUser updatedUser) {
        this.contact = contact;
        this.consultant = consultant;
        this.item = item;
        this.area = area;
        this.createdUser = createdUser;
        this.updatedUser = updatedUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public RealtyUser getConsultant() {
        return consultant;
    }

    public void setConsultant(RealtyUser consultant) {
        this.consultant = consultant;
    }

    public Set<Consulting> getConsultings() {
        return consultings;
    }

    public void setConsultings(Set<Consulting> consultings) {
        this.consultings = consultings;
    }

    public void addOffice(Office office) {
        consultings.add(new Consulting(this, office));
    }

    public Office.Item getItem() {
        return item;
    }

    public void setItem(Office.Item item) {
        this.item = item;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public RealtyUser getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(RealtyUser createdUser) {
        this.createdUser = createdUser;
    }

    @Override
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public Date getCreatedDate() {
        return createdDate;
    }

    public RealtyUser getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(RealtyUser updatedUser) {
        this.updatedUser = updatedUser;
    }

    @Override
    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    public Date getUpdatedDate() {
        return updatedDate;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Proposal{");
        sb.append("id=").append(id);
        sb.append(", contact=").append(contact);
        sb.append(", consultings=").append(consultings);
        sb.append(", item=").append(item);
        sb.append(", area=").append(area);
        sb.append(", state='").append(state).append('\'');
        sb.append(", createdDate=").append(createdDate);
        sb.append(", updatedDate=").append(updatedDate);
        sb.append('}');
        return sb.toString();
    }

    @Embeddable
    public static class Area {

        private Integer fromValue;

        private Integer toValue;

        public Area() {
        }

        public Area(Integer fromValue, Integer toValue) {

            this.fromValue = fromValue;
            this.toValue = toValue;
        }

        public Integer getFromValue() {
            return fromValue;
        }

        public void setFromValue(Integer fromValue) {
            this.fromValue = fromValue;
        }

        public Integer getToValue() {
            return toValue;
        }

        public void setToValue(Integer toValue) {
            this.toValue = toValue;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Area{");
            sb.append("fromValue=").append(fromValue);
            sb.append(", toValue=").append(toValue);
            sb.append('}');
            return sb.toString();
        }
    }
}
