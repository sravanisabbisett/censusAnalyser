public class CensusAnalyserException extends Exception{

    enum ExceptionType {
        CENSUS_FILE_PROBLEM, UNABLE_TO_PARSE, NO_SUCH_FILE, DELIMITER_ISSUE,WRONG_PATH;
    }

    ExceptionType type;
    public CensusAnalyserException(String meaasge,ExceptionType type){
        super(meaasge);
        this.type=type;
    }
}