import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

/**
 * 설명
 *
 * @author : Charles Kim
 * @fileName :  JSONtoCSV
 * @since : 2023-10-16
 */
public class JSONtoCSV {

    public static void main(String[] args) throws IOException {

//        JsonNode jsonTree = new ObjectMapper().readTree(new File("src/main/webapp/statics/map/modules/common/temp_data/bus_station.json"));
//        JsonNode jsonTree = new ObjectMapper().readTree(new InputStreamReader(new FileInputStream("src/main/webapp/statics/map/modules/common/temp_data/bus_station.json"), "UTF-8"));
//        CsvSchema.Builder csvSchemaBuilder = CsvSchema.builder();
//        JsonNode firstObject = jsonTree.elements().next();
//        firstObject.fieldNames().forEachRemaining(fieldName -> {csvSchemaBuilder.addColumn(fieldName);} );
//        CsvSchema csvSchema = csvSchemaBuilder.build().withHeader();
//
//        CsvMapper csvMapper = new CsvMapper();
//        csvMapper.writerFor(JsonNode.class)
//                .with(csvSchema)
//                .writeValue(new File("src/main/webapp/statics/map/modules/common/temp_data/orderLines.csv"), jsonTree);
    }
}
