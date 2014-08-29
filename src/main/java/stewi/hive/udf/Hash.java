package stewi.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

public class Hash extends UDF {

    public Text evaluate(Text input) {
		if(input==null) return null;

		int hash=0;

		for(char c: input.toString().toCharArray()) {
			hash = 37 * hash + c;
		}

		return new Text(Integer.toHexString(hash));
	}
}
