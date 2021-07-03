package com.atti.gdp.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import com.atti.gdp.dto.GrowthRateDto;
import com.atti.gdp.model.CountryGdp;
import com.atti.gdp.repository.CountryGdpRepository;

class CountryGdpServiceTst {

	private static final String COUNTRY_CODE = "AFG";
	
	private static CountryGdpRepository countryGdpRepository = Mockito.mock(CountryGdpRepository.class);
	
	
	@Test
	void test_returns_growth_rate() {
		Mockito.when(countryGdpRepository.getGdpByCountryCodeAndYear(COUNTRY_CODE, 2010)).thenReturn(createDummyGdp(2010));
		Mockito.when(countryGdpRepository.getGdpByCountryCodeAndYear(COUNTRY_CODE, 2009)).thenReturn(createDummyGdp(2009));
		
		CountryGdpService gdpService = new CountryGdpService(countryGdpRepository);
		
		GrowthRateDto growthDto = gdpService.getGrowthRateByCountryAndYear(COUNTRY_CODE, 2010);
		
		Double expected = Double.valueOf(((751111191.1-546666677.8)/546666677.8) * 100);
		double exp  = ((2010-2009)/2009) * 100;
		
		assertEquals(expected.doubleValue(), growthDto.getGrowthRate().doubleValue(), 0.01);
		
	}
	
	private CountryGdp createDummyGdp(int year) {
		CountryGdp countryGdp = new CountryGdp();
		countryGdp.setCountryCode(COUNTRY_CODE);
		if(year == 2010) {
			countryGdp.setValue(751111191.1);
		}
		else {
			countryGdp.setValue(546666677.8);
		}
		countryGdp.setCountryName("Afghanistan");
		countryGdp.setYear(year);
		
		
		return countryGdp;
	}

}
