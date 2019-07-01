package edu.up.ihelppo.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Metodos {

    public static String ConverterData(String data)
    {
        if(data.contains("-"))
        {
            Date tmpDate;

            SimpleDateFormat formatoOriginal = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            SimpleDateFormat formatoNovo = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

            try
            {
                tmpDate = formatoOriginal.parse(data);
                data = formatoNovo.format(tmpDate);
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
        }
        return data;
    }
}
