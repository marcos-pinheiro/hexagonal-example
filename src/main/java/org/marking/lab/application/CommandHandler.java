package org.marking.lab.application;

public interface CommandHandler<R, T extends Command<R>> {
	
	R handle(T command);
	
}
