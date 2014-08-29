package stewi.hive.udf;

import java.util.HashMap;
import java.util.Map;

import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import eu.bitwalker.useragentutils.Version;

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
		
		result.put("os",ua.getOperatingSystem().toString());
		result.put("os.name", ua.getOperatingSystem().getName());
		result.put("os.manufacturer", ua.getOperatingSystem().getManufacturer().getName());
		result.put("os.device.type", ua.getOperatingSystem().getDeviceType().getName());
		result.put("os.device.mobile", String.valueOf(ua.getOperatingSystem().isMobileDevice()));
		
		result.put("browser", ua.getBrowser().toString());
		result.put("browser.manufacturer", ua.getBrowser().getManufacturer().getName());
		result.put("browser.type", ua.getBrowser().getBrowserType().getName());
		result.put("browser.engine", ua.getBrowser().getRenderingEngine().toString());
		
		if(ua.getBrowserVersion()!=null) {
			result.put("browser.version", ua.getBrowserVersion().getVersion());
			result.put("browser.majorversion", ua.getBrowserVersion().getMajorVersion());
			result.put("browser.minorversion", ua.getBrowserVersion().getMinorVersion());
		}
		
		result.put("browser.name", ua.getBrowser().getName());

		return result;
	}

}
