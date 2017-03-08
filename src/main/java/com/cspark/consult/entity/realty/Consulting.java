/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.entity.realty;

import com.cspark.consult.entity.realty.consulting.Proposal;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Created by cspark on 2017. 2. 13..
 */
@Entity
public class Consulting {

    @EmbeddedId
    private Id id = new Id();

    @ManyToOne
    @JoinColumn(name = "PROPOSAL_ID", foreignKey = @ForeignKey(name = "FK_PROPOSAL_ID"), insertable = false, updatable = false)
    private Proposal proposal;

    @ManyToOne
    @JoinColumn(name = "OFFICE_ID", foreignKey = @ForeignKey(name = "FK_OFFICE_ID"), insertable = false, updatable = false)
    private Office office;

    private String state;

    public Consulting() {
    }

    public Consulting(Proposal proposal, Office office) {
        // Set fields
        this.proposal = proposal;
        this.office = office;

        // Set identifier values
        this.id.proposalId = proposal.getId();
        this.id.officeId = office.getId();

        // Guarantee referential integrity if made bidirectional
//        proposal.getConsultings().add(this);
//        office.getConsultings().add(this);
    }

    public Id getId() {
        return id;
    }

    public Proposal getProposal() {
        return proposal;
    }

    public Office getOffice() {
        return office;
    }

    public void setOffice(Office office) {
        this.office = office;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consulting that = (Consulting) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Consulting{");
        sb.append("id=").append(id);
        sb.append(", state='").append(state).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Embeddable
    public static class Id implements Serializable {

        @Column(name = "PROPOSAL_ID")
        protected Long proposalId;

        @Column(name = "OFFICE_ID")
        protected Long officeId;

        public Id() {
        }

        public Id(Long proposalId, Long officeId) {
            this.proposalId = proposalId;
            this.officeId = officeId;
        }

        public boolean equals(Object o) {
            if (o != null && o instanceof Id) {
                Id that = (Id) o;
                return this.proposalId.equals(that.proposalId)
                        && this.officeId.equals(that.officeId);
            }
            return false;
        }

        public int hashCode() {
            return proposalId.hashCode() + officeId.hashCode();
        }

        @Override
        public String toString() {
            final StringBuffer sb = new StringBuffer("Id{");
            sb.append("proposalId=").append(proposalId);
            sb.append(", officeId=").append(officeId);
            sb.append('}');
            return sb.toString();
        }
    }

}
