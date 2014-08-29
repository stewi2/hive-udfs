package stewi.hive.udf;

import java.util.UUID;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

public class Sessionize extends UDF {
    private Text session_uuid = new Text();
    private long last_timestamp = 0;
    private Text last_id = new Text();
    int i=0;

    public Text evaluate(long timestamp, Text id, long timeout) {

        if(timestamp-last_timestamp>timeout || !id.equals(last_id)) {
            session_uuid.set(Long.toHexString(UUID.randomUUID().getLeastSignificantBits()));
        }

        last_timestamp = timestamp;
        last_id.set(id);

        return session_uuid;
    }
}
