package stewi.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

public class LevenshteinDistance extends UDF {

	public Integer evaluate(Text input1, Text input2) {

		String term1 = input1.toString();
		String term2 = input2.toString();

		int m = term1.length();
		int n = term2.length();

		int[][] d = new int[m][n];
		for(int i=0; i<m; i++) d[i][0] = i; // the distance of any first string to an empty second string
		for(int i=0; i<n; i++) d[0][i] = i; // the distance of any first string to an empty second string

		for(int j=0; j<n; j++) {
			for(int i=0; i<m; i++) {
				if(term1.charAt(i) == term2.charAt(j)) {
					d[i][j] = d[Math.max(0,i-1)][Math.max(0,j-1)];       // no operation required
				} else {
					d[i][j] = Math.min(
							d[Math.max(0,i-1)][j] + 1,  // a deletion
							Math.min(
									d[i][Math.max(0,j-1)] + 1,  // an insertion
									d[Math.max(0,i-1)][Math.max(0,j-1)] + 1 // a substitution
									));
				}
			}
		}

		return d[m-1][n-1];
	}
}
