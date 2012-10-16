package stewi.hive.udf;

import static org.junit.Assert.*;

import org.apache.hadoop.io.Text;
import org.junit.Test;

import stewi.hive.udf.StripHtml;

public class StripHtmlTest {
	@Test
	public void test() {
		StripHtml sh = new StripHtml();
		assertEquals("hello world &second paragraph", sh.evaluate(new Text("<script>foo bar</script> <p>hello world</p><p>&amp;second paragraph</p>")).toString());
	}

}
