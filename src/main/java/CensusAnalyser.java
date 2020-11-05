import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusAnalyser {

    public int loadCensusData(String filePathCSV) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePathCSV))) {
            Iterator<IndiaCensusCSV> indiaCensusCSVIterator = this.getCsvFileIterator(reader,IndiaCensusCSV.class);
            return this.getCount(indiaCensusCSVIterator);
        } catch (IOException exception) {
            throw new CensusAnalyserException(exception.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException exception) {
            throw new CensusAnalyserException(exception.getMessage(), CensusAnalyserException.ExceptionType.INCORRECT_FILE_ISSUE);
        }
    }

    public int loadStateCode(String filePathCSV) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePathCSV))) {
            Iterator<IndiaStateCodeCSV> indiaStateCodeCSVIterator =this.getCsvFileIterator(reader,IndiaStateCodeCSV.class);
            return this.getCount(indiaStateCodeCSVIterator);
        } catch (IOException exception){
            throw new CensusAnalyserException(exception.getMessage(),CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }catch (RuntimeException exception){
            throw new CensusAnalyserException(exception.getMessage(), CensusAnalyserException.ExceptionType.INCORRECT_FILE_ISSUE);
        }
    }

    private <E> Iterator<E> getCsvFileIterator(Reader reader,Class<E> csvClass) throws CensusAnalyserException{
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

    private <E> int getCount(Iterator<E> iterator){
        Iterable<E> csvIterator=()->iterator;
        int numOfEntries = (int) StreamSupport.stream(csvIterator.spliterator(), false).count();
        return numOfEntries;
    }
}
