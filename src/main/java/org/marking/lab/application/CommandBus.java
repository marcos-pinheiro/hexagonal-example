package org.marking.lab.application;

public interface CommandBus {
	
	<R, T extends Command<R>> R execute(T command);
	
}
