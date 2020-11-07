import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class CommonCsvBuilder<E> implements ICommomCsvBuilder {

    @Override
    public List<CSVRecord> getCsvFileList(Reader reader) throws CensusAnalyserException {
        try {
            Iterable<CSVRecord> csvRecordIterable = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
            return StreamSupport.stream(csvRecordIterable.spliterator(),false).collect(Collectors.toList());
        }catch (IOException ioException){
            throw new CensusAnalyserException(ioException.getMessage(),CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }
}
