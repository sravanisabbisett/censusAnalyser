import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.stream.StreamSupport;

public class CensusAnalyser{

    public int loadCensusData(String filePathCSV) throws IOException {
        Reader reader= Files.newBufferedReader(Paths.get(filePathCSV));
        CsvToBean<IndiaCensusCSV> csvToBean=new CsvToBeanBuilder<IndiaCensusCSV>(reader)
                                    .withType(IndiaCensusCSV.class)
                                    .withIgnoreLeadingWhiteSpace(true)
                                    .build();
        Iterator<IndiaCensusCSV> indiaCensusCSVIterator=csvToBean.iterator();
        Iterable<IndiaCensusCSV> censusCSVIterable =() ->indiaCensusCSVIterator;
        int numOfEntries=(int) StreamSupport.stream(censusCSVIterable.spliterator(),false).count();
        return numOfEntries;


    }

}
