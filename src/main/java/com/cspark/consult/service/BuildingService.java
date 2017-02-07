/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan. 
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna. 
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus. 
 * Vestibulum commodo. Ut rhoncus gravida arcu. 
 */

package com.cspark.consult.service;

import com.cspark.consult.entity.Building;

import javax.transaction.Transactional;

/**
 * Created by cspark on 2017. 2. 7..
 */
public interface BuildingService {

    @Transactional
    Building build(Building building);

    @Transactional
    Building addContact(Long buildingId, Long contactId, String director);
}
