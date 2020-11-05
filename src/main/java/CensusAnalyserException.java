public class CensusAnalyserException extends Exception{


    enum ExceptionType {
        CENSUS_FILE_PROBLEM, UNABLE_TO_PARSE, NO_SUCH_FILE, DELIMITER_ISSUE,INCORRECT_DATA_ISSUE;
    }

    ExceptionType type;
    public CensusAnalyserException(String meaasge,ExceptionType type){
        super(meaasge);
        this.type=type;
    }
    public CensusAnalyserException(String message,ExceptionType type,Throwable cause){
        super(message,cause);
        this.type=type;

    }
}