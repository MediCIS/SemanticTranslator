package repository;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class SampleFilter extends Filter<ILoggingEvent> {
	// Filter for logs (extends the provided class)

	@Override	// Replace the built in function
	public FilterReply decide(ILoggingEvent event) {    	// Decide if a log is logged or not		
		if (event.getLoggerName().contains("repository")) { // if log come from Repository
			return FilterReply.ACCEPT;						// Logs is logged
		} else if (event.getMessage().contains("Exception") // Use to kill logs during logs during ontology loading...
				|| event.getMessage().contains("Could not complete request")) {
			if (Application.hideLogs) {
				return FilterReply.DENY;					// ...only when these boolean is on true
			} else {
				return FilterReply.ACCEPT;			
			}
		} else {
			return FilterReply.DENY;
		}
	}
}