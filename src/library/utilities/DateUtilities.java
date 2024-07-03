package library.utilities;

import java.sql.Date;
import java.util.Calendar;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class DateUtilities {
    public static Date addMonths(Date date, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, months);
        return new Date(cal.getTimeInMillis());
    }

    public static int calculateRemainTime(String time, Date bookDate){
        Date endDate = null;
        if(Objects.equals(time, "1 mese")){
            endDate = addMonths(bookDate, 1);
        } else if (Objects.equals(time, "2 mesi")){
            endDate = addMonths(bookDate, 2);
        } else if (Objects.equals(time, "3 mesi")){
            endDate = addMonths(bookDate, 3);
        }
        assert endDate != null;
        long diffInMillis = Math.abs(Calendar.getInstance().getTime().getTime() - endDate.getTime());
        return (int) TimeUnit.DAYS.convert(diffInMillis, TimeUnit.MILLISECONDS);
    }
}
