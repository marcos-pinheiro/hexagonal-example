package org.marking.lab.infrastructure.database.transaction;

import javax.interceptor.InvocationContext;

public class TransactionInterceptor {
	
	public Object around(InvocationContext context) throws Exception {
		TransactionType transactionType = context.getMethod().getAnnotation(Transactional.class).type();
		if(transactionType == TransactionType.NOT_REQUIRED)
			return context.proceed();
		
		TransactionConfigurationPool.push(transactionType);
		return context.proceed();
	}
}
