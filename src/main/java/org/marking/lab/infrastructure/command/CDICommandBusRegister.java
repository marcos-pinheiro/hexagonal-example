package org.marking.lab.infrastructure.command;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AfterDeploymentValidation;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.ProcessInjectionTarget;

import org.jboss.weld.environment.se.WeldContainer;
import org.marking.lab.application.Command;
import org.marking.lab.application.CommandHandler;

public class CDICommandBusRegister {

	private final Registry registry = new Registry();
	private Map<Class<? extends Command>, Class<? extends CommandHandler<?,?>>> commandHandlers = new HashMap<>();

	  /**
	   * Captures all command handlers.
	   *
	   * @param target cdi event
	   * @param <H> handler type
	   */
	  public <H extends CommandHandler<?, ?>> void captureCommandHandlers(@Observes ProcessInjectionTarget<H> target) {
	    Class<H> handler = target.getAnnotatedType().getJavaClass();
	    for ( Type type : target.getAnnotatedType().getTypeClosure() ) {
	      if (type instanceof ParameterizedType) {
	        ParameterizedType parameterizedType = (ParameterizedType) type;
	        Type genericParameterType = parameterizedType.getActualTypeArguments()[1];
	        if (genericParameterType instanceof Class) {
	          register((Class<? extends Command<?>>) genericParameterType, handler);
	        }
	      }
	    }
	  }

	  /**
	   * Registers all captured handlers on the {@link Registry}.
	   * @param event cdi event
	   * @param beanManager cdi bean manager
	   */
	  public void register(@Observes AfterDeploymentValidation event, final BeanManager beanManager, WeldContainer container) {
	    Registry registry = getRegistry(beanManager);
	    commandHandlers.forEach((commandClass, handlerClass) -> registry.register(commandClass, container.instance().select(handlerClass).get()));
	  }

	  private void register(Class<? extends Command<?>> command, Class<? extends CommandHandler<?, ?>> handler) {
	    commandHandlers.put(command, handler);
	  }

	  private Registry getRegistry(BeanManager beanManager) {
	    Bean<Registry> registryBean = (Bean<Registry>) beanManager.getBeans(Registry.class).iterator().next();
	    CreationalContext<Registry> context = beanManager.createCreationalContext(registryBean);
	    return (Registry) beanManager.getReference(registryBean, Registry.class, context);
	  }

	Registry registry() {
		return registry;
	}
}
