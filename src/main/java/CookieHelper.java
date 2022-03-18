import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class CookieHelper {

    private static SimpleDateFormat date_formatter = new SimpleDateFormat("yyyy-MM-dd");

    public static Set<String> getMostActiveCookie(String logfile, String opt_dateString)
            throws FileNotFoundException, ParseException {
        int largestCount = 0;
        Map<String, Integer> counts = new HashMap<>();
        for (String line : getLogLines(logfile)) {
            String[] cols = line.split(",");
            String cookie = cols[0];
            Date date = date_formatter.parse(cols[1].split("T")[0]); // only concerned with date
            if (!isSameDay(date_formatter.parse(opt_dateString), date)) continue;
            if (!counts.containsKey(cookie)) counts.put(cookie, 0);
            int count = counts.get(cookie) + 1;
            if (count > largestCount) largestCount = count;
            counts.put(cookie, count);
        }

        final int finalLargestCount = largestCount;
        return counts.keySet()
                .stream()
                .filter(c -> counts.get(c) == finalLargestCount)
                .collect(Collectors.toSet());

    }

    private static List<String> getLogLines(String logfile) throws FileNotFoundException {
        List<String> out = new ArrayList<>();
        File cookiesFile = new File(logfile);
        Scanner reader = new Scanner(cookiesFile);
        reader.nextLine(); // skip column name line
        while (reader.hasNextLine()) {
            out.add(reader.nextLine());
        }
        reader.close();
        return out;
    }

    private static boolean isSameDay(Date d1, Date d2) {
        Instant instant1 = d1.toInstant()
                .truncatedTo(ChronoUnit.DAYS);
        Instant instant2 = d2.toInstant()
                .truncatedTo(ChronoUnit.DAYS);
        return instant1.equals(instant2);
    }
}
