package edu.sjsu.cmpe282.mapreduce.hadoop.sort;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class URIFreqReducer extends Reducer<LongWritable, Text, Text, LongWritable> {

    @Override
    public void reduce(LongWritable freq, Iterable<Text> uris, Context context)
        throws IOException, InterruptedException {

        for (Text uri : uris) {
            context.write(uri, freq);
        }
    }
}
