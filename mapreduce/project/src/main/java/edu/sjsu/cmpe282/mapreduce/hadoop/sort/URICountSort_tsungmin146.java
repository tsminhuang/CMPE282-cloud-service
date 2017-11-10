package edu.sjsu.cmpe282.mapreduce.hadoop.sort;

import java.io.IOException;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class URICountSort_tsungmin146 extends Configured implements Tool {

    private static class URICountSortMapper extends Mapper<Text, Text, LongWritable, Text> {

        @Override
        public void map(Text uri, Text uri_freq, Context context)
            throws IOException, InterruptedException {
            // Swap <Key: URI, Value: COUNT> to <Key: COUNT, Value: URI>
            // Let sort and shuffle phase to handle to Key sort
            long freq = Long.parseLong(uri_freq.toString());
            context.write(new LongWritable(freq), uri);
        }
    }

    private static class URICountSortReducer extends Reducer<LongWritable, Text, Text, LongWritable> {

        @Override
        public void reduce(LongWritable count, Iterable<Text> uris, Context context)
            throws IOException, InterruptedException {
            // Swap <Key: COUNT, Value: URI> back to <Key: URI, Value: COUNT>
            for (Text uri : uris) {
                context.write(uri, count);
            }
        }
    }

    @Override
    public int run(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("Usage: URICountSort <input dir> <output dir>");
            System.exit(-1);
        }

        Job job = new Job((getConf()));
        job.setJarByClass(URICountSort_tsungmin146.class);
        job.setJobName("URICountSort");

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(URICountSortMapper.class);
        job.setReducerClass(URICountSortReducer.class);

        job.setInputFormatClass(KeyValueTextInputFormat.class);
        job.setSortComparatorClass(LongWritable.Comparator.class);

        // force map key and value type ?
        job.setMapOutputKeyClass(LongWritable.class);
        job.setMapOutputValueClass(Text.class);

        // force reduce key and value type ?
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        return (job.waitForCompletion(true) ? 0 : 1);
    }

    public static void main(String[] args) throws Exception {
        int exit_code = ToolRunner.run(new URICountSort_tsungmin146(), args);
        System.exit(exit_code);
    }
}
