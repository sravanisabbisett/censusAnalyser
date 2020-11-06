import org.apache.commons.collections4.iterators.IteratorIterable;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class CensusAnalyser {

    public int loadCensusData(String filePathCSV) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePathCSV))) {
            ICSVBuilder csvBuilder=CSVBuilderFactory.createCSVBuilder();
            List<IndiaCensusCSV> indiaCensusCSVList=csvBuilder.getCsvFileIterator(reader,IndiaCensusCSV.class);
            return indiaCensusCSVList.size();
        } catch (IOException exception) {
            throw new CensusAnalyserException(exception.getMessage(),
                                                CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        } catch (RuntimeException exception) {
            throw new CensusAnalyserException(exception.getMessage(),
                                                CensusAnalyserException.ExceptionType.INCORRECT_FILE_ISSUE);
        }catch (CSVBuilderException builderException){
            throw new CensusAnalyserException(builderException.getMessage()
                                        ,CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }
    }


    public int loadStateCode(String filePathCSV) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePathCSV))) {
            ICSVBuilder csvBuilder=CSVBuilderFactory.createCSVBuilder();
            List<IndiaStateCodeCSV> indiaStateCodeCSVList=csvBuilder.getCsvFileIterator(reader,IndiaStateCodeCSV.class);
            return indiaStateCodeCSVList.size();
        } catch (IOException exception){
            throw new CensusAnalyserException(exception.getMessage(),
                                                CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }catch (RuntimeException exception){
            throw new CensusAnalyserException(exception.getMessage(),
                                                CensusAnalyserException.ExceptionType.INCORRECT_FILE_ISSUE);
        }catch (CSVBuilderException builderException){
            throw new CensusAnalyserException(builderException.getMessage(),
                                                CensusAnalyserException.ExceptionType.UNABLE_TO_PARSE);
        }
    }

    private <E> int getCount(Iterator<E> iterator){
        Iterable<E> csvIterator=()->iterator;
        int numOfEntries = (int) StreamSupport.stream(csvIterator.spliterator(), false).count();
        return numOfEntries;
    }

}
