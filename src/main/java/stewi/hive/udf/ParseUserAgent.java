package stewi.hive.udf;

import java.util.HashMap;
import java.util.Map;

import nl.bitwalker.useragentutils.UserAgent;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

public class ParseUserAgent extends UDF {

	public ParseUserAgent() {
		// TODO Auto-generated constructor stub
	}

	public Map<String,String> evaluate(Text input) {
		if(input==null) return null;

		Map<String,String> result = new HashMap<String,String>();

		UserAgent ua = UserAgent.parseUserAgentString(input.toString());
		result.put("browser",ua.getBrowser().toString());
		result.put("os",ua.getOperatingSystem().toString());
		result.put("browsertype", ua.getBrowser().getBrowserType().toString());
		result.put("engine", ua.getBrowser().getRenderingEngine().toString());

		return result;
	}

}
