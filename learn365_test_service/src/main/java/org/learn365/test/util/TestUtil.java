package org.learn365.test.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.ThreadLocalRandom;

import static org.learn365.test.util.TestConstants.DATE_FORMAT;
import static org.learn365.test.util.TestConstants.UTC;

public class TestUtil {

    public static String generateRandomId() {
        return String.valueOf(Math.abs(ThreadLocalRandom.current().nextInt()));
    }

    public static Date formatDate(String dateLimit) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        dateFormat.setTimeZone(TimeZone.getTimeZone(UTC));
        return dateFormat.parse(dateLimit);
    }
}
