package edu.sjsu.cmpe282.mapreduce.hadoop;

import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class URICount extends Configured implements Tool {

    public int run(String[] args) throws Exception {
        if (args.length != 2) {
            System.out.println("Usage: URICount <input dir> <output dir>");
            System.exit(-1);
        }

        Job job = new Job(getConf());
        job.setJarByClass(URICount.class);
        job.setJobName("URICount");

        FileInputFormat.addInputPath(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));

        job.setMapperClass(URICountMapper.class);
        job.setCombinerClass(URISumReducer.class);
        job.setReducerClass(URISumReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        return (job.waitForCompletion(true) ? 0 : 1);
    }

    public static void main(String[] args) throws Exception {
        int exitCode = ToolRunner.run(new URICount(), args);
        System.exit(exitCode);
    }
}
