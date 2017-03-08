/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.entity.realty.consulting;

import com.cspark.consult.entity.CreatedAndUpdatedDateEntityListener;
import com.cspark.consult.entity.HasCreatedAndUpdatedDate;
import com.cspark.consult.entity.realty.Consulting;
import com.cspark.consult.entity.realty.Contact;
import com.cspark.consult.entity.realty.Office;
import com.cspark.consult.entity.realty.embeddable.CodeNote;
import com.cspark.consult.entity.realty.embeddable.ParkingFacility;
import com.cspark.consult.entity.realty.embeddable.SubwayAccessibility;
import com.cspark.consult.entity.realty.user.RealtyUser;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 의뢰서
 *
 * Created by cspark on 2017. 2. 8..
 */
@Entity
@EntityListeners({ CreatedAndUpdatedDateEntityListener.class })
public class Proposal implements HasCreatedAndUpdatedDate {

    @Id
    @GeneratedValue
    private Long id;

    /**
     * 의뢰인 (Client)
     */
    @ManyToOne(optional = false)
    private Contact client;

    /**
     * 컨설턴트 (Consultant)
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "consultant_username", referencedColumnName = "username")
    private RealtyUser consultant;

    /**
     * 컨설팅s (Consultings)
     */
    @OneToMany(mappedBy = "proposal")
    private Set<Consulting> consultings = new HashSet<>();

    /**
     * 형태(ex. 사무실 / 임대)
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "typeCd", column = @Column(name = "item_type_cd")),
            @AttributeOverride(name = "dealCd", column = @Column(name = "item_deal_cd"))
    })
    private Office.Item item;

    /**
     * 건물형태코드 (Building Type CD)
     * ex. 대형건물, 중소형건물, 통임대, 단독주택
     */
    private String buildingTypeCd;

    /**
     * 면적
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "fromValue", column = @Column(name = "area_from")),
            @AttributeOverride(name = "toValue", column = @Column(name = "area_to"))
    })
    private Area area;

    /**
     * 사용형태코드 (Usage Type Checkbox)
     * ex. 한층전체사용, 한층 중 일부 사용가능, 복수층 사용가능, 건물전체 사용가능, 사용없음
     */
    private String usageTypeCb;

    /**
     * 의뢰층 (Proposal Floor)
     */
    @Digits(integer = 4, fraction = 0)
    @Column(precision = 4)
    private Integer floor;

    /**
     * 보증금 (Deposit)
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "fromValue", column = @Column(name = "deposit_from")),
            @AttributeOverride(name = "toValue", column = @Column(name = "deposit_to"))
    })
    private Budget deposit;

    /**
     * 전세금 (Lease)
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "fromValue", column = @Column(name = "lease_from")),
            @AttributeOverride(name = "toValue", column = @Column(name = "lease_to"))
    })
    private Budget lease;

    /**
     * 임대료(월세) (monthly rent)
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "fromValue", column = @Column(name = "rent_from")),
            @AttributeOverride(name = "toValue", column = @Column(name = "rent_to"))
    })
    private Budget rent;

    /**
     * 주요권역코드 (Main Region Checkbox)
     * ex. 강남서초권, 도심권, 분당권, 일산권, 기타
     */
    private String mainRegionCb;

    /**
     * 역세권 접근성 (Subway Accessibility, Railway Station Sphere)
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "lineCd", column = @Column(name = "subway_line_cd")),
            @AttributeOverride(name = "distance", column = @Column(name = "subway_distance")),
            @AttributeOverride(name = "minute", column = @Column(name = "subway_minute"))
    })
    private SubwayAccessibility subwayAccessibility;

    /**
     * 대로변 접근성 (Main Street Accessibility)
     */
    private String streetAccessibility;

    /**
     * 지역명칭 (Town Name)
     */
    private String townName;

    /**
     * 인테리어 (Interior)
     */
    private String interior;

    /**
     * 냉난방방식코드 (Air Conditioning System Code)
     */
    private String airConditioningSystemCd;

    /**
     * 주차방식 (Parking Facility)
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "capacity", column = @Column(name = "parking_capacity")),
            @AttributeOverride(name = "carType", column = @Column(name = "parking_car_type"))
    })
    private ParkingFacility parkingFacility;

    /**
     * 입주 (Resident)
     */
    private String resident;

    /**
     * 추천 (Recommendation)
     * ex. 온라인 접수, 회사 접수, 지인 소개, 부동산, 기타
     */
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "code", column = @Column(name = "recommendation_code")),
            @AttributeOverride(name = "note", column = @Column(name = "recommendation_note"))
    })
    private CodeNote recommendation;

    /**
     * 추가사항 (Note)
     */
    private String note;

    /**
     * 진행상태
     */
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

    public Proposal(long id, Contact client, Office.Item item, Area area) {
        this.id = id;
        this.client = client;
        this.item = item;
        this.area = area;
    }

    public Proposal(Contact client, Office.Item item, Area area) {
        this.client = client;
        this.item = item;
        this.area = area;
    }

    public Proposal(Contact client, RealtyUser consultant, Office.Item item, Area area, RealtyUser createdUser, RealtyUser updatedUser) {
        this.client = client;
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

    public Contact getClient() {
        return client;
    }

    public void setClient(Contact client) {
        this.client = client;
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

    public String getBuildingTypeCd() {
        return buildingTypeCd;
    }

    public void setBuildingTypeCd(String buildingTypeCd) {
        this.buildingTypeCd = buildingTypeCd;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public String getUsageTypeCb() {
        return usageTypeCb;
    }

    public void setUsageTypeCb(String usageTypeCb) {
        this.usageTypeCb = usageTypeCb;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Budget getDeposit() {
        return deposit;
    }

    public void setDeposit(Budget deposit) {
        this.deposit = deposit;
    }

    public Budget getLease() {
        return lease;
    }

    public void setLease(Budget lease) {
        this.lease = lease;
    }

    public Budget getRent() {
        return rent;
    }

    public void setRent(Budget rent) {
        this.rent = rent;
    }

    public String getMainRegionCb() {
        return mainRegionCb;
    }

    public void setMainRegionCb(String mainRegionCb) {
        this.mainRegionCb = mainRegionCb;
    }

    public SubwayAccessibility getSubwayAccessibility() {
        return subwayAccessibility;
    }

    public void setSubwayAccessibility(SubwayAccessibility subwayAccessibility) {
        this.subwayAccessibility = subwayAccessibility;
    }

    public String getStreetAccessibility() {
        return streetAccessibility;
    }

    public void setStreetAccessibility(String streetAccessibility) {
        this.streetAccessibility = streetAccessibility;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }

    public String getInterior() {
        return interior;
    }

    public void setInterior(String interior) {
        this.interior = interior;
    }

    public String getAirConditioningSystemCd() {
        return airConditioningSystemCd;
    }

    public void setAirConditioningSystemCd(String airConditioningSystemCd) {
        this.airConditioningSystemCd = airConditioningSystemCd;
    }

    public ParkingFacility getParkingFacility() {
        return parkingFacility;
    }

    public void setParkingFacility(ParkingFacility parkingFacility) {
        this.parkingFacility = parkingFacility;
    }

    public String getResident() {
        return resident;
    }

    public void setResident(String resident) {
        this.resident = resident;
    }

    public CodeNote getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(CodeNote recommendation) {
        this.recommendation = recommendation;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
        sb.append(", client=").append(client);
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
