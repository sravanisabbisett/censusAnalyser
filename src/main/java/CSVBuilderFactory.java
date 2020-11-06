
public class CSVBuilderFactory {

    public static ICSVBuilder createCSVBuilder() {
        return new CommonCsvBuilder();
    }
}
