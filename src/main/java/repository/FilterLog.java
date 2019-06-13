package repository;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class FilterLog extends Filter<ILoggingEvent> {

	@Override
	public FilterReply decide(ILoggingEvent event) {   
		if (Application.hideLogs==true) {
			if (event.getLoggerName().contains("repository") || event.getLoggerName().contains("querries")) {
				return FilterReply.ACCEPT;
			} else {
				return FilterReply.DENY;
			}
		} else {
			return FilterReply.ACCEPT;
		}
		/*
		if (event.getLoggerName().contains("springfox")) {
			return FilterReply.DENY;
		} else if (event.getLoggerName().contains("org.apache.jena")) {
			return FilterReply.DENY;
		} else {
			return FilterReply.NEUTRAL;
		}*/
	}
}
