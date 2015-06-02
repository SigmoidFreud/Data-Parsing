package rollio;

/**
 * Created by Sushanth on 5/31/2015.
 */
import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.ParseBool;
import org.supercsv.cellprocessor.ParseDate;
import org.supercsv.cellprocessor.ParseInt;
import org.supercsv.cellprocessor.constraint.LMinMax;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.constraint.StrRegEx;
import org.supercsv.cellprocessor.constraint.UniqueHashCode;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ReadCSV {

    String filename;

    ReadCSV(String filename) {
        this.filename = filename;
    }


    public ArrayList<HashMap<String,Object>> readWithCsvMapReader() throws Exception {

        ICsvMapReader mapReader = null;

        try {
            mapReader = new CsvMapReader(new FileReader(this.filename), CsvPreference.STANDARD_PREFERENCE);

            // the header columns are used as the keys to the Map
            final String[] header = mapReader.getHeader(true);
            final CellProcessor[] processors = new CellProcessor[header.length];
            for (int i = 0 ; i < processors.length; i++) {
                processors[i] = new Optional();
            }
            ArrayList<HashMap<String,Object>> csvMap = new ArrayList<HashMap<String,Object>>();
            HashMap<String, Object> sfDataMap;
            while( (sfDataMap = new HashMap<String,Object>(mapReader.read(header, processors))) != null)  {
                csvMap.add(sfDataMap);
                System.out.println(sfDataMap);
            }
            return csvMap;
        }
        finally {
            if( mapReader != null ) {
                mapReader.close();
            }
        }
    }


}
