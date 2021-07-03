package com.atti.gdp.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atti.gdp.dto.GrowthRateDto;
import com.atti.gdp.model.CountryGdp;
import com.atti.gdp.repository.CountryGdpRepository;

@Service
public class CountryGdpService {
	
	@Autowired
	private CountryGdpRepository countryGdpRepository;
	
	public CountryGdpService(CountryGdpRepository aCountryGdpRepository) {
		this.countryGdpRepository = aCountryGdpRepository;
	}
	
	public GrowthRateDto getGrowthRateByCountryAndYear(String aCountryCd, int aYear) {
		List<CountryGdp> countryGdpList = new ArrayList<>();
		countryGdpList.add(countryGdpRepository.getGdpByCountryCodeAndYear(aCountryCd, aYear));
		countryGdpList.add(countryGdpRepository.getGdpByCountryCodeAndYear(aCountryCd, aYear-1));
		System.out.println(countryGdpList.get(0).getYear());
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
	
	

}
