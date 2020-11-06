import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.util.Iterator;

public class OpenCSVBuilder {
    public <E> Iterator<E> getCsvFileIterator(Reader reader, Class<E> csvClass) throws CensusAnalyserException {
        try {
            CsvToBean<E> csvToBean = new CsvToBeanBuilder<E>(reader)
                    .withType(csvClass)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            return csvToBean.iterator();
        }catch (IllegalStateException exception){
            throw new CensusAnalyserException(exception.getMessage(),CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }
    }
}
