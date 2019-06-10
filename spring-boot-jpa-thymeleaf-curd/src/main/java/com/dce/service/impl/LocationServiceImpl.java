package com.dce.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dce.model.Location;
import com.dce.repository.LocationRepository;
import com.dce.service.LocationService;

@Service
public class LocationServiceImpl implements LocationService{
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Override
	public void save(Location location) {
		locationRepository.save(location);
	}

}
