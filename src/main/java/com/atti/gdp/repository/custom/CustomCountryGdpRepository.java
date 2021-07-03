package com.atti.gdp.repository.custom;

import java.util.List;

import com.atti.gdp.model.CountryGdp;

public interface CustomCountryGdpRepository {
	public CountryGdp getGdpByCountryCodeAndYear(String aCountryCd, int aYear);
}
