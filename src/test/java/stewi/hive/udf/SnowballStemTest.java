package stewi.hive.udf;

import static org.junit.Assert.*;

import org.apache.hadoop.io.Text;
import org.junit.Test;

public class SnowballStemTest {

	@Test
	public void testEvaluate() {
		SnowballStem udf = new SnowballStem();
		String result = udf.evaluate(new Text("apply"), new Text("English")).toString();
		assertEquals("appli", result);
	}

}
