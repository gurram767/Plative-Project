package scheduler;

import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class MyApp
{
	public static void main(String[] args)
	{
		try
		{
			// code to check whether job is active or not in database
			JobDetail job1 = JobBuilder.newJob(ScheduleTask.class).withIdentity("job1", "group1").build();

			Trigger trigger1 = TriggerBuilder.newTrigger().withIdentity("cronTrigger1", "group1")
					.withSchedule(CronScheduleBuilder.cronSchedule(new CronExpression("0 0/2 * * * ?"))).build();

			Scheduler scheduler1 = new StdSchedulerFactory().getScheduler();
			scheduler1.start();
			scheduler1.scheduleJob(job1, trigger1);

			Thread.sleep(3000);

			// scheduler1.shutdown();

		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}