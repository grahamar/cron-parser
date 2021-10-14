cron-parser
===========

[![Join the chat at https://gitter.im/grahamar/cron-parser](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/grahamar/cron-parser?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)
[![Build Status](https://travis-ci.org/grahamar/cron-parser.png?branch=master)](https://travis-ci.org/grahamar/cron-parser)

A Java library that converts cron expressions into human readable strings.
Translated to Java from https://github.com/bradyholt/cron-expression-descriptor.

Original Author & Credit: Brady Holt (http://www.geekytidbits.com)
License: MIT

**Features**

 * Supports all cron expression special characters including * / , - ? L W, #.
 * Supports 5, 6 (w/ seconds or year), or 7 (w/ seconds and year) part cron expressions.
 * Provides casing options (Sentence, Title, Lower, etc.).
 * Support for non-standard non-zero-based week day numbers.
 * Supports printing to locale specific human readable format (Italian, English, Spanish, Romanian, Dutch, French and Chinese【中文】 so far...).

**Download**

cron-parser is available in the [maven central repository](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22net.redhogs.cronparser%22), please select the latest version from there.

**Usage Examples (Unit Tests)**

See [CronExpressionDescriptorTest](/cron-parser-core/src/test/java/net/redhogs/cronparser/CronExpressionDescriptorTest.java)
