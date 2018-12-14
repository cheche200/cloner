package com.example.substitutor;

import com.example.substitutor.model.input.Input;
import com.example.substitutor.model.input.ExcelFile;
import com.example.substitutor.model.output.Output;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SubstitutorApplicationTests {

	Substitutor substitutor;
	Input input;

	String formatToSubstitute = "INSERT INTO table_name (column1, column2, column3) VALUES " +
			"(${column1}, ${column2}, ${column3});";

	@Before
	public void setup(){
		substitutor = new Substitutor();
		input = new ExcelFile();
	}


	@Test
	public void canSubstituteDataFromExcelFile(){

		input.setFormatToSubstitute(formatToSubstitute);
		input.setDataToSubstitute(getDataToSubstitute());

		Output output = substitutor.run(input);

		assertEquals("INSERT INTO table_name (column1, column2, column3) VALUES " +
				"(value1, value2, value3);", output.getSubstitutedData() );
	}

	private List<HashMap<String, String>> getDataToSubstitute() {
		List<HashMap<String,String>> dataToSubstitute = new ArrayList<HashMap<String, String>>();

		HashMap<String,String> dataRow = new HashMap<String, String>();
		dataRow.put("column1", "value1");
		dataRow.put("column2", "value2");
		dataRow.put("column3", "value3");

		dataToSubstitute.add(dataRow);
		return dataToSubstitute;
	}


}

