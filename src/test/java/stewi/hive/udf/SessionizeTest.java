package stewi.hive.udf;

import static org.junit.Assert.*;

import org.apache.hadoop.io.Text;
import org.junit.Test;

public class SessionizeTest {

    @Test
    public void testEvaluate() {
        Sessionize sessionize =new Sessionize();
        String a = sessionize.evaluate(1000,new Text("a"),60).toString();
        String b = sessionize.evaluate(1010,new Text("a"),60).toString();
        System.out.println(a);
        assertTrue(a.equals(b));

        String c = sessionize.evaluate(1100,new Text("a"),60).toString();
        assertTrue(!b.equals(c));

        String d = sessionize.evaluate(1110,new Text("b"),60).toString();
        assertTrue(!c.equals(d));
    }

}
