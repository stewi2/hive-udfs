package com.zendesk.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;
import org.jsoup.Jsoup;
import org.jsoup.parser.Parser;
import org.jsoup.safety.Cleaner;
import org.jsoup.safety.Whitelist;

public class StripHtml extends UDF {
	public Text evaluate(Text input) {
		if(input==null) return null;
		return new Text(Parser.parse(input.toString(),"").text());
	}
}
