/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.entity.realty;

import javax.persistence.Embeddable;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Objects;

/**
 * Created by cspark on 2017. 2. 7..
 */
@Embeddable
public class BuildingContact {

    @ManyToOne
    @JoinColumn(
            name = "CONTACT_ID",
            nullable = false, updatable = false,
            foreignKey = @ForeignKey(name="FK_CONTACT_ID")
    )
    private Contact contact;

    private String director;

    public BuildingContact() {
    }

    public BuildingContact(Contact contact, String director) {
        this.contact = contact;
        this.director = director;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BuildingContact that = (BuildingContact) o;
        return Objects.equals(director, that.director);
    }

    @Override
    public int hashCode() {
        return Objects.hash(director);
    }
}
