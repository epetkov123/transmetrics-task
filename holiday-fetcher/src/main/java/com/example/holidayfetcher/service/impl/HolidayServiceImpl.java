package com.example.holidayfetcher.service.impl;

import com.example.holidayfetcher.model.Holiday;
import com.example.holidayfetcher.repository.HolidayRepository;
import com.example.holidayfetcher.service.HolidayService;
import com.example.holidayfetcher.utils.Helpers;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;

@Service
public class HolidayServiceImpl implements HolidayService {

    private final HolidayRepository holidayRepository;

    public HolidayServiceImpl(HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }

    @Value("${holidaysURL}")
    private String URL;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void getAllHolidays() {
        List<Holiday> holidayList = new ArrayList<Holiday>();
        holidayList = holidayRepository.findAll();

        Helpers.saveToJson(holidayList);
        Helpers.saveToCsv(holidayList);
    }

    @Override
    public void addAllHolidays() throws JSONException {
        String jsonResult = restTemplate.getForObject(URL, String.class);

        //Convert the Json string into a JSONObject to parse the needed input.
        JSONObject obj = new JSONObject(jsonResult);
        JSONArray arr = obj.getJSONArray("holidays");

        List<Holiday> holidayList = new ArrayList<Holiday>();

        for (int i = 0; i < arr.length(); i++) {
            //Get the json values of keys name, oneliner and iso.
            String name = arr.getJSONObject(i).getString("name");
            String description = arr.getJSONObject(i).getString("oneliner");
            String date = arr.getJSONObject(i).getJSONObject("date").getString("iso");

            Holiday holiday = new Holiday();

            holiday.setName(name);
            holiday.setDescription(description);
            holiday.setDate(date);

            holidayList.add(holiday);
        }
        holidayRepository.saveAll(holidayList);
    }
}