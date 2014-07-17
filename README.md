cron-parser
===========

[![Build Status](https://travis-ci.org/RedHogs/cron-parser.png?branch=master)](https://travis-ci.org/RedHogs/cron-parser) [![Stories in Ready](https://badge.waffle.io/RedHogs/cron-parser.png?label=ready)](https://waffle.io/RedHogs/cron-parser) [![Bitdeli Badge](https://d2weczhvl823v0.cloudfront.net/RedHogs/cron-parser/trend.png)](https://bitdeli.com/free "Bitdeli Badge")

A Java library that converts cron expressions into human readable strings.
Translated to Java from https://github.com/bradyholt/cron-expression-descriptor.

Original Author & Credit: Brady Holt (http://www.geekytidbits.com)
License: MIT

**Features**

 * Supports all cron expression special characters including * / , - ? L W, #.
 * Supports 5 or 6 (w/ seconds) part cron expressions.  Does NOT support Year in cron expression.
 * Provides casing options (Sentence, Title, Lower, etc.).
 * Support for non-standard non-zero-based week day numbers.
 * Supports printing to locale specific human readable format (Italian, English, Spanish and Dutch so far...).

**Download**

cron-parser is available in the [maven central repository](http://search.maven.org/#browse|987144470), please select the latest version from there.

**Usage Examples**

For complete examples, please view our tests: src/test/java/com/gr/cronparser/CronExpressionDescriptorTest.java

***Quick example***

    CronExpressionDescriptor descriptor =
                DescriptorParamsBuilder.createDescriptor()
                        .withLocale(ENGLISH)
                        .forCronType(CronType.QUARTZ)
                        .build();

                        String description = descriptor.getDescription("*/45 * * * * *");
                        //description will be: "Every 45 seconds"

                        description = descriptor.getDescription("5-10 * * * * *");
                        //description will be: "Seconds 5 through 10 past the minute"


                descriptor = DescriptorParamsBuilder.createDescriptor()
                        .withLocale(ENGLISH)
                        .forCronType(CronType.UNIX)
                        .build();

                        description = descriptor.getDescription("0 23 ? * MON-FRI");
                        //description will be: "At 11:00:00 PM, Monday through Friday"