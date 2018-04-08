package com.swpu.jobanalysissystem.SpiderRefactor;

import com.swpu.jobanalysissystem.SpiderRefactor.xml.getProprety;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;



public class Main {
	public void run(SpiderConfig spiderConfig) {
		try {
			Task task = new Task();
			task.setPath(spiderConfig.getSavePath());
			task.setSpiderName(spiderConfig.getSpiderName());
			ScheduledExecutorService service = Executors.newScheduledThreadPool(1);
			service.scheduleAtFixedRate(task, spiderConfig.getInitialDelay(), spiderConfig.getPeriod(), spiderConfig.getTimeUnit());

		}catch(Exception e) {
			e.printStackTrace();
		}
	}





}
