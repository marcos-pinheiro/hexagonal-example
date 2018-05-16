package org.marking.lab.infrastructure.config;

public abstract class ConfigurationInitializer implements Initializer {
	
	public Integer priority() {
		return 2;
	}
}
