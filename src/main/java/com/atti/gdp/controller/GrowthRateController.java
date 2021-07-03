package com.atti.gdp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atti.gdp.dto.GrowthRateDto;
import com.atti.gdp.service.CountryGdpService;

@RestController
@RequestMapping("/growthrate")
public class GrowthRateController {

	@Autowired
	private CountryGdpService countryGdpService;

	@GetMapping
	public GrowthRateDto getGrowthRateByCountryCdAndYear(@RequestParam String countrycd, @RequestParam int year)
			throws Exception {

		return countryGdpService.getGrowthRateByCountryAndYear(countrycd, year);

	}

}
