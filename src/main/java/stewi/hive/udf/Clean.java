package stewi.hive.udf;

import java.util.regex.Pattern;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

public class Clean extends UDF {
	
	Pattern PAT = Pattern.compile("\\p{Zs}+");
	
	public Text evaluate(Text input) {
		if(input==null) return null;
		return new Text(PAT.matcher(input.toString()).replaceAll(" ").trim());
	}
}
