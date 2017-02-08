/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.entity;

import javax.persistence.*;

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

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "type", column = @Column(name = "ITEM_TYPE")),
            @AttributeOverride(name = "deal", column = @Column(name = "ITEM_DEAL"))
    })
    private Item item;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "fromValue", column = @Column(name = "TARGET_FLOOR_FROM")),
            @AttributeOverride(name = "toValue", column = @Column(name = "TARGET_FLOOR_TO")),
            @AttributeOverride(name = "note", column = @Column(name = "TARGET_FLOOR_NOTE"))
    })
    private TargetFloor targetFloor;


    public Office() {
    }

    public Office(Item item, TargetFloor targetFloor) {
        this.item = item;
        this.targetFloor = targetFloor;
    }

    public Office(Building building, Item item, TargetFloor targetFloor) {
        this.building = building;
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Office{");
        sb.append("id=").append(id);
        sb.append(", building_id=").append(building.getId());
        sb.append(", item=").append(item);
        sb.append(", targetFloor=").append(targetFloor);
        sb.append('}');
        return sb.toString();
    }
}
