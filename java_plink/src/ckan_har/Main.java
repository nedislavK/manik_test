package ckan_har;
import java.io.InputStream;
import java.io.OutputStream;

public class Main{
    public static void main(String[] args) throws InterruptedException {
//        SSH_Plink ssh = new SSH_Plink();
//        Thread shh_thread1 = new Thread(ssh);
//        shh_thread1.setPriority(1);
//        shh_thread1.start();

        Environment new_env = new Environment();
        Thread env = new Thread(new_env);
        env.setPriority(1);
        env.start();
//
        Source_Creator source_creator = new Source_Creator();
        Thread source_thread = new Thread(source_creator);
        source_thread.setPriority(2);
        source_thread.start();

            source_thread.join();

        Job_Creator job_creator = new Job_Creator();
        Thread job = new Thread(job_creator);
        job.setPriority(3);
        job.start();

            job.join();

        Gather gather = new Gather();
        Thread thread_gather = new Thread(gather);
        thread_gather.setPriority(4);
        thread_gather.start();

            thread_gather.join();

        Fetch fetch = new Fetch();
        Thread thread_fetch = new Thread(fetch);
        thread_fetch.setPriority(5);
        thread_fetch.start();

            thread_fetch.join();

        Run run = new Run();
        Thread thread_run = new Thread(run);
        thread_run.setPriority(6);
        thread_run.start();
    }
    public void start(){
    }
}
