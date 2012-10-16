package stewi.hive.udf;

import static org.junit.Assert.assertEquals;

import org.apache.hadoop.io.Text;
import org.junit.Test;

public class LevenshteinDistanceTest {

	@Test
	public void testEvaluate() {
		LevenshteinDistance udf = new LevenshteinDistance();
		int dist = udf.evaluate(new Text("ticket"),new Text("tockets"));
		assertEquals(2,dist);
	}

}
