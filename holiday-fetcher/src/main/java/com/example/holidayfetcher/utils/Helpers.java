package com.example.holidayfetcher.utils;

import com.example.holidayfetcher.model.Holiday;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.io.*;
import java.util.List;

public class Helpers {
    static ObjectMapper mapper = new ObjectMapper();

    public static void saveToJson (List<Holiday> list) {
        String s = new Gson().toJson(list);
        try {
            mapper.writeValue(new File("holiday.json"), list);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final String CSV_SEPARATOR = ",";
    public static void saveToCsv(List<Holiday> list)
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("holidays.csv"), "UTF-8"));
            for (Holiday holiday : list)
            {
                StringBuffer oneLine = new StringBuffer();
                oneLine.append(holiday.getId());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(holiday.getName());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(holiday.getDescription());
                oneLine.append(CSV_SEPARATOR);
                oneLine.append(holiday.getDate());
                bw.write(oneLine.toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        }
        catch (UnsupportedEncodingException e) {}
        catch (FileNotFoundException e){}
        catch (IOException e){}
    }
}