package com.wisdomleaf.speaking_clock.controller;

import com.wisdomleaf.speaking_clock.exception.InvalidTimeException;
import com.wisdomleaf.speaking_clock.service.TimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/time")
public class TimeController {

    @Autowired
    private TimeService timeService;

    @GetMapping("/convert")
    public ResponseEntity<String> convertTime(@RequestParam String time) {
        try {
            String result = timeService.convertTimeToWords(time);
            return ResponseEntity.ok(result);
        } catch (InvalidTimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }


    @GetMapping("/getDayOrNight")
    public ResponseEntity<String> convertMiddayMidnight(@RequestParam String time) {
        try {
            String result = timeService.convertToMiddayMidnight(time);
            return ResponseEntity.ok(result);
        } catch (InvalidTimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }
}


