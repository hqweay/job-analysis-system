package com.swpu.jobanalysissystem.SpiderRefactor;

import java.util.concurrent.TimeUnit;

public class SpiderConfig {
  private String spiderName = "five";//执行爬虫名
  private String savePath = "D:\\";//储存路径
  private int initialDelay = 0;//多久后执行
  private int period = 1;//执行周期
  private TimeUnit timeUnit = TimeUnit.DAYS;//执行时间间隔单位

  public String getSpiderName() {
    return spiderName;
  }

  public void setSpiderName(String spiderName) {
    this.spiderName = spiderName;
  }

  public String getSavePath() {
    return savePath;
  }

  public void setSavePath(String savePath) {
    this.savePath = savePath;
  }

  public int getInitialDelay() {
    return initialDelay;
  }

  public void setInitialDelay(int initialDelay) {
    this.initialDelay = initialDelay;
  }

  public int getPeriod() {
    return period;
  }

  public void setPeriod(int period) {
    this.period = period;
  }

  public TimeUnit getTimeUnit() {
    return timeUnit;
  }

  public void setTimeUnit(TimeUnit timeUnit) {
    this.timeUnit = timeUnit;
  }
}
