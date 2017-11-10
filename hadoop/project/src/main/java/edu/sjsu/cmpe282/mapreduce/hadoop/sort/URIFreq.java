package edu.sjsu.cmpe282.mapreduce.hadoop.sort;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class URIFreq extends Configured implements Tool {

    @Override
    public int run(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("Usage: URIFreq <input dir> <output dir>");
            System.exit(-1);
        }

        Job job = new Job((getConf()));
        job.setJarByClass(URIFreq.class);
        job.setJobName("URIFreq");

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(URIFreqMapper.class);
        job.setReducerClass(URIFreqReducer.class);

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
        int exit_code = ToolRunner.run(new URIFreq(), args);
        System.exit(exit_code);
    }
}
