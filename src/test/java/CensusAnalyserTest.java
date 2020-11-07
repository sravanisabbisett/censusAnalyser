import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;

public class CensusAnalyserTest {
    CensusAnalyserException analyserException;
    public static final String STATE_CENSUS_FILE = "C:\\Users\\PC\\IdeaProjects\\StateCensusAnalysis1\\src\\main\\java\\StateCensusData.csv";
    public static final String STATE_CODE_FILE = "C:\\Users\\PC\\IdeaProjects\\StateCensusAnalysis1\\src\\main\\java\\StateCode.csv";
    public static final String WRONG_FILE = "C:\\Users\\PC\\IdeaProjects\\StateCensusAnalysis1\\src\\main\\java\\StateCode.txt";
    public static final String STATE_CENSUS_FILE_WRONG_DELIMITER = "C:\\Users\\PC\\IdeaProjects\\StateCensusAnalysis1\\src\\main\\java\\StateCensusData 1.csv";
    public static final String STATE_CENSUS_FILE_WRONG_HEADER = "C:\\Users\\PC\\IdeaProjects\\StateCensusAnalysis1\\src\\main\\java\\StateCensusData.csv";
    public static final String STATE_CODE_FILE_WRONG_HEADER = "C:\\Users\\PC\\IdeaProjects\\StateCensusAnalysis1\\src\\main\\java\\StateCodeWrongHeader.csv";
    public static final String STATE_CENSUS_FILE_WRONG_FILE_EXTENSION = "C:\\Users\\PC\\IdeaProjects\\StateCensusAnalysis1\\src\\test\\java\\StateCensusData.csv";
    public static final String STATE_CODE_FILE_WRONG_FILE_EXTENSION = "C:\\Users\\PC\\IdeaProjects\\StateCensusAnalysis1\\src\\test\\java\\StateCodeData.csv";
    public static final String STATE_CODE_FILE_WRONG_DELIMITER = "C:\\Users\\PC\\IdeaProjects\\StateCensusAnalysis1\\src\\main\\java\\StateCodeWrongDelimiter.csv";

    CensusAnalyser censusAnalyser;

    @Before
    public void setUp() throws Exception {
        censusAnalyser = new CensusAnalyser();
    }

    @Test
    public void givenStateCensusCsvFile_ItHasCorrectNumber_ShouldMatchRecords() throws CensusAnalyserException {
        int numOfRecords = censusAnalyser.loadCensusData(STATE_CENSUS_FILE);
        Assert.assertEquals(29, numOfRecords);
    }

