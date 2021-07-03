package com.atti.gdp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.atti.gdp.model.CountryCode;
import com.atti.gdp.model.CountryGdp;

public interface CountryGdpRepository extends JpaRepository<CountryGdp, Integer> {
	
	CountryGdp findByCountryCodeAndYear(String aCountryCode, int aYear);

}
