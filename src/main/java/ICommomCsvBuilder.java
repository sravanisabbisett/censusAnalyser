import org.apache.commons.csv.CSVRecord;

import java.io.Reader;
import java.util.List;

public interface ICommomCsvBuilder {
    List<CSVRecord> getCsvFileList(Reader reader) throws CensusAnalyserException;
}
