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
            CsvToBean<IndiaCensusCSV> csvToBean = new CsvToBeanBuilder<IndiaCensusCSV>(reader)
                    .withType(IndiaCensusCSV.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator<IndiaCensusCSV> indiaCensusCSVIterator = csvToBean.iterator();
            Iterable<IndiaCensusCSV> censusCSVIterable = () -> indiaCensusCSVIterator;
            int numOfEntries = (int) StreamSupport.stream(censusCSVIterable.spliterator(), false).count();
            return numOfEntries;
        } catch (NoSuchFileException exception) {
            throw new CensusAnalyserException(exception.getMessage(), CensusAnalyserException.ExceptionType.NO_SUCH_FILE);
        } catch (IOException exception) {
            throw new CensusAnalyserException(exception.getMessage(), CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (IllegalStateException exception) {
            throw new CensusAnalyserException(exception.getMessage(), CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        } catch (RuntimeException exception) {
            throw new CensusAnalyserException(exception.getMessage(), CensusAnalyserException.ExceptionType.INCORRECT_DATA_ISSUE);
        }
    }
    public int loadStateCode(String filePathCSV) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePathCSV))) {
            ;
            CsvToBean<IndiaStateCodeCSV> csvToBean = new CsvToBeanBuilder<IndiaStateCodeCSV>(reader)
                    .withType(IndiaStateCodeCSV.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();
            Iterator<IndiaStateCodeCSV> indiaStateCodeCSVIterator = csvToBean.iterator();
            Iterable<IndiaStateCodeCSV> indiaStateCodeCSVIterable = () -> indiaStateCodeCSVIterator;
            int numOfEntries = (int) StreamSupport.stream(indiaStateCodeCSVIterable.spliterator(), false).count();
            return numOfEntries;
        }catch (NoSuchFieldError exception) {
            throw new CensusAnalyserException(exception.getMessage(),CensusAnalyserException.ExceptionType.DELIMITER_ISSUE);
        } catch (NoSuchFileException exception){
            throw new CensusAnalyserException(exception.getMessage(),CensusAnalyserException.ExceptionType.NO_SUCH_FILE);
        } catch (IOException exception){
            throw new CensusAnalyserException(exception.getMessage(),CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }catch (IllegalStateException exception){
            throw new CensusAnalyserException(exception.getMessage(),CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }catch (RuntimeException exception){
            throw new CensusAnalyserException(exception.getMessage(), CensusAnalyserException.ExceptionType.INCORRECT_DATA_ISSUE);
        }
    }
}
