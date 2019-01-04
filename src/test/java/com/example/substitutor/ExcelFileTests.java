package com.example.substitutor;

import com.example.substitutor.model.input.ExcelFile;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.example.substitutor.FixtureData.getDataToSubstitute;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExcelFileTests {

    public static final int INDEX_FIRST_DATA_ROW = 0;



    @Test
    public void shouldNotReadEmptyRowsFromExcelFile() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/ExcelTest.xlsx");
        ExcelFile excelFile = new ExcelFile(inputStream);

        int expectedNumOfRowsWithData = 3;
        int actualNumOfRowsWithData = excelFile.getDataToSubstitute().size();

        assertEquals(expectedNumOfRowsWithData, actualNumOfRowsWithData);
    }

    @Test
    public void canReadInputDataFromExcelFile() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/ExcelTest.xlsx");
        ExcelFile excelFile = new ExcelFile(inputStream);

        List<HashMap<String, String>> expectedRow = getDataToSubstitute();

        List<HashMap<String, String>> actualRow = excelFile.getDataToSubstitute();

        assertEquals(expectedRow.get(INDEX_FIRST_DATA_ROW), actualRow.get(INDEX_FIRST_DATA_ROW));
    }

}
