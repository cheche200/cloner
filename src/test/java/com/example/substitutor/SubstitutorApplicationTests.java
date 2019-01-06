package com.example.substitutor;

import com.example.substitutor.model.input.Input;
import com.example.substitutor.model.input.ExcelFile;
import com.example.substitutor.model.output.FileOutput;
import com.example.substitutor.model.output.Output;
import com.example.substitutor.model.template.Template;
import com.example.substitutor.model.template.TemplateFile;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.example.substitutor.FixtureData.*;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SubstitutorApplicationTests {

	public static final int INDEX_FIRST_DATA_ROW = 0;
	private Substitutor substitutor;
	private Input input;
	private Template template;
	private Output output;

	private String TEMPLATE_TO_SUBSTITUTE = "INSERT INTO table_name (columnA, columnB, columnC) VALUES " +
			"(${columnA}, ${columnB}, ${columnC});";

	@Before
	public void setup(){
		substitutor = new Substitutor();
		input = new ExcelFile();
		template = new TemplateFile();
		output = new FileOutput();
	}

	@Test
	public void canSubstituteDataFromExcelFileUsingTemplate(){
		template.setTemplate(TEMPLATE_TO_SUBSTITUTE);

		input.setDataToSubstitute(getDataToSubstitute());

		output.setSubstitutedData(substitutor.substitute(input, template));

		assertEquals("INSERT INTO table_name (columnA, columnB, columnC) VALUES " +
				"(valueA2, valueB2, valueC2);", output.getSubstitutedData().get(INDEX_FIRST_DATA_ROW) );
	}

	@Test(expected = Substitutor.SubstitutorException.class)
	public void shouldNotApplySubstitutionWithoutInputData(){
		template.setTemplate(TEMPLATE_TO_SUBSTITUTE);
		input.setDataToSubstitute(null);

		substitutor.substitute(input, template);
	}

	@Test(expected = Substitutor.SubstitutorException.class)
	public void shouldNotApplySubstitutionWithoutTemplate(){
		template.setTemplate(null);
		input.setDataToSubstitute(getDataToSubstitute());

		substitutor.substitute(input, template);
	}

	@Test
    public void outputShouldFormatTheSubstitutedData(){
	    template.setTemplate(TEMPLATE_TO_SUBSTITUTE);
	    input.setDataToSubstitute(getDataToSubstitute());
	    String expectedFormat = getExpectedOutputFormat();

	    output.setSubstitutedData(substitutor.substitute(input, template));

	    assertEquals(expectedFormat, output.format());
    }

}

