package com.jf.common.ext.aop;

import java.util.HashMap;
import java.util.Map;

/**
 * InterceptorBuilder
 * @author hdl
 */
public class InterceptorBuilder {
	
	public static final Interceptor[] NULL_INTERS = new Interceptor[0];
	private Map<Class<? extends Interceptor>, Interceptor> intersMap = new HashMap<Class<? extends Interceptor>, Interceptor>();
	

	
	/**
	 * Create Interceptors with Annotation of Before. Singleton version.
	 */
	public Interceptor[] createInterceptors(Interceptors interceptors) {
		if (interceptors == null)
			return NULL_INTERS;
		
		Class<? extends Interceptor>[] interceptorClasses = interceptors.value();
		if (interceptorClasses.length == 0)
			return NULL_INTERS;
		
		Interceptor[] result = new Interceptor[interceptorClasses.length];
		try {
			for (int i=0; i<result.length; i++) {
				result[i] = intersMap.get(interceptorClasses[i]);
				if (result[i] == null) {
					result[i] = (Interceptor)interceptorClasses[i].newInstance();
					intersMap.put(interceptorClasses[i], result[i]);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return result;
	}
}





