[![Stories in Ready](https://badge.waffle.io/RedHogs/cron-parser.png?label=ready)](https://waffle.io/RedHogs/cron-parser)  
cron-parser
===========

[![Build Status](https://travis-ci.org/RedHogs/cron-parser.png?branch=master)](https://travis-ci.org/RedHogs/cron-parser)

A Java library that converts cron expressions into human readable strings.  
Translated to Java from https://github.com/bradyholt/cron-expression-descriptor.  
  
Original Author & Credit: Brady Holt (http://www.geekytidbits.com)  
License: MIT  
  
**Features**          

 * Supports all cron expression special characters including * / , - ? L W, #.
 * Supports 5 or 6 (w/ seconds) part cron expressions.  Does NOT support Year in cron expression.
 * Provides casing options (Sentence, Title, Lower, etc.)
 * Supports printing to locale specific human readable format (Italian & English so far...).

**Download**

cron-parser is available in the [maven central repository](http://search.maven.org/#browse|987144470), please select the latest version from there.

**Usage Examples (Unit Tests)**  
  
See src/test/java/com/gr/cronparser/CronExpressionDescriptorTest.java
