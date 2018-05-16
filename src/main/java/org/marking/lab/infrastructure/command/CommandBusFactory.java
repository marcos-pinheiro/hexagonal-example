package org.marking.lab.infrastructure.command;

import org.marking.lab.application.CommandBus;

public class CommandBusFactory {
	
	public static final CommandBus create(Registry registry) {
		return new CDICommandBus(registry);
	}
}
