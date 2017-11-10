package edu.sjsu.cmpe282.mapreduce.hadoop.sort;

import java.io.IOException;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class URIFreqMapper extends Mapper<Text, Text, LongWritable, Text> {

    @Override
    public void map(Text uri, Text uri_freq, Context context)
        throws IOException, InterruptedException {

        long freq = Long.parseLong(uri_freq.toString());
        context.write(new LongWritable(freq), uri);
    }
}
