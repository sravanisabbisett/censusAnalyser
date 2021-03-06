import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CommonCsvSensorAnalyserTest {
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
        int numOfRecords = censusAnalyser.loadDataWithCommonsCSV(STATE_CENSUS_FILE);
        Assert.assertEquals(29, numOfRecords);
    }

    @Test
    public void givenStateCensusCsvFile_WhenCorrect_ButFileExtensionIncorrect_ShouldThrowCensusAnalyserException() throws CensusAnalyserException {
        try {
            int noOfRecords = censusAnalyser.loadDataWithCommonsCSV(STATE_CENSUS_FILE_WRONG_FILE_EXTENSION);
        } catch (CensusAnalyserException censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, censusAnalyserException.type);
        }
    }

    @Test
    public void givenStateCensusCsvFile_IfDoesntExists_ShouldThrowCensusAnalyserException() {
        try {
            int noOfRecords = censusAnalyser.loadDataWithCommonsCSV(WRONG_FILE);
        } catch (CensusAnalyserException censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, censusAnalyserException.type);
        }
    }

    @Test
    public void givenStateCensusFileCorrect_WhenHeadersIncorrect_ShouldThrowCensusAnalyserException() {
        try {
            int noOfRecords = censusAnalyser.loadDataWithCommonsCSV(STATE_CENSUS_FILE_WRONG_HEADER);
        } catch (CensusAnalyserException censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE_ISSUE, censusAnalyserException.type);
        }
    }

    @Test
    public void givenStateCensusFile_WhenInvalidDelimiter_ShouldThrowCensusAnalysisException() {
        try {
            int noOfRecords = censusAnalyser.loadDataWithCommonsCSV(STATE_CENSUS_FILE_WRONG_DELIMITER);
        } catch (CensusAnalyserException censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE_ISSUE, censusAnalyserException.type);
        }
    }

    @Test
    public void givenStateCodeCsvFile_ItHasCorrectNumber_ShouldMatchRecords() throws CensusAnalyserException {
        int numOfRecords = censusAnalyser.loadDataWithCommonsCSV(STATE_CODE_FILE);
        Assert.assertEquals(37, numOfRecords);
    }

    @Test
    public void givenStateCodeCsvFile_WhenCorrect_ButFileExtensionIncorrect_ShouldThrowCensusAnalyserException() {
        try {
            int numOfRecords = censusAnalyser.loadDataWithCommonsCSV(STATE_CODE_FILE_WRONG_FILE_EXTENSION);
        } catch (CensusAnalyserException censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, censusAnalyserException.type);
        }
    }

    @Test
    public void givenStateCodeCsvFile_IfDoesntExists_ShouldThrowCensusAnalyserException() {
        try {
            int noOfRecords = censusAnalyser.loadDataWithCommonsCSV(WRONG_FILE);
        } catch (CensusAnalyserException censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.CENSUS_FILE_PROBLEM, censusAnalyserException.type);
        }
    }

    @Test
    public void givenStateCodeFileCorrect_WhenHeadersIncorrect_ShouldThrowCensusAnalyserException() {
        try {
            int noOfRecords = censusAnalyser.loadDataWithCommonsCSV(STATE_CODE_FILE_WRONG_HEADER);
        } catch (CensusAnalyserException censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE_ISSUE, censusAnalyserException.type);
        }
    }

    @Test
    public void givenStateCodeFile_WhenInvalidDelimiter_ShouldThrowCensusAnalysisException() {
        try {
            int noOfRecords = censusAnalyser.loadDataWithCommonsCSV(STATE_CODE_FILE_WRONG_DELIMITER);
        } catch (CensusAnalyserException censusAnalyserException) {
            Assert.assertEquals(CensusAnalyserException.ExceptionType.INCORRECT_FILE_ISSUE, censusAnalyserException.type);
        }
    }

}


