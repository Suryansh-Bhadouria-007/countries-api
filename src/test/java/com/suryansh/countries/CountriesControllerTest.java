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
 * date: 08/10/22
 **************************************************************************/

package com.suryansh.countries;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

@WebMvcTest(CountriesController.class)

public class CountriesControllerTest {
    @Autowired
    MockMvc mockMvc;
    @MockBean
    RestCountriesService restCountriesService;
    @MockBean
    RestTemplate restTemplate;
    private static final String COUNTRY_DETAILS = "[{'name':{'common':'France','official':'French Republic','nativeName':{'fra':{'official':'République française','common':'France'}}},'tld':['.fr'],'cca2':'FR','ccn3':'250','cca3':'FRA','cioc':'FRA','independent':true,'status':'officially-assigned','unMember':true,'currencies':{'EUR':{'name':'Euro','symbol':'€'}},'idd':{'root':'+3','suffixes':['3']},'capital':['Paris'],'altSpellings':['FR','French Republic','République française'],'region':'Europe','subregion':'Western Europe','languages':{'fra':'French'},'translations':{'ara':{'official':'الجمهورية الفرنسية','common':'فرنسا'},'bre':{'official':'Republik Frañs','common':'Frañs'},'ces':{'official':'Francouzská republika','common':'Francie'},'cym':{'official':'French Republic','common':'France'},'deu':{'official':'Französische Republik','common':'Frankreich'},'est':{'official':'Prantsuse Vabariik','common':'Prantsusmaa'},'fin':{'official':'Ranskan tasavalta','common':'Ranska'},'fra':{'official':'République française','common':'France'},'hrv':{'official':'Francuska Republika','common':'Francuska'},'hun':{'official':'Francia Köztársaság','common':'Franciaország'},'ita':{'official':'Repubblica francese','common':'Francia'},'jpn':{'official':'フランス共和国','common':'フランス'},'kor':{'official':'프랑스 공화국','common':'프랑스'},'nld':{'official':'Franse Republiek','common':'Frankrijk'},'per':{'official':'جمهوری فرانسه','common':'فرانسه'},'pol':{'official':'Republika Francuska','common':'Francja'},'por':{'official':'República Francesa','common':'França'},'rus':{'official':'Французская Республика','common':'Франция'},'slk':{'official':'Francúzska republika','common':'Francúzsko'},'spa':{'official':'República francés','common':'Francia'},'swe':{'official':'Republiken Frankrike','common':'Frankrike'},'tur':{'official':'Fransa Cumhuriyeti','common':'Fransa'},'urd':{'official':'جمہوریہ فرانس','common':'فرانس'},'zho':{'official':'法兰西共和国','common':'法国'}},'latlng':[46.0,2.0],'landlocked':false,'borders':['AND','BEL','DEU','ITA','LUX','MCO','ESP','CHE'],'area':551695.0,'demonyms':{'eng':{'f':'French','m':'French'},'fra':{'f':'Française','m':'Français'}},'flag':'\\uD83C\\uDDEB\\uD83C\\uDDF7','maps':{'googleMaps':'https://goo.gl/maps/g7QxxSFsWyTPKuzd7','openStreetMaps':'https://www.openstreetmap.org/relation/1403916'},'population':67391582,'gini':{'2018':32.4},'fifa':'FRA','car':{'signs':['F'],'side':'right'},'timezones':['UTC-10:00','UTC-09:30','UTC-09:00','UTC-08:00','UTC-04:00','UTC-03:00','UTC+01:00','UTC+02:00','UTC+03:00','UTC+04:00','UTC+05:00','UTC+10:00','UTC+11:00','UTC+12:00'],'continents':['Europe'],'flags':{'png':'https://flagcdn.com/w320/fr.png','svg':'https://flagcdn.com/fr.svg'},'coatOfArms':{'png':'https://mainfacts.com/media/images/coats_of_arms/fr.png','svg':'https://mainfacts.com/media/images/coats_of_arms/fr.svg'},'startOfWeek':'monday','capitalInfo':{'latlng':[48.87,2.33]},'postalCode':{'format':'#####','regex':'^(\\\\d{5})$'}}]";
    private static final String ALL_COUNTRIES_DETAILS = "[{'name':{'common':'India','official':'Republic of India','nativeName':{'eng':{'official':'Republic of India','common':'India'},'hin':{'official':'भारत गणराज्य','common':'भारत'},'tam':{'official':'இந்தியக் குடியரசு','common':'இந்தியா'}}},'tld':['.in'],'cca2':'IN','ccn3':'356','cca3':'IND','cioc':'IND','independent':true,'status':'officially-assigned','unMember':true,'currencies':{'INR':{'name':'Indian rupee','symbol':'₹'}},'idd':{'root':'+9','suffixes':['1']},'capital':['New Delhi'],'altSpellings':['IN','Bhārat','Republic of India','Bharat Ganrajya','இந்தியா'],'region':'Asia','subregion':'Southern Asia','languages':{'eng':'English','hin':'Hindi','tam':'Tamil'},'translations':{'ara':{'official':'جمهورية الهند','common':'الهند'},'bre':{'official':'Republik India','common':'India'},'ces':{'official':'Indická republika','common':'Indie'},'cym':{'official':'Republic of India','common':'India'},'deu':{'official':'Republik Indien','common':'Indien'},'est':{'official':'India Vabariik','common':'India'},'fin':{'official':'Intian tasavalta','common':'Intia'},'fra':{'official':'République de l-Inde','common':'Inde'},'hrv':{'official':'Republika Indija','common':'Indija'},'hun':{'official':'Indiai Köztársaság','common':'India'},'ita':{'official':'Repubblica dell-India','common':'India'},'jpn':{'official':'インド共和国','common':'インド'},'kor':{'official':'인도 공화국','common':'인도'},'nld':{'official':'Republiek India','common':'India'},'per':{'official':'جمهوری هندوستان','common':'هند'},'pol':{'official':'Republika Indii','common':'Indie'},'por':{'official':'República da Índia','common':'Índia'},'rus':{'official':'Республика Индия','common':'Индия'},'slk':{'official':'Indická republika','common':'India'},'spa':{'official':'República de la India','common':'India'},'swe':{'official':'Republiken Indien','common':'Indien'},'tur':{'official':'Hindistan Cumhuriyeti','common':'Hindistan'},'urd':{'official':'جمہوریہ بھارت','common':'بھارت'},'zho':{'official':'印度共和国','common':'印度'}},'latlng':[20,77],'landlocked':false,'borders':['BGD','BTN','MMR','CHN','NPL','PAK'],'area':3287590,'demonyms':{'eng':{'f':'Indian','m':'Indian'},'fra':{'f':'Indienne','m':'Indien'}},'flag':'\uD83C\uDDEE\uD83C\uDDF3','maps':{'googleMaps':'https://goo.gl/maps/WSk3fLwG4vtPQetp7','openStreetMaps':'https://www.openstreetmap.org/relation/304716'},'population':1380004385,'gini':{'2011':35.7},'fifa':'IND','car':{'signs':['IND'],'side':'left'},'timezones':['UTC+05:30'],'continents':['Asia'],'flags':{'png':'https://flagcdn.com/w320/in.png','svg':'https://flagcdn.com/in.svg'},'coatOfArms':{'png':'https://mainfacts.com/media/images/coats_of_arms/in.png','svg':'https://mainfacts.com/media/images/coats_of_arms/in.svg'},'startOfWeek':'monday','capitalInfo':{'latlng':[28.6,77.2]},'postalCode':{'format':'######','regex':'^(\\\\d{6})$'}},{'name':{'common':'British Indian Ocean Territory','official':'British Indian Ocean Territory','nativeName':{'eng':{'official':'British Indian Ocean Territory','common':'British Indian Ocean Territory'}}},'tld':['.io'],'cca2':'IO','ccn3':'086','cca3':'IOT','independent':false,'status':'officially-assigned','unMember':false,'currencies':{'USD':{'name':'United States dollar','symbol':'$'}},'idd':{'root':'+2','suffixes':['46']},'capital':['Diego Garcia'],'altSpellings':['IO'],'region':'Africa','subregion':'Eastern Africa','languages':{'eng':'English'},'translations':{'ara':{'official':'إقليم المحيط الهندي البريطاني','common':'إقليم المحيط الهندي البريطاني'},'bre':{'official':'Tiriad breizhveurat Meurvor Indez','common':'Tiriad breizhveurat Meurvor Indez'},'ces':{'official':'Britské indickooceánské území','common':'Britské indickooceánské území'},'cym':{'official':'Tiriogaeth Brydeinig Cefnfor India','common':'Tiriogaeth Brydeinig Cefnfor India'},'deu':{'official':'Britisches Territorium im Indischen Ozean','common':'Britisches Territorium im Indischen Ozean'},'est':{'official':'Briti India ookeani ala','common':'Briti India ookeani ala'},'fin':{'official':'Brittiläinen Intian valtameren alue','common':'Brittiläinen Intian valtameren alue'},'fra':{'official':'Territoire britannique de l- océan Indien','common':'Territoire britannique de l-océan Indien'},'hrv':{'official':'British Indian Ocean Territory','common':'Britanski Indijskooceanski teritorij'},'hun':{'official':'Brit Indiai-óceáni Terület','common':'Brit Indiai-óceáni Terület'},'ita':{'official':'Territorio britannico dell-Oceano Indiano','common':'Territorio britannico dell-oceano indiano'},'jpn':{'official':'イギリス領インド洋地域','common':'イギリス領インド洋地域'},'kor':{'official':'인도 공화국','common':'인도'},'nld':{'official':'Brits Indische Oceaan Territorium','common':'Britse Gebieden in de Indische Oceaan'},'per':{'official':'قلمرو بریتانیا در اقیانوس هند','common':'قلمرو بریتانیا در اقیانوس هند'},'pol':{'official':'Brytyjskie Terytorium Oceanu Indyjskiego','common':'Brytyjskie Terytorium Oceanu Indyjskiego'},'por':{'official':'British Indian Ocean Territory','common':'Território Britânico do Oceano Índico'},'rus':{'official':'Британская территория Индийского океана','common':'Британская территория в Индийском океане'},'slk':{'official':'Britské indickooceánske územie','common':'Britské indickooceánske územie'},'spa':{'official':'Territorio Británico del Océano Índico','common':'Territorio Británico del Océano Índico'},'swe':{'official':'Brittiska territoriet i Indiska Oceanen','common':'Brittiska territoriet i Indiska Oceanen'},'tur':{'official':'Britanya Hint Okyanusu Toprakları','common':'Britanya Hint Okyanusu Toprakları'},'urd':{'official':'برطانوی بحرہند خطہ','common':'برطانوی بحرہند خطہ'},'zho':{'official':'英属印度洋领地','common':'英属印度洋领地'}},'latlng':[-6,71.5],'landlocked':false,'area':60,'demonyms':{'eng':{'f':'Indian','m':'Indian'}},'flag':'\uD83C\uDDEE\uD83C\uDDF4','maps':{'googleMaps':'https://goo.gl/maps/bheNucgekVEYozoi6','openStreetMaps':'https://www.openstreetmap.org/relation/1993867'},'population':3000,'car':{'signs':['GB'],'side':'right'},'timezones':['UTC+06:00'],'continents':['Asia'],'flags':{'png':'https://flagcdn.com/w320/io.png','svg':'https://flagcdn.com/io.svg'},'coatOfArms':{},'startOfWeek':'monday','capitalInfo':{'latlng':[-7.3,72.4]}}]";
    private static final String validCountry = "France";
    private static final String invalidCountry = "XX";

    @Test
    public void getCountyCapitalSuccess() throws Exception {
        Mockito.when(restCountriesService.getCapitalByCountryName(validCountry)).thenReturn(Optional.ofNullable(COUNTRY_DETAILS));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/capital?country=" + validCountry)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(content().string("Paris"));
    }

    @Test
    public void getCountyCapitalFailure() throws Exception {
        Mockito.when(restCountriesService.getCapitalByCountryName(validCountry)).thenReturn(Optional.ofNullable(null));
        mockMvc.perform(MockMvcRequestBuilders
                .get("/capital?country=" + invalidCountry)
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }

    @Test
    public void getAllCountriesDetailsSuccess() throws Exception {
        Mockito.when(restCountriesService.getAllCountriesWithDetails()).thenReturn(Optional.ofNullable(ALL_COUNTRIES_DETAILS));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/countries")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(2)))
            .andExpect(jsonPath("$[0].name.common", is("India")));
    }

    @Test
    public void getAllCountriesDetailsFailure() throws Exception {
        Mockito.when(restCountriesService.getAllCountriesWithDetails()).thenReturn(Optional.ofNullable(null));

        mockMvc.perform(MockMvcRequestBuilders
                .get("/countries")
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isNotFound());
    }
}