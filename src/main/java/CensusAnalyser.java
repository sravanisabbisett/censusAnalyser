import com.google.gson.Gson;
import org.apache.commons.collections4.iterators.IteratorIterable;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.StreamSupport;

public class CensusAnalyser {
    List<IndiaCensusCSV> indianCensusCSVList = null;

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
    public String getStateWiseSortedCensusData(String filePath) throws CensusAnalyserException {
        loadCensusData(filePath);
        if(indianCensusCSVList == null || indianCensusCSVList.size() == 0) {
            throw new CensusAnalyserException("No census data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.state);
        this.sort(censusComparator);
        String sortedStateCensusAsJSON = new Gson().toJson(indianCensusCSVList);
        return sortedStateCensusAsJSON;

    }
    public void sort(Comparator<IndiaCensusCSV> censusCSVComparator) {
        for(int index = 0; index < indianCensusCSVList.size() - 1; index++) {
            for(int index2 = 0; index2 < indianCensusCSVList.size() - index - 1; index2++) {
                IndiaCensusCSV census1 = indianCensusCSVList.get(index2);
                IndiaCensusCSV census2 = indianCensusCSVList.get(index2 + 1);
                if(censusCSVComparator.compare(census1, census2) > 0) {
                    indianCensusCSVList.set(index2, census2);
                    indianCensusCSVList.set(index2 + 1, census1);
                }
            }
        }
    }
}
