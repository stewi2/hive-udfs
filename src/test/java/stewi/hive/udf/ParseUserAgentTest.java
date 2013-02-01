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
		assertEquals("WEB_BROWSER",output.get("browsertype"));
		assertEquals("TRIDENT",output.get("engine"));
	}

}
