import com.opencsv.bean.CsvBindByName;

public class IndiaStateCodeCSV {
    @CsvBindByName(column = "SrNo", required = true)
    public int srNo;

    @CsvBindByName(column = "State", required = true)
    public String state;

    @CsvBindByName(column = "Name", required = true)
    public int name;

    @CsvBindByName(column = "TIN", required = true)
    public String tin;

    @CsvBindByName(column = "StateCode", required = true)
    public int stateCode;
}
