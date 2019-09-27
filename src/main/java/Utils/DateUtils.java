package Utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DateUtils {

    private DateUtils(){
        throw new AssertionError();
    }

    public static String dataAtual(){
        Calendar calendar = Calendar.getInstance();
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        return df.format(calendar.getTime());
    }

    public static Integer semanaDoAno(){
        return Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
    }


    public static String chaveSemanaAno(){

        return semanaDoAno()+String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
    }
}
