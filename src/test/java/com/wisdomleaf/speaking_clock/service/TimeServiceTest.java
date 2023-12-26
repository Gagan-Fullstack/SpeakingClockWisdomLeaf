package com.wisdomleaf.speaking_clock.service;


import com.wisdomleaf.speaking_clock.exception.InvalidTimeException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class TimeServiceTest {

    @InjectMocks
    private TimeService timeService;

    @Mock
    private InvalidTimeException mockException;

    @Test
    void testConvertTimeToWordsValidTimeReturnsWords() throws InvalidTimeException {

        String time = "14:30";
        String expectedOutput = "It's two thirty";

        String result = timeService.convertTimeToWords(time);

        assertEquals(expectedOutput, result);
    }

    @Test
    void testConvertTimeToWordsInvalidTimeFormatException() {

        String invalidTime = "invalidFormat";


        assertThrows(InvalidTimeException.class, () -> {
            timeService.convertTimeToWords(invalidTime);
        });
    }

    @Test
    void testConvertToMiddayMidnightMiddayReturnsMidday() throws InvalidTimeException {
        String middayTime = "12:00";


        String result = timeService.convertToMiddayMidnight(middayTime);

        assertEquals("It's Midday", result);
    }

    @Test
    void testConvertToMiddayMidnightMidnightReturnsMidnight() throws InvalidTimeException {

        String midnightTime = "00:00";

        String result = timeService.convertToMiddayMidnight(midnightTime);

        assertEquals("It's Midnight", result);
    }

    @Test
    void testConvertToMiddayMidnightException() {
        String otherTime = "08:30";

        assertThrows(InvalidTimeException.class, () -> {
            timeService.convertToMiddayMidnight(otherTime);
        });
    }

}
