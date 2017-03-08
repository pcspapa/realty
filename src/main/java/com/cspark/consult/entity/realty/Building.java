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
import com.cspark.consult.entity.realty.building.Address;
import com.cspark.consult.entity.realty.building.TypicalFloor;
import com.cspark.consult.entity.realty.user.RealtyUser;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by cspark on 2017. 2. 7..
 */
@Entity
@Table(name = "BUILDING", uniqueConstraints = {
        @UniqueConstraint(name = "UIX_BUILDING_ADDRESS", columnNames = {"address_zipcode", "address_street", "address_city", "address_building_name"})
})
@EntityListeners({ CreatedAndUpdatedDateEntityListener.class })
public class Building implements HasCreatedAndUpdatedDate {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 주소
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "zipcode", column = @Column(name = "ADDRESS_ZIPCODE")),
            @AttributeOverride(name = "street", column = @Column(name = "ADDRESS_STREET")),
            @AttributeOverride(name = "city", column = @Column(name = "ADDRESS_CITY")),
            @AttributeOverride(name = "buildingName", column = @Column(name = "ADDRESS_BUILDING_NAME"))
    })
    private Address address;

    /**
     * 빌딩 담당자
     */
    @ElementCollection
    @CollectionTable(
            name = "BUILDING_CONTACT",
            joinColumns = @JoinColumn(name = "BUILDING_ID"),
            foreignKey = @ForeignKey(name="FK_BUILDING_ID")
    )
    @OrderBy("director")
    private Set<BuildingContact> buildingContacts = new HashSet<>();

    /**
     * 빌딩의 사무실
     */
    @OneToMany(mappedBy = "building", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Office> offices = new HashSet<>();

    /**
     * 지하 층수
     */
    private Integer basementFloor;

    /**
     * 지상 층수
     */
    private Integer groundFloor;


    /**
     * 관리_기준층 면적 (Typical TargetFloor Area)
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "rental.pyeong", column = @Column(name = "TYPICAL_FLOOR_RENTAL_AREA_PY")),
            @AttributeOverride(name = "rental.squareMeter", column = @Column(name = "TYPICAL_FLOOR_RENTAL_AREA_M2")),
            @AttributeOverride(name = "exclusive.pyeong", column = @Column(name = "TYPICAL_FLOOR_EXCLUSIVE_AREA_PY")),
            @AttributeOverride(name = "exclusive.squareMeter", column = @Column(name = "TYPICAL_FLOOR_EXCLUSIVE_AREA_M2")),
            @AttributeOverride(name = "exclusiveRatio", column = @Column(name = "TYPICAL_FLOOR_EXCLUSIVE_RT")),
            @AttributeOverride(name = "floorHeight", column = @Column(name = "TYPICAL_FLOOR_HEIGHT"))
    })
    private TypicalFloor typicalFloor;

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

    public TypicalFloor getTypicalFloor() {
        return typicalFloor;
    }

    public void setTypicalFloor(TypicalFloor typicalFloor) {
        this.typicalFloor = typicalFloor;
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


    /**
     * @return The building name.
     */
    public String getName() {
        return address.getBuildingName();
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