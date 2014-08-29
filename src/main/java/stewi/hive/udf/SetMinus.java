package stewi.hive.udf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

public class SetMinus extends UDF {
    public ArrayList<Text> evaluate(final ArrayList<Text> a, final ArrayList<Text> b)  {
        Set<Text> set = new HashSet<Text>();
        set.addAll(a);
        set.removeAll(b);
        return new ArrayList<Text>(set);
    }
}
