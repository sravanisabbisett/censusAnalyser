import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public class CommonCsvBuilder<E> implements ICSVBuilder {

    @Override
    public List getCsvFileIterator(Reader reader, Class csvClass) throws CSVBuilderException {
        try {
            CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader(csvClass).withIgnoreHeaderCase());
            List<CSVRecord> csvList = csvParser.getRecords();
            return csvList;
        }catch (IOException ioException){
            throw new CSVBuilderException(ioException.getMessage(),CSVBuilderException.ExceptionType.UNABLE_TO_PARSE);
        }
    }

}
