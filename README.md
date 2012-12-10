cron-parser
===========

A Java library that converts cron expressions into human readable strings.  
Translated to Java from https://github.com/bradyholt/cron-expression-descriptor.  
  
Original Author: Brady Holt (http://www.geekytidbits.com)  
License: MIT  
  
**Features**          

 * Supports all cron expression special characters including * / , - ? L W, #.
 * Supports 5 or 6 (w/ seconds) part cron expressions.  Does NOT support Year in cron expression.
 * Provides casing options (Sentence, Title, Lower, etc.)
 

**Download**

If you want to get up and running quickly and just want the library, [visit the Downloads page](https://github.com/grahamar/cron-parser/downloads) and download the latest JAR library.

**Usage Examples (Unit Tests)**  
  
See src/test/java/com/gr/cronparser/CronExpressionDescriptorTest.java