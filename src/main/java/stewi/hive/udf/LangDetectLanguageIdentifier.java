package stewi.hive.udf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;
import org.jsoup.parser.Parser;

import com.cybozu.labs.langdetect.Detector;
import com.cybozu.labs.langdetect.DetectorFactory;
import com.cybozu.labs.langdetect.LangDetectException;

public class LangDetectLanguageIdentifier extends UDF {

	// DetectorFactory is totally global, so we only want to do this once... ever!!!
	static boolean loaded;

	// profiles we will load from classpath
	static final String languages[] = {
		/*"af",*/ "ar", "bg", "bn", "cs", "da", "de", "el", "en", "es", "et", "fa", "fi", "fr", "gu",
		"he", "hi", "hr", "hu", "id", "it", "ja", "kn", "ko", "lt", "lv", "mk", "ml", "mr", "ne",
		"nl", "no", "pa", "pl", "pt", "ro", "ru", "sk", "sl", /*"so",*/ "sq", "sv", /*"sw",*/ "ta", "te",
		"th", "tl", "tr", "uk", "ur", "vi", "zh-cn", "zh-tw"
	};

	public static synchronized void loadData() throws IOException, LangDetectException {
		if (loaded)
			return;
		loaded = true;
		Charset encoding = Charset.forName("UTF-8");
	    List<String> profileData = new ArrayList<String>();
		for (String language : languages) {
			InputStream stream = LangDetectLanguageIdentifier.class.getResourceAsStream("langdetect-profiles/" + language);
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream, encoding));
			profileData.add(new String(IOUtils.toCharArray(reader)));
			reader.close();
		}
		DetectorFactory.loadProfile(profileData);
	}

	public Text evaluate(Text input) throws IOException, LangDetectException {

		if(input==null) return null;
		loadData();

		try {
			Detector detector = DetectorFactory.create();
			detector.append(Parser.parse(input.toString(),"").text());
			return new Text(detector.detect());
		} catch(Exception e) {
			return null;
		}
	}
}
