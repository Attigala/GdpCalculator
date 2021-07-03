package com.atti.gdp;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.atti.gdp.model.CountryCode;
import com.atti.gdp.model.CountryGdp;
import com.atti.gdp.repository.CountryCodeRepository;
import com.atti.gdp.repository.CountryGdpRepository;

@SpringBootApplication
public class GdpApplication {

	@Autowired
	private CountryGdpRepository gdpRepository;

	@Autowired
	private CountryCodeRepository countryCodeRepository;

	public static void main(String[] args) {
		SpringApplication.run(GdpApplication.class, args);
	}

	@PostConstruct
	public void loadData() {
		ClassLoader classLoader = getClass().getClassLoader();
		InputStream is = null;
		// load dataset if data not exist in database
		List<CountryGdp> records = (List<CountryGdp>) gdpRepository.findAll();

		if (records.isEmpty()) {

			is = classLoader.getResourceAsStream("country_gdp.csv");
			try {
				gdpRepository.saveAll(CsvUtil.read(CountryGdp.class, is));
				System.out.println(is);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (countryCodeRepository.count() == 0) {
			is = classLoader.getResourceAsStream("country_code.csv");
			try {
				countryCodeRepository.saveAll(CsvUtil.read(CountryCode.class, is));
				System.out.println(is);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
