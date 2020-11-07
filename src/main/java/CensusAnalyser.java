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
    List<IndiaStateCodeCSV> indiaStateCodeCSVList=null;


    public int loadCensusData(String filePathCSV) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(filePathCSV))) {
            ICSVBuilder csvBuilder=CSVBuilderFactory.createCSVBuilder();
            indianCensusCSVList=csvBuilder.getCsvFileIterator(reader,IndiaCensusCSV.class);
            return indianCensusCSVList.size();
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
            indiaStateCodeCSVList=csvBuilder.getCsvFileIterator(reader,IndiaStateCodeCSV.class);
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
    public int loadDataWithCommonsCSV(String csvFilePath) throws CensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvFilePath))) {
            ICommomCsvBuilder commonsCSVBuilder = CommonsCSVBuilderFactory.createCSVBuilder();
            List<CSVRecord> csvFileList = commonsCSVBuilder.getCsvFileList(reader);
            return csvFileList.size();
        } catch (IOException ioException) {
            throw new CensusAnalyserException(ioException.getMessage(),CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM);
        }
    }

    public String getStateWiseSortedCensusData(String csvFilepath) throws CensusAnalyserException {
        loadCensusData(csvFilepath);
        if(indianCensusCSVList == null || indianCensusCSVList.size() == 0) {
            throw new CensusAnalyserException("No census data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.state);
        this.sort(indianCensusCSVList, censusComparator);
        String sortedStateCensusAsJSON = new Gson().toJson(indianCensusCSVList);
        return sortedStateCensusAsJSON;
    }
    public String getStateWiseSortedCensusData1() throws CensusAnalyserException {
        if(indianCensusCSVList == null || indianCensusCSVList.size() == 0) {
            throw new CensusAnalyserException("No census data", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.state);
        this.sort(indianCensusCSVList, censusComparator);
        String sortedStateCensusAsJSON = new Gson().toJson(indianCensusCSVList);
        return sortedStateCensusAsJSON;
    }
    public String getPopulationWiseSortedCensusData(String csvFilePath) throws CensusAnalyserException {
        loadCensusData(csvFilePath);
        if (indianCensusCSVList == null || indianCensusCSVList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.population);
        this.sort(indianCensusCSVList,censusComparator);
        String sortedStateCensusJson = new Gson().toJson(indianCensusCSVList);
        return sortedStateCensusJson;
    }

    public String getAreaWiseSortedCensusData(String csvFilePath) throws CensusAnalyserException {
        loadCensusData(csvFilePath);
        if (indianCensusCSVList == null || indianCensusCSVList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.areaInSqKm);
        this.sort(indianCensusCSVList,censusComparator);
        String sortedStateCensusJson = new Gson().toJson(indianCensusCSVList);
        return sortedStateCensusJson;
    }

    public String getDensityWiseSortedCensusData(String csvFilePath) throws CensusAnalyserException {
        loadCensusData(csvFilePath);
        if (indianCensusCSVList == null || indianCensusCSVList.size() == 0) {
            throw new CensusAnalyserException("NO_CENSUS_DATA", CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaCensusCSV> censusComparator = Comparator.comparing(census -> census.densityPerSqKm);
        this.sort(indianCensusCSVList,censusComparator);
        String sortedStateCensusJson = new Gson().toJson(indianCensusCSVList);
        return sortedStateCensusJson;
    }

    public String getTinWiseSortedStateCodeData(String csvFilePath) throws CensusAnalyserException{
        loadStateCode(csvFilePath);
        if(indiaStateCodeCSVList ==null || indiaStateCodeCSVList.size()==0){
            throw new CensusAnalyserException("NO_CENSUS_DATA",CensusAnalyserException.ExceptionType.NO_CENSUS_DATA);
        }
        Comparator<IndiaStateCodeCSV> indiaStateCodeCSVComparator=Comparator.comparing(census->census.tin);
        this.sort(indiaStateCodeCSVList,indiaStateCodeCSVComparator);
        String sortedStateCodeData=new Gson().toJson(indiaStateCodeCSVList);
        return sortedStateCodeData;
    }

    private <E> void sort(List<E> csvList, Comparator<E> comparator) {
        for (int i = 0; i < csvList.size() - 1; i++) {
            for (int j = 0; j < csvList.size() - i - 1; j++) {
                E census1 = csvList.get(j);
                E census2 = csvList.get(j + 1);
                if (comparator.compare(census1, census2) > 0) {
                    csvList.set(j, census2);
                    csvList.set(j + 1, census1);
                }
            }
        }
    }
}
