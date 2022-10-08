/* ************************************************************************
 * ADOBE CONFIDENTIAL
 * ___________________
 *
 *  Copyright 2020 Adobe Systems Incorporated
 *  All Rights Reserved.
 *
 * NOTICE:  All information contained herein is, and remains
 * the property of Adobe Systems Incorporated and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Adobe Systems Incorporated and its
 * suppliers and are protected by all applicable intellectual property
 * laws, including trade secret and copyright laws.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Adobe Systems Incorporated.
 *
 * author: suryansh
 * date: 07/10/22
 **************************************************************************/

package com.suryansh.countries;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestCountriesService {
    private static final Logger log = LoggerFactory.getLogger(RestCountriesService.class);
    public static final String FETCH_COUNTRY_DETAILS_URL = "https://restcountries.com/v3.1/name";
    public static final String FETCH_ALL_COUNTRIES_URL = "https://restcountries.com/v3.1/all";
    public static final String URL_DELIMITER = "/";

    @Autowired
    RestTemplate restTemplate;

    public Optional<String> getAllCountriesWithDetails() {
        String allCountriesDetails = restTemplate.getForObject(FETCH_ALL_COUNTRIES_URL, String.class);
        log.info("Response from {}: {}", FETCH_ALL_COUNTRIES_URL, allCountriesDetails);
        return Optional.ofNullable(allCountriesDetails);
    }

    public Optional<String> getCapitalByCountryName(String countryName) {
        String countryDetails = restTemplate.getForObject(FETCH_COUNTRY_DETAILS_URL + URL_DELIMITER + countryName + "?fullText=true", String.class);
        log.info("Response from {}: {}", FETCH_COUNTRY_DETAILS_URL, countryDetails);
        return Optional.ofNullable(countryDetails);
    }

}
