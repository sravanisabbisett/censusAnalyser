public class CensusAnalyserException extends Exception{

    enum ExceptionType {
        CENSUS_FILE_PROBLEM, UNABLE_TO_PARSE,INCORRECT_FILE_ISSUE,NO_CENSUS_DATA;
    }
    ExceptionType type;
    public CensusAnalyserException(String message,ExceptionType type){
        super(message);
        this.type=type;
    }

}