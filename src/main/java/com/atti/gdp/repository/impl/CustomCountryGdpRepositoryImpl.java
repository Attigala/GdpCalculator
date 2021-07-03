package com.atti.gdp.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.atti.gdp.model.CountryGdp;
import com.atti.gdp.repository.custom.CustomCountryGdpRepository;

public class CustomCountryGdpRepositoryImpl implements CustomCountryGdpRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public CountryGdp getGdpByCountryCodeAndYear(String aCountryCd, int aYear) {
		System.out.println("here");
		String queryString = "SELECT * FROM country_gdp WHERE country_code = ? AND year = ? ORDER BY year";
		try {
			System.out.println("here");
			TypedQuery<CountryGdp> query = entityManager.createQuery(queryString, CountryGdp.class);
			query.setParameter(1, aCountryCd);
			query.setParameter(2, aYear);
			
			System.out.println(query.toString());
			
			return query.getSingleResult();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
