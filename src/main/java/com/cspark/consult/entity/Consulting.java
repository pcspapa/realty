/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by cspark on 2017. 2. 13..
 */
@Entity
public class Consulting {

    @EmbeddedId
    private Id id;

    @ManyToOne
    @JoinColumn(name = "PROPOSAL_ID", foreignKey = @ForeignKey(name = "FK_PROPOSAL_ID"), insertable = false, updatable = false)
    private Proposal proposal;

    @ManyToOne
    @JoinColumn(name = "OFFICE_ID", foreignKey = @ForeignKey(name = "FK_OFFICE_ID"), insertable = false, updatable = false)
    private Office office;

    public Consulting(Proposal proposal, Office office) {
        this.proposal = proposal;
        this.office = office;
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
    }

}
