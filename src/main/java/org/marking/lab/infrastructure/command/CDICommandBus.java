package org.marking.lab.infrastructure.command;

import java.util.Objects;
import java.util.Optional;

import org.marking.lab.application.Command;
import org.marking.lab.application.CommandBus;
import org.marking.lab.application.CommandHandler;

public class CDICommandBus implements CommandBus {

	private final Registry registry;

	public CDICommandBus(Registry registry) {
		this.registry = registry;
	}

	@Override
	public <R, T extends Command<R>> R execute(T command) {
		Objects.requireNonNull(command);

		return resolveHandler(command).handle(command);
	}

	@SuppressWarnings("unchecked")
	private <R, T extends Command<R>> CommandHandler<R, T> resolveHandler(T command) {
		Optional<CommandHandler<R, T>> optional = registry.get(command.getClass());
		return optional.orElseThrow(() -> new RuntimeException());
	}
}
