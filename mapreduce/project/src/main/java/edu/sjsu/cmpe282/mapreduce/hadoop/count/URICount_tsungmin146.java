package edu.sjsu.cmpe282.mapreduce.hadoop.count;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class URICount_tsungmin146 extends Configured implements Tool {

    private static class URICountMapper extends Mapper<LongWritable, Text, Text, LongWritable> {

        static final String URI_RECORD_PATTERN_STR = "(.*) - - .*";
        static final Pattern URI_RECORD_PATTERN = Pattern.compile(URI_RECORD_PATTERN_STR);
        static final LongWritable ONE = new LongWritable(1);

        @Override
        public void map(LongWritable key, Text uri_record, Context context)
            throws IOException, InterruptedException {

            // extract URI from text line record mapped <K, V> : <URI, COUNT>
            String record = uri_record.toString();
            Matcher uri_matcher = URI_RECORD_PATTERN.matcher(record);
            if (uri_matcher.find()) {
                context.write(new Text(uri_matcher.group(1)), ONE);
            } else {
                throw new IOException("URI regular expression extract failed on record: " + record);
            }
        }
    }

    private static class URICountReducer extends Reducer<Text, LongWritable, Text, LongWritable> {

        public void reduce(Text uri, Iterable<LongWritable> uri_count, Context context)
            throws IOException, InterruptedException {
            // sum up URI count
            long uri_count_sum = 0;
            for (LongWritable val : uri_count) {
                uri_count_sum += val.get();
            }
            context.write(uri, new LongWritable(uri_count_sum));
        }
    }

    public int run(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("Usage: URICount <input dir> <output dir>");
            System.exit(-1);
        }

        Job job = new Job(getConf());
        job.setJarByClass(URICount_tsungmin146.class);
        job.setJobName("URICount");

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(URICountMapper.class);
        job.setCombinerClass(URICountReducer.class);
        job.setReducerClass(URICountReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        return (job.waitForCompletion(true) ? 0 : 1);
    }

    public static void main(String[] args) throws Exception {
        int exit_code = ToolRunner.run(new URICount_tsungmin146(), args);
        System.exit(exit_code);
    }
}
