package com.wisdomleaf.speaking_clock.service;

import com.wisdomleaf.speaking_clock.exception.InvalidTimeException;
import org.springframework.stereotype.Service;

@Service
public class TimeService {

    private static final String[] HOURS = {
            "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
            "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen", "twenty"
    };

    private static final String[] MINUTES = {
            "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
            "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    };


    public String convertTimeToWords(String time) throws InvalidTimeException {
        String[] timeParts = time.split(":");

        if (timeParts.length != 2) {
            throw new InvalidTimeException("Invalid time format");
        }

        int hours = Integer.parseInt(timeParts[0]);
        int minutes = Integer.parseInt(timeParts[1]);

        if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
            throw new InvalidTimeException("Invalid time format");
        }

        String hourInWords = convertHourToWords(hours);
        String minuteInWords = convertMinuteToWords(minutes);

        return "It's " + hourInWords + (minutes > 0 ? " " + minuteInWords : "");
    }

    public String convertToMiddayMidnight(String time) throws InvalidTimeException {
        String[] timeParts = time.split(":");

        if (timeParts.length != 2) {
            throw new InvalidTimeException("Invalid time format");
        }

        int hours = Integer.parseInt(timeParts[0]);
        int minutes = Integer.parseInt(timeParts[1]);

        if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) {
            throw new InvalidTimeException("Invalid time format");
        }

        if (hours == 12 && minutes == 0) {
            return "It's Midday";
        } else if ((hours == 0 || hours == 24) && minutes == 0) {
            return "It's Midnight";
        } else {
            throw new InvalidTimeException("Input does not represent Midday or Midnight");
        }
    }

    private String convertHourToWords(int hours) {
        if (hours == 0) {
            return "12 AM - midnight";
        } else if (hours == 12) {
            return "12 PM - noon";
        } else {
            return HOURS[hours % 12];
        }
    }

    private String convertMinuteToWords(int minutes) {
        String ans = "";
        if (minutes == 0) {
            return "";
        } else if (minutes <= 20) {
            return MINUTES[minutes];
        } else {
            int tens = minutes / 10;
            int ones = minutes % 10;
            switch (tens) {
                case 2:
                    ans = "twenty" + (ones > 0 ? " " + MINUTES[ones] : "");
                    break;
                case 3:
                    ans = "thirty" + (ones > 0 ? " " + MINUTES[ones] : "");
                    break;
                case 4:
                    ans = "forty" + (ones > 0 ? " " + MINUTES[ones] : "");
                    break;
                case 5:
                    ans = "fifty" + (ones > 0 ? " " + MINUTES[ones] : "");
                    break;
            }
        }
        return ans;
    }
}

