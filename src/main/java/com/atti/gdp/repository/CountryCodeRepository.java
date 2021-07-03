package com.atti.gdp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atti.gdp.model.CountryCode;

public interface CountryCodeRepository extends JpaRepository<CountryCode, Integer>{

	public CountryCode getByAlpha2Code(String aAlpha2Code);
}
