package edu.sjsu.cmpe282.mapreduce.hadoop;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class URICountMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

    private static final String URI_RECORD_PATTERN_STR = "(.*) - - .*";
    private static final Pattern URI_RECORD_PATTERN = Pattern.compile(URI_RECORD_PATTERN_STR);
    private static final LongWritable ONE = new LongWritable(1);

    @Override
    public void map(LongWritable key, Text uri_record, Context context)
        throws IOException, InterruptedException {
        String record = uri_record.toString();
        Matcher uri_matcher = URI_RECORD_PATTERN.matcher(record);

        if (uri_matcher.find()) {
            context.write(new Text(uri_matcher.group(1)), ONE);
        } else {
            throw new IOException("URI regular expression extract failed on record: " + record);
        }
    }
}