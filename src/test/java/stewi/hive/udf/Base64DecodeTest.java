package stewi.hive.udf;

import static org.junit.Assert.*;

import org.apache.hadoop.io.Text;
import org.junit.Test;

public class Base64DecodeTest {

	@Test
	public void testEvaluate() {
		Base64Decode decode = new Base64Decode();
		assertEquals("225995066",decode.evaluate(new Text("MjI1OTk1MDY2")).toString());
	}

}
