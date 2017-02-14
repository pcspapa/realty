/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.entity;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.util.Date;

/**
 * Created by cspark on 2017. 2. 14..
 */
public class UpdatedDateEntityListener {

    @PreUpdate
    @PrePersist
    public void prePersistAndUpdate(HasUpdatedDate hud) {
        hud.setUpdatedDate(new Date());
    }

}
