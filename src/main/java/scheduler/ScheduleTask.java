package scheduler;

import java.io.IOException;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class ScheduleTask implements Job
{
	public void execute(JobExecutionContext context) throws JobExecutionException
	{
		try
		{
			System.out.println("Job1 --->>> Time is " + new Date());
			String exeBatchFilePath = "D:\\POCs\\VtigerDemo\\Run.bat";
			System.out.println("exeBatchFilePath" + exeBatchFilePath);
			exeBatchFilePath = exeBatchFilePath.replaceAll("\\\\", "/");
			Runtime.getRuntime().exec("cmd.exe /c start " + exeBatchFilePath);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}