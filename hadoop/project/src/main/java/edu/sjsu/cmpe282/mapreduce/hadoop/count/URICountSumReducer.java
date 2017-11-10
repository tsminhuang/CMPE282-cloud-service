package edu.sjsu.cmpe282.mapreduce.hadoop.count;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class URICountSumReducer extends Reducer<Text, LongWritable, Text, LongWritable> {

    public void reduce(Text uri, Iterable<LongWritable> uri_count, Context context)
        throws IOException, InterruptedException {
        long uri_count_sum = 0;
        for (LongWritable val : uri_count) {
            uri_count_sum += val.get();
        }
        context.write(uri, new LongWritable(uri_count_sum));
    }
}
