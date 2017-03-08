/*
 * Copyright (c) 2017. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.cspark.consult.service;

import com.cspark.consult.entity.realty.Building;
import com.cspark.consult.entity.realty.BuildingContact;
import com.cspark.consult.entity.realty.Contact;
import com.cspark.consult.repository.BuildingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by cspark on 2017. 2. 7..
 */
@Service
public class BuildingServiceImpl implements BuildingService {

    @Autowired
    private BuildingRepository buildingRepository;

    @Override
    public Building build(Building building) {
        return buildingRepository.save(building);
    }

    @Override
    public Building addContact(long buildingId, long contactId, String director) {
        Building building = buildingRepository.findOne(buildingId);
        building.addBuildingContact(new BuildingContact(new Contact(contactId), director));

        return building;
    }

    @Override
    public Building findOne(long buildingId) {
        return buildingRepository.findOne(buildingId);
    }

    @Override
    public List<Building> findAll() {
        return buildingRepository.findAll();
    }

    @Override
    public Page<Building> findAll(Pageable pageable) {
        return buildingRepository.findAll(pageable);
    }

    @Override
    public Building rebuild(Building building) {
        return buildingRepository.save(building);
    }

}
