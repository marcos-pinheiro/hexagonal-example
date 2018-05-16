package org.marking.lab.infrastructure.command;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.inject.Singleton;

import org.marking.lab.application.Command;
import org.marking.lab.application.CommandHandler;

@Singleton
public class Registry {

  private Map<Class<? extends Command>, CommandHandler> providerMap = new HashMap<>();

  void register(Class<? extends Command> commandClass, CommandHandler<?, ?> handler){
    providerMap.put(commandClass, handler);
  }

  @SuppressWarnings("unchecked")
  <R, C extends Command<R>> Optional<CommandHandler<R, C>> get(Class<C> commandClass) {
    return Optional.ofNullable(providerMap.get(commandClass));
  }

}
