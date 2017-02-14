/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.entity.realty;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by cspark on 2017. 2. 8..
 */
@Entity
public class Office {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "building_id", nullable = false)
    private Building building;

    @OneToMany(mappedBy = "office")
    private Set<Consulting> consultings = new HashSet<>();

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "type", column = @Column(name = "ITEM_TYPE")),
            @AttributeOverride(name = "deal", column = @Column(name = "ITEM_DEAL"))
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
    private Floor floor;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "pyeong", column = @Column(name = "area_py")),
            @AttributeOverride(name = "squareMeter", column = @Column(name = "area_m2"))
    })
    private Area area;

    /**
     * 보증금
     */
    private Integer deposit;

    /**
     * 월세
     */
    private Integer monthlyRent;

    /**
     * 관리비
     */
    private Integer maintenanceFee;

    public Office() {
    }

    public Office(long id) {
        this.id = id;
    }

    public Office(Item item, Floor floor) {
        this.item = item;
        this.floor = floor;
    }

    public Office(Long id, Item item, Floor floor) {
        this.id = id;
        this.item = item;
        this.floor = floor;
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

    public Floor getFloor() {
        return floor;
    }

    public void setFloor(Floor floor) {
        this.floor = floor;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public Integer getDeposit() {
        return deposit;
    }

    public void setDeposit(Integer deposit) {
        this.deposit = deposit;
    }

    public Integer getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(Integer monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    public Integer getMaintenanceFee() {
        return maintenanceFee;
    }

    public void setMaintenanceFee(Integer maintenanceFee) {
        this.maintenanceFee = maintenanceFee;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Office{");
        sb.append("id=").append(id);
        if (building != null)
            sb.append(", building_id=").append(building.getId());
        sb.append(", consultings=").append(consultings);
        sb.append(", item=").append(item);
        sb.append(", floor=").append(floor);
        sb.append(", area=").append(area);
        sb.append(", deposit=").append(deposit);
        sb.append(", monthlyRent=").append(monthlyRent);
        sb.append(", maintenanceFee=").append(maintenanceFee);
        sb.append('}');
        return sb.toString();
    }

    @Embeddable
    public static class Item {

        private String type;

        private String deal;

        public Item() {
        }

        public Item(String type, String deal) {
            this.type = type;
            this.deal = deal;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDeal() {
            return deal;
        }

        public void setDeal(String deal) {
            this.deal = deal;
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Item{");
            sb.append("type='").append(type).append('\'');
            sb.append(", deal='").append(deal).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

    @Embeddable
    public static class Floor {

        private Integer fromValue;

        private Integer toValue;

        private String note;

        public Floor() {
        }

        public Floor(Integer fromValue, Integer toValue, String note) {
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
            final StringBuffer sb = new StringBuffer("Floor{");
            sb.append("fromValue=").append(fromValue);
            sb.append(", toValue=").append(toValue);
            sb.append(", note='").append(note).append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

}
