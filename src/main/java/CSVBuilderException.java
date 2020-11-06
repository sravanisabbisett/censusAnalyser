

public class CSVBuilderException extends Exception {
    enum ExceptionType{
        CENSUS_FILE_PROBLEM,UNABLE_TO_PARSE;
    }
    ExceptionType exceptionType;

    public CSVBuilderException(String message,ExceptionType exceptionType){
        super(message);
        this.exceptionType=exceptionType;
    }
}
