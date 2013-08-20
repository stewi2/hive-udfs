package stewi.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDFMethodResolver;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;

@Description(
    name = "pursway_row_number",
    value = "_FUNC_(partitionByKey1, partitionByKey2 ... ) - assigns a unique number to each row to which it is applied, starting from 1"
)
   
public class RowNumber extends UDF {
    
    private final LongWritable count = new LongWritable();   
    private final StringBuilder partitionByKeyArgs = new StringBuilder();    
    private final StringBuilder keepPartitionByKeys = new StringBuilder();

    private static final String NULL = "NULL";
    private static final String SPLIT = ";";

    public RowNumber() { count.set(0); }

    public RowNumber(UDFMethodResolver rslv) { super(rslv); }

    public LongWritable evaluate(Text... args) {
        
    	partitionByKeyArgs.setLength(0);
        
        for (int i=0; i< args.length; i++) {
            String arg = args[i].toString();
            if (arg == null) partitionByKeyArgs.append(NULL);
            else partitionByKeyArgs.append(arg);
            partitionByKeyArgs.append(SPLIT);
        }
        
        if (! keepPartitionByKeys.toString().equals(partitionByKeyArgs.toString())) {
            keepPartitionByKeys.setLength(0);
            keepPartitionByKeys.append(partitionByKeyArgs);
            count.set(0);
        }
        
        count.set(count.get() + 1);
        return count;
    }
}
