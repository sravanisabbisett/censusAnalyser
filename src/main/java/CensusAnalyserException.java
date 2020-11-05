public class CensusAnalyserException extends Exception{


    enum ExceptionType {
        CENSUS_FILE_PROBLEM, UNABLE_TO_PARSE, NO_SUCH_FILE, DELIMITER_ISSUE,INCORRECT_DATA_ISSUE;
    }

    ExceptionType type;
    public CensusAnalyserException(String message,ExceptionType type){
        super(message);
        this.type=type;
    }

}