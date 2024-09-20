import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * //In the U.S., daylight saving time starts on the second Sunday in March
 * and ends on the first Sunday in November, with the time changes taking place at 2:00 a.m. local time.
 */
public class LokTimeZoneSvc {
    Map<String, ZoneId> zonesWithDayLight = new HashMap<>();

    LokTimeZoneSvc(){
        // All the java timezones where cities follow the day light saving
        zonesWithDayLight.put("-4", ZoneId.of("America/Puerto_Rico") ); // PR does not follow day light saving
        zonesWithDayLight.put("-5", ZoneId.of("America/New_York") );
        zonesWithDayLight.put("-6", ZoneId.of("America/Chicago") );
        zonesWithDayLight.put("-7", ZoneId.of("America/Denver") );
        zonesWithDayLight.put("-8", ZoneId.of("America/Los_Angeles") );
        zonesWithDayLight.put("-9", ZoneId.of("America/Anchorage") );
    }

    public boolean isDstActive(String localDateTimeStr, String offset) {
        if( !zonesWithDayLight.containsKey(offset) ){
            return false;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"); // Adjust pattern as needed
        // Parse the Date string into a LocalDateTime object
        LocalDateTime localDateTime = LocalDateTime.parse(localDateTimeStr, formatter);

        // Parse the date string into a ZonedDateTime object
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime,zonesWithDayLight.get(offset));
        boolean isDSTActive = zonedDateTime.getZone().getRules().isDaylightSavings(zonedDateTime.toInstant());

        // Access the zoned date and time
        System.out.println("DST: " + isDSTActive);
        System.out.println("ZonedDateTime: " + zonedDateTime);
        System.out.println("Date: " + zonedDateTime.toLocalDate());
        System.out.println("Time: " + zonedDateTime.toLocalTime());
        System.out.println("Zone ID: " + zonedDateTime.getZone() + "\n");

        return isDSTActive;
    }

    public static void main(String[] args) {
        LokTimeZoneSvc lokTimeZoneSvc = new LokTimeZoneSvc();
        lokTimeZoneSvc.isDstActive("2024-11-03T01:59:59", "-4");
        lokTimeZoneSvc.isDstActive("2024-11-03T02:00:00", "-4");
    }
}