    @Test
    public void givenStateCensusCsvFile_WhenCorrect_ButFileExtensionIncorrect_ShouldThrowCensusAnalyserException() throws CensusAnalyserException {
        try {
            int noOfRecords = censusAnalyser.loadCensusData(STATE_CENSUS_FILE_WRONG_FILE_EXTENSION);
        } catch (CensusAnalyserException censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, censusAnalyserException.type);
        }
    }

    @Test
    public void givenStateCensusCsvFile_IfDoesntExists_ShouldThrowCensusAnalyserException() {
        try {
            int noOfRecords = censusAnalyser.loadCensusData(WRONG_FILE);
        } catch (CensusAnalyserException censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, censusAnalyserException.type);
        }
    }

    @Test
    public void givenStateCensusFileCorrect_WhenHeadersIncorrect_ShouldThrowCensusAnalyserException() {
        try {
            int noOfRecords = censusAnalyser.loadCensusData(STATE_CENSUS_FILE_WRONG_HEADER);
        } catch (CensusAnalyserException censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE_ISSUE, censusAnalyserException.type);
        }
    }

    @Test
    public void givenStateCensusFile_WhenInvalidDelimiter_ShouldThrowCensusAnalysisException() {
        try {
            int noOfRecords = censusAnalyser.loadCensusData(STATE_CENSUS_FILE_WRONG_DELIMITER);
        } catch (CensusAnalyserException censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE_ISSUE, censusAnalyserException.type);
        }
    }

    @Test
    public void givenStateCodeCsvFile_ItHasCorrectNumber_ShouldMatchRecords() throws CensusAnalyserException {
        int numOfRecords = censusAnalyser.loadStateCode(STATE_CODE_FILE);
        Assert.assertEquals(37, numOfRecords);
    }

    @Test
    public void givenStateCodeCsvFile_WhenCorrect_ButFileExtensionIncorrect_ShouldThrowCensusAnalyserException() {
        try {
            int numOfRecords = censusAnalyser.loadStateCode(STATE_CODE_FILE_WRONG_FILE_EXTENSION);
        } catch (CensusAnalyserException censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, censusAnalyserException.type);
        }
    }

    @Test
    public void givenStateCodeCsvFile_IfDoesntExists_ShouldThrowCensusAnalyserException() {
        try {
            int noOfRecords = censusAnalyser.loadStateCode(WRONG_FILE);
        } catch (CensusAnalyserException censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, censusAnalyserException.type);
        }
    }

    @Test
    public void givenStateCodeFileCorrect_WhenHeadersIncorrect_ShouldThrowCensusAnalyserException() {
        try {
            int noOfRecords = censusAnalyser.loadStateCode(STATE_CODE_FILE_WRONG_HEADER);
        } catch (CensusAnalyserException censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE_ISSUE, censusAnalyserException.type);
        }
    }

    @Test
    public void givenStateCodeFile_WhenInvalidDelimiter_ShouldThrowCensusAnalysisException() {
        try {
            int noOfRecords = censusAnalyser.loadStateCode(STATE_CODE_FILE_WRONG_DELIMITER);
        } catch (CensusAnalyserException censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE_ISSUE, censusAnalyserException.type);
        }
    }

    @Test
    public void givenIndianCensusData_ShouldSortedOnState_ShouldReturnResult() {
        String sortedCensusData = null;
        try {
            sortedCensusData = censusAnalyser.getStateWiseSortedCensusData(STATE_CENSUS_FILE);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals("Andhra Pradesh", censusCSV[0].state);
            Assert.assertEquals("West Bengal", censusCSV[28].state);
        } catch (CensusAnalyserException censusAnalyserException) {
            censusAnalyserException.printStackTrace();
        }
    }

    @Test
    public void givenIndianCensusData_WhenSortedOnPopulation_ShouldReturnSortedResult() throws CensusAnalyserException {

        String sortedCensusData = null;
        try {
            sortedCensusData = censusAnalyser.getPopulationWiseSortedCensusData(STATE_CENSUS_FILE);
            IndiaCensusCSV[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(199812341, censusCSV[censusCSV.length - 1].population);
            Assert.assertEquals(607688,censusCSV[0].population);
        }catch (CensusAnalyserException censusAnalyserException){
            censusAnalyserException.printStackTrace();
        }

    }

    @Test
    public void givenCensusData_WhenSortedOnArea_ShouldReturnSortedResult() {
        String sortedCensusData = null;
        try {
            sortedCensusData = censusAnalyser.getAreaWiseSortedCensusData(STATE_CENSUS_FILE);
            IndiaCensusCSV[] censusCSVS = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(342239, censusCSVS[censusCSVS.length - 1].areaInSqKm);
            Assert.assertEquals(0gwt,censusCSVS[0].areaInSqKm);
        } catch (CensusAnalyserException censusAnalyserException) {
            censusAnalyserException.printStackTrace();
        }
    }
    @Test
    public void givenCensusData_WhenSortedOnDensity_ShouldReturnSortedResult() {
        String sortedCensusData = null;
        try {
            sortedCensusData = censusAnalyser.getDensityWiseSortedCensusData(STATE_CENSUS_FILE);
            IndiaCensusCSV[] censusCSVS = new Gson().fromJson(sortedCensusData, IndiaCensusCSV[].class);
            Assert.assertEquals(1102, censusCSVS[censusCSVS.length - 1].densityPerSqKm);
            Assert.assertEquals(0,censusCSVS[0].densityPerSqKm);
        } catch (CensusAnalyserException censusAnalyserException) {
            censusAnalyserException.printStackTrace();
        }
    }
    @Test
    public void givenStateCodeData_WhenSortedOnTin_ShouldReturnSortedResult() {
        String sortedCensusData = null;
        try {
            sortedCensusData = censusAnalyser.getTinWiseSortedStateCodeData(STATE_CODE_FILE);
            IndiaStateCodeCSV[] censusCSVS = new Gson().fromJson(sortedCensusData, IndiaStateCodeCSV[].class);
            Assert.assertEquals("AD", censusCSVS[0].tin);
            Assert.assertEquals("WB",censusCSVS[censusCSVS.length-1].tin);
        } catch (CensusAnalyserException censusAnalyserException) {
            censusAnalyserException.printStackTrace();
        }
    }
}


