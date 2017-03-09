/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.entity.realty;

import com.cspark.constraints.validation.ByteLength;
import com.cspark.consult.entity.CreatedAndUpdatedDateEntityListener;
import com.cspark.consult.entity.HasCreatedAndUpdatedDate;
import com.cspark.consult.entity.realty.user.RealtyUser;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.Objects;

/**
 * Created by cspark on 2017. 2. 7..
 */
@Entity
@Table(name = "CONTACT", uniqueConstraints = {
        @UniqueConstraint(name = "UIX_CONTACT_NAME_MOBILE", columnNames = {"fullname", "mobileNo"})
})
@EntityListeners({ CreatedAndUpdatedDateEntityListener.class })
public class Contact implements HasCreatedAndUpdatedDate {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @ByteLength(min = 4, max = 50)
    private String fullname;

    /**
     * one's position (in an organization).
     */
    private String position;

    private String gender;

    private String typeOfEnterprise;

    private String typeOfIndustry;

    private String companyName;

    private String deptName;

    @NotBlank
    @Email
    private String email;

    @Pattern(regexp = "[0-9]{3}-[0-9]{3,4}-[0-9]{4}")
    private String mobileNo;

    @Pattern(regexp = "([0-9]{2,3}-[0-9]{3,4}-[0-9]{4})?")
    private String phoneNo;

    @Pattern(regexp = "([0-9]{2,3}-[0-9]{3,4}-[0-9]{4})?")
    private String faxNo;

    private String address;

    private String note;

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

    public Contact() {
    }

    public Contact(Long id) {
        this.id = id;
    }

    public Contact(String fullname, String mobileNo, String companyName) {
        this.fullname = fullname;
        this.mobileNo = mobileNo;
        this.companyName = companyName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getTypeOfEnterprise() {
        return typeOfEnterprise;
    }

    public void setTypeOfEnterprise(String typeOfEnterprise) {
        this.typeOfEnterprise = typeOfEnterprise;
    }

    public String getTypeOfIndustry() {
        return typeOfIndustry;
    }

    public void setTypeOfIndustry(String typeOfIndustry) {
        this.typeOfIndustry = typeOfIndustry;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getFaxNo() {
        return faxNo;
    }

    public void setFaxNo(String faxNo) {
        this.faxNo = faxNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(fullname, contact.fullname) &&
                Objects.equals(mobileNo, contact.mobileNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fullname, mobileNo);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Contact{");
        sb.append("id=").append(id);
        sb.append(", fullname='").append(fullname).append('\'');
        sb.append(", position='").append(position).append('\'');
        sb.append(", gender='").append(gender).append('\'');
        sb.append(", typeOfEnterprise='").append(typeOfEnterprise).append('\'');
        sb.append(", typeOfIndustry='").append(typeOfIndustry).append('\'');
        sb.append(", companyName='").append(companyName).append('\'');
        sb.append(", deptName='").append(deptName).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", mobileNo='").append(mobileNo).append('\'');
        sb.append(", phoneNo='").append(phoneNo).append('\'');
        sb.append(", faxNo='").append(faxNo).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", note='").append(note).append('\'');
        sb.append(", createdUser=").append(createdUser);
        sb.append(", createdDate=").append(createdDate);
        sb.append(", updatedUser=").append(updatedUser);
        sb.append(", updatedDate=").append(updatedDate);
        sb.append('}');
        return sb.toString();
    }
}
