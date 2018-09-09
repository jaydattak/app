package com.example.app.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BaseService {

	@Autowired
	protected ModelMapper mapper;

	public ModelMapper getMapper() {
		return mapper;
	}

	public void setMapper(ModelMapper mapper) {
		this.mapper = mapper;
	}

}
