/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit. 
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan. 
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna. 
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus. 
 * Vestibulum commodo. Ut rhoncus gravida arcu. 
 */

package com.cspark.consult.service;

import com.cspark.consult.entity.realty.Building;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by cspark on 2017. 2. 7..
 */
@Transactional(readOnly = true)
public interface BuildingService {

    @Transactional
    Building build(Building building);

    @Transactional
    Building addContact(long buildingId, long contactId, String director);

    Building findOne(long id);

    List<Building> findAll();

    Page<Building> findAll(Pageable pageable);

    @Transactional
    Building rebuild(Building building);
}
