package stewi.hive.udf;

import org.apache.hadoop.hive.ql.exec.Description;
import org.apache.hadoop.hive.ql.exec.UDF;
import java.net.URLDecoder;

@Description(
   name = "url_decode",
   value = "_FUNC_(url) - Decodes a x-www-form-urlencoded string"
)

public final class URLDecode extends UDF {

   public String evaluate(final String s) {
       if (s == null) return null;
       return getString(s);
   }

   public static String getString(String s) {
       try {
           return URLDecoder.decode(s, "UTF-8");
       } catch ( Exception e) {
           return "";
       }
   }

   public static void main(String args[]) {
       String t = "%E5%A4%AA%E5%8E%9F-%E4%B8%89%E4%BA%9A";
       System.out.println( getString(t) );
   }

}
