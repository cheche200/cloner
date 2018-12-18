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

    @Test
    public void canReadInputDataFromExcelFile() throws IOException {
        InputStream inputStream = getClass().getResourceAsStream("/ExcelTest.xlsx");
        ExcelFile excelFile = new ExcelFile(inputStream);

        List<HashMap<String, String>> expected = getDataToSubstitute();

        List<HashMap<String, String>> actual = excelFile.getDataToSubstitute();

        assertEquals(expected, actual);
    }

}
