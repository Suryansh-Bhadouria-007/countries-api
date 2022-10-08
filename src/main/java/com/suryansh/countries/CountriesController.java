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

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CountriesController {

    private static final String CAPITAL_KEY = "capital";
    @Autowired
    RestCountriesService restCountriesService;

    @GetMapping(value = "/countries", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<String> getAllCountriesDetails() {
        Optional<String> allCountriesOpt = restCountriesService.getAllCountriesWithDetails();
        return allCountriesOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/capital")
    public @ResponseBody ResponseEntity<String> getCapital(@RequestParam String country) {
        Optional<String> countryDetails = restCountriesService.getCapitalByCountryName(country);
        if (countryDetails.isPresent()) {
            JsonArray countryList = new Gson().fromJson(countryDetails.get(), JsonArray.class);
            return ResponseEntity.ok(countryList.get(0).getAsJsonObject().get(CAPITAL_KEY).getAsString());
        }
        return ResponseEntity.notFound().build();
    }
}