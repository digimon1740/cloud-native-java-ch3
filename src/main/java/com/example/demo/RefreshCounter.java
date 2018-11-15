package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.context.scope.refresh.RefreshScopeRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class RefreshCounter {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private final AtomicLong counter = new AtomicLong(0);

	@EventListener
	public void refresh(RefreshScopeRefreshedEvent e) {
		this.logger.info("The refresh count is now at : {}", this.counter.incrementAndGet());
	}
}
