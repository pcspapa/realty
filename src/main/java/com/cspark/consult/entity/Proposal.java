/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cspark on 2017. 2. 8..
 */
@Entity
public class Proposal {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    private Contact contact;

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
            @AttributeOverride(name = "fromValue", column = @Column(name = "target_area_from")),
            @AttributeOverride(name = "toValue", column = @Column(name = "target_area_to"))
    })
    private TargetArea targetArea;

    public Proposal() {
    }

    public Proposal(long id) {
        this.id= id;
    }

    public Proposal(Contact contact, Office.Item item, TargetArea targetArea) {
        this.contact = contact;
        this.item = item;
        this.targetArea = targetArea;
    }

    public Proposal(long id, Contact contact, Office.Item item, TargetArea targetArea) {
        this.id = id;
        this.contact = contact;
        this.item = item;
        this.targetArea = targetArea;
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

    public TargetArea getTargetArea() {
        return targetArea;
    }

    public void setTargetArea(TargetArea targetArea) {
        this.targetArea = targetArea;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Proposal{");
        sb.append("id=").append(id);
        sb.append(", contact=").append(contact);
        sb.append(", consultings=").append(consultings);
        sb.append(", item=").append(item);
        sb.append(", targetArea=").append(targetArea);
        sb.append('}');
        return sb.toString();
    }

    @Embeddable
    public static class TargetArea {

        private Integer fromValue;

        private Integer toValue;

        public TargetArea() {
        }

        public TargetArea(Integer fromValue, Integer toValue) {

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
    }
}
