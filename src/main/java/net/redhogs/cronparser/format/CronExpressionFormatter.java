package net.redhogs.cronparser.format;

import java.text.ParseException;
import java.util.Locale;

import net.redhogs.cronparser.CronExpressionDescriptor;
import net.redhogs.cronparser.Options;

import org.quartz.CronExpression;
import org.springframework.format.Formatter;

/**
 * A Spring formatter that can be used to format a Quartz cron expression.
 * 
 * <p>It can be used like</p>
 * 
 * <blockquote><code>
 * 	&lt;bean id="conversionService"
 *		class="org.springframework.format.support.FormattingConversionServiceFactoryBean"&gt;
 *		&lt;property name="formatters"&gt;
 *			&lt;set&gt;
 *				&lt;bean class="net.redhogs.cronparser.format.CronExpressionFormatter"/&gt;
 *			&lt;/set&gt;
 *		&lt;/property&gt;
 * &lt;/bean&gt;
 * </pre></code></blockquote>
 *
 * In ThymeLeaf, a cron expression can be displayed as <code>${{cronExpression}}</code>.
 */
public class CronExpressionFormatter implements Formatter<CronExpression> {

	private Options options;
	
	public CronExpressionFormatter() {
	}
	
	public CronExpressionFormatter(Options options) {
		this.options = options;
	}

	@Override
	public String print(CronExpression cron, Locale locale) {
		String cronExpression = cron.getCronExpression();
		try {
			return options == null ? CronExpressionDescriptor.getDescription(cronExpression, locale)
					: CronExpressionDescriptor.getDescription(cronExpression, options, locale);
		} catch (ParseException e) {
			return cronExpression;
		}
	}

	@Override
	public CronExpression parse(String text, Locale locale)
			throws ParseException {
		throw new UnsupportedOperationException("Parsing cron human readable string is not implemeted.");
	}

}
