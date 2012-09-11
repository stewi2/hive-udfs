package stewi.hive.udf;

import org.apache.commons.codec.binary.Base64;
import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

public class Base64Decode extends UDF {
	public Text evaluate(Text input) {
		if(input==null) return null;
		return new Text(Base64.decodeBase64(input.toString()));
	}
}
