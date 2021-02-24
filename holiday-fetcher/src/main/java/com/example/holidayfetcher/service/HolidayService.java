package com.example.holidayfetcher.service;

import org.json.JSONException;

public interface HolidayService {
    void getAllHolidays();
    void addAllHolidays() throws JSONException;
}