package com.maia_business_solutions;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class StopWatch
{
  private final Date startTime = new Date();
  private long elapsed = 0;

  private StopWatch()
  {
  }

  public static StopWatch start()
  {
    return new StopWatch();
  }

  public StopWatch stop()
  {
    elapsed = new Date().getTime() - startTime.getTime();
    
    return this;
  }
  
  public long elapsed()
  {
    long retVal = elapsed;
    
    if (retVal == 0)
      retVal = new Date().getTime() - startTime.getTime();
    
    return retVal;
  }

  public String elapsed(final TimeUnit timeUnit)
  {
    return Long.toString(timeUnit.convert(elapsed(), TimeUnit.MILLISECONDS));
  }
}