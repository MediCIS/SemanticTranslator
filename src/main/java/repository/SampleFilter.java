package repository;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class SampleFilter extends Filter<ILoggingEvent> {

	@Override
	public FilterReply decide(ILoggingEvent event) {    		
		if (event.getLoggerName().contains("repository")) {
			return FilterReply.ACCEPT;
		} else if (event.getMessage().contains("Could not complete request") || event.getMessage().contains("Exception")) {
			if (Application.ontologyLoaded) {
				return FilterReply.ACCEPT;
			} else {
				return FilterReply.DENY;
			}
		} else {
			return FilterReply.DENY;
		}
	}
}