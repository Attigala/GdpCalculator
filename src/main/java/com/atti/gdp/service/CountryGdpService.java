package com.atti.gdp.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atti.gdp.dto.GrowthRateDto;
import com.atti.gdp.model.CountryGdp;
import com.atti.gdp.repository.CountryCodeRepository;
import com.atti.gdp.repository.CountryGdpRepository;

@Service
public class CountryGdpService {
	
	@Autowired
	private CountryGdpRepository countryGdpRepository;
	
	@Autowired
	private CountryCodeRepository countryCodeRepository;
	
	public CountryGdpService(CountryGdpRepository aCountryGdpRepository, CountryCodeRepository aCountryCodeRepository) {
		this.countryGdpRepository = aCountryGdpRepository;
		this.countryCodeRepository = aCountryCodeRepository;
	}
	
	//Calculates the growth rate of the country and the year specified by the user
	public GrowthRateDto getGrowthRateByCountryAndYear(String aCountryCd, int aYear) throws Exception {
		if(!validateYear(aYear)) {
			throw new Exception("Unsupported Year");
		}
		//If the user sends the Alpha 2 code get the corresponding alpha 3 code to query
		if(aCountryCd.length() != 3) {
			aCountryCd = getAlpha3CountryCode(aCountryCd);
		}
		List<CountryGdp> countryGdpList = new ArrayList<> ();
		countryGdpList.add(countryGdpRepository.findByCountryCodeAndYear(aCountryCd, aYear)); 
		//previous year's result
		countryGdpList.add(countryGdpRepository.findByCountryCodeAndYear(aCountryCd, aYear-1)); 
		GrowthRateDto growthRate = new GrowthRateDto();
		growthRate.setCountryCd(aCountryCd);
		growthRate.setYear(Integer.valueOf(aYear));
		growthRate.setGrowthRate(Double.valueOf(growthRate(countryGdpList)));
		return growthRate;
	}
	//Assuming growth rate is calculated using (currentgdp - previousgdp)/previousgdp * 100
	private double growthRate(List <CountryGdp>countryGdpList) {
		return  (countryGdpList.get(0).getValue() - countryGdpList.get(1).getValue()) / countryGdpList.get(1).getValue() * 100;
	}
	
	private String getAlpha3CountryCode(String aCountryCode) {
		return countryCodeRepository.getByAlpha2Code(aCountryCode).getAlpha3Code();
	}
	
	private boolean validateYear(int year) {
		if (!(year>2007)|| !(year<2016)) {
			return false;
		}
		return  true;
	}
	

}
