package stewi.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;
import org.tartarus.snowball.SnowballProgram;

public class SnowballStem extends UDF {
	public Text evaluate(Text input, Text name) {
		if(input==null) return null;

		SnowballProgram stemmer;
		try {
			Class<?> stemClass = Class.forName("org.tartarus.snowball.ext." + name.toString() + "Stemmer");
			stemmer = (SnowballProgram) stemClass.newInstance();
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
		stemmer.setCurrent(input.toString());
		stemmer.stem();
		String stem = stemmer.getCurrent();

		return new Text(stem);
	}
}
