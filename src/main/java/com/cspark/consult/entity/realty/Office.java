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
import com.cspark.consult.entity.realty.consulting.Proposal;
import com.cspark.consult.entity.realty.user.RealtyUser;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cspark on 2017. 2. 8..
 */
@Entity
@EntityListeners({ CreatedAndUpdatedDateEntityListener.class })
public class Office implements HasCreatedAndUpdatedDate {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 건물
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;

    /**
     * 컨설팅된 제안서.
     */
    @OneToMany(mappedBy = "office")
    private Set<Consulting> consultings = new HashSet<>();

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "typeCd", column = @Column(name = "ITEM_TYPE_CD")),
            @AttributeOverride(name = "dealCd", column = @Column(name = "ITEM_DEAL_CD"))
    })
    private Item item;

    /**
     * 해당 층수 (target floor)
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "fromValue", column = @Column(name = "FLOOR_FROM")),
            @AttributeOverride(name = "toValue", column = @Column(name = "FLOOR_TO")),
            @AttributeOverride(name = "note", column = @Column(name = "FLOOR_NOTE"))
    })
    private TargetFloor targetFloor;

    /**
     * 해당 면적 (target targetArea)
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "rental", column = @Column(name = "rental_py")),
            @AttributeOverride(name = "exclusive", column = @Column(name = "exclusive_py"))
    })
    private TargetArea targetArea;

    /**
     * 보증금
     */
    private Integer deposit;

    /**
     * 임대료(월세)
     */
    private Integer rent;

    /**
     * 관리비
     */
    private Integer maintenanceFee;

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

    public Office() {
    }

    public Office(long id) {
        this.id = id;
    }

    public Office(Item item, TargetFloor targetFloor) {
        this.item = item;
        this.targetFloor = targetFloor;
    }

    public Office(Long id, Item item, TargetFloor targetFloor) {
        this.id = id;
        this.item = item;
        this.targetFloor = targetFloor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Set<Consulting> getConsultings() {
        return consultings;
    }

    public void setConsultings(Set<Consulting> consultings) {
        this.consultings = consultings;
    }

    public void addProposal(Proposal proposal) {
        consultings.add(new Consulting(proposal, this));
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public TargetFloor getTargetFloor() {
        return targetFloor;
    }

    public void setTargetFloor(TargetFloor targetFloor) {
        this.targetFloor = targetFloor;
    }

    public TargetArea getTargetArea() {
        return targetArea;
    }

    public void setTargetArea(TargetArea targetArea) {
        this.targetArea = targetArea;
    }

    public Integer getDeposit() {
        return deposit;
    }

    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }

    public Integer getRent() {
        return rent;
    }

    public void setRent(Integer rent) {
        this.rent = rent;
    }

    public Integer getMaintenanceFee() {
        return maintenanceFee;
    }

    public void setMaintenanceFee(Integer maintenanceFee) {
        this.maintenanceFee = maintenanceFee;
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
        final StringBuffer sb = new StringBuffer("Office{");
        sb.append("id=").append(id);
        if (building != null)
            sb.append(", building_id=").append(building.getId());
        sb.append(", consultings=").append(consultings);
        sb.append(", item=").append(item);
        sb.append(", targetFloor=").append(targetFloor);
        sb.append(", targetArea=").append(targetArea);
        sb.append(", deposit=").append(deposit);
        sb.append(", rent=").append(rent);
        sb.append(", maintenanceFee=").append(maintenanceFee);
        sb.append('}');
        return sb.toString();
    }

    @Embeddable
    public static class Item {

        private String typeCd;

        private String dealCd;

        public Item() {
        }

        public Item(String typeCd, String dealCd) {
            this.typeCd = typeCd;
            this.dealCd = dealCd;
        }

        public String getTypeCd() {
            return typeCd;
        }

        public void setTypeCd(String typeCd) {
            this.typeCd = typeCd;
        }

        public String getDealCd() {
            return dealCd;
        }

        public void setDealCd(String dealCd) {
            this.dealCd = dealCd;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Item{");
            sb.append("type='").append(typeCd).append('\'');
            sb.append(", dealCd='").append(dealCd).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    @Embeddable
    public static class TargetFloor {

        private Integer fromValue;

        private Integer toValue;

        private String note;

        public TargetFloor() {
        }

        public TargetFloor(Integer fromValue, Integer toValue, String note) {
            this.fromValue = fromValue;
            this.toValue = toValue;
            this.note = note;
        }

        public Integer getFromValue() {
            return fromValue;
        }

        public Integer getToValue() {
            return toValue;
        }

        public String getNote() {
            return note;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("TargetFloor{");
            sb.append("fromValue=").append(fromValue);
            sb.append(", toValue=").append(toValue);
            sb.append(", note='").append(note).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    @Embeddable
    public static class TargetArea {

        @Digits(integer=10, fraction=2)
        @Column(precision=12, scale=2, columnDefinition = "DECIMAL(12,2)")
        private Double rental;

        @Digits(integer=10, fraction=2)
        @Column(precision=12, scale=2, columnDefinition = "DECIMAL(12,2)")
        private Double exclusive;

        public Double getRental() {
            return rental;
        }

        public void setRental(Double rental) {
            this.rental = rental;
        }

        public Double getExclusive() {
            return exclusive;
        }

        public void setExclusive(Double exclusive) {
            this.exclusive = exclusive;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("TargetArea{");
            sb.append("rental=").append(rental);
            sb.append(", exclusive=").append(exclusive);
            sb.append('}');
            return sb.toString();
        }
    }

}
