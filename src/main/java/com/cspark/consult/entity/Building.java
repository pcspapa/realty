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
import java.util.Objects;
import java.util.Set;

/**
 * Created by cspark on 2017. 2. 7..
 */
@Entity
@Table(name = "BUILDING", uniqueConstraints = {
        @UniqueConstraint(name = "UIX_BUILDING_ADDRESS", columnNames = {"zipcode", "street", "city", "buildingName"})
})
public class Building {

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private Address address;

    private Integer basementFloor;

    private Integer groundFloor;

    @ElementCollection
    @CollectionTable(
            name = "BUILDING_CONTACT",
            joinColumns = @JoinColumn(name = "BUILDING_ID"),
            foreignKey = @ForeignKey(name="FK_BUILDING_ID")
    )
    @OrderBy("director")
    private Set<BuildingContact> buildingContacts = new HashSet<>();

    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Office> offices = new HashSet<>();


    public Building() {
    }

    public Building(Long id) {
        this.id = id;
    }

    public Building(Address address) {
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Integer getBasementFloor() {
        return basementFloor;
    }

    public void setBasementFloor(Integer basementFloor) {
        this.basementFloor = basementFloor;
    }

    public Integer getGroundFloor() {
        return groundFloor;
    }

    public void setGroundFloor(Integer groundFloor) {
        this.groundFloor = groundFloor;
    }

    public Set<BuildingContact> getBuildingContacts() {
        return buildingContacts;
    }

    public void setBuildingContacts(Set<BuildingContact> buildingContacts) {
        this.buildingContacts = buildingContacts;
    }

    public void addBuildingContact(BuildingContact buildingContact) {
        buildingContacts.add(buildingContact);
    }

    public Set<Office> getOffices() {
        return offices;
    }

    public void setOffices(Set<Office> offices) {
        this.offices = offices;
    }

    public void addOffice(Office office) {
        office.setBuilding(this);
        offices.add(office);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Building building = (Building) o;
        return Objects.equals(address, building.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Building{");
        sb.append("id=").append(id);
        sb.append(", address=").append(address);
        sb.append(", basementFloor=").append(basementFloor);
        sb.append(", groundFloor=").append(groundFloor);
        sb.append(", buildingContacts=").append(buildingContacts);
        sb.append(", offices=").append(offices);
        sb.append('}');
        return sb.toString();
    }

}