package stewi;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;
import org.apache.tika.language.LanguageIdentifier;
import org.jsoup.Jsoup;
import org.jsoup.parser.Parser;
import org.jsoup.safety.Whitelist;

public class TikaLanguageIdentifier extends UDF {

	public Text evaluate(Text input) {
		if(input==null) return null;
		LanguageIdentifier identifier = new LanguageIdentifier(Parser.parse(input.toString(),"").text());
		return new Text(identifier.getLanguage());
	}

}
