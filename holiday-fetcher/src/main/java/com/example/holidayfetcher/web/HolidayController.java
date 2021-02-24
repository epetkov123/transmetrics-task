package com.example.holidayfetcher.web;

import com.example.holidayfetcher.service.HolidayService;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(path="/holidays")
public class HolidayController {
    private final HolidayService holidayService;

    public HolidayController(HolidayService holidayService) {
        this.holidayService = holidayService;
    }

    @GetMapping(path="/download")
    @ResponseStatus
    public ResponseEntity addAllHolidays () throws JSONException {
        holidayService.addAllHolidays();
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping(path="/export")
    @ResponseStatus
    public ResponseEntity getAllHolidays () {
        holidayService.getAllHolidays();
        return new ResponseEntity(HttpStatus.CREATED);
    }
}