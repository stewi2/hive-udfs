package stewi.hive.udf;

import static org.junit.Assert.*;

import java.util.Map;

import org.apache.hadoop.io.Text;
import org.junit.Test;

public class ParseUserAgentTest {

	@Test
	public void testEvaluate() {
		ParseUserAgent udf = new ParseUserAgent();
		Map<String,String> output = udf.evaluate(new Text("Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 5.1; Trident/4.0; .NET CLR 1.1.4322; .NET CLR 2.0.50727; .NET CLR 3.0.4506.2152; .NET CLR 3.5.30729; InfoPath.2; .NET4.0C; .NET4.0E)"));

		assertEquals("IE8",output.get("browser"));
		assertEquals("WINDOWS_XP",output.get("os"));
		assertEquals("Windows XP",output.get("os.name"));
		assertEquals("Microsoft Corporation", output.get("os.manufacturer"));
		assertEquals("Browser",output.get("browser.type"));
		assertEquals("TRIDENT",output.get("browser.engine"));
		assertEquals("false",output.get("os.device.mobile"));
		assertEquals("Computer", output.get("os.device.type"));
		assertEquals("8", output.get("browser.majorversion"));
		assertEquals("0", output.get("browser.minorversion"));
		assertEquals("8.0", output.get("browser.version"));
		assertEquals("Internet Explorer 8", output.get("browser.name"));
		assertEquals("Microsoft Corporation", output.get("browser.manufacturer"));
		
		try {
			udf.evaluate(new Text("-"));
		} catch(Exception e) {
			fail("Invalid input caused unexpected exception");
		}
	}

}
