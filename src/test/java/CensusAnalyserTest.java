import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

public class CensusAnalyserTest {
    public static final String STATE_CENSUS_FILE="C:\\Users\\PC\\IdeaProjects\\StateCensusAnalysis1\\src\\main\\java\\StateCensusData.csv";


    @Test
    public void givenStateSensusCsvFile_ItHasCorrectNumber_ShouldMatchRecords() throws IOException {
        CensusAnalyser censusAnalyser=new CensusAnalyser();
        int numOfRecords=censusAnalyser.loadCensusData(STATE_CENSUS_FILE);
        Assert.assertEquals(29,numOfRecords);
    }
}
