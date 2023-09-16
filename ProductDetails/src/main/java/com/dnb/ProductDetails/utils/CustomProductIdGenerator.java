package com.dnb.ProductDetails.utils;

import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.enhanced.SequenceStyleGenerator;
import org.hibernate.internal.util.config.ConfigurationHelper;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;
import org.hibernate.type.spi.TypeConfiguration;
import org.springframework.stereotype.Component;
@Component
public class CustomProductIdGenerator extends SequenceStyleGenerator {
	
	public static final String VALUE_PREFIX_PARAMETER="valuePrefix";
	public static final String VALUE_PREFIX_DEFAULT="_";
	private String valuePrefix;
	
	public static final String NUMBER_FORMAT_PARAMETER="numberFormat";
	public static final String NUMBER_FORMAT_DEFAULT="%d";
	private String numberFormat;
	
	@Override
	public Object generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
		// TODO Auto-generated method stub
		return valuePrefix + String.format( numberFormat ,super.generate(session, object));
	}
	
	@Override
	public void configure(Type type, Properties parameters, ServiceRegistry serviceRegistry) throws MappingException {
		// TODO Auto-generated method stub
		//super.configure(type, parameters, serviceRegistry);
		
		valuePrefix = ConfigurationHelper.getString(VALUE_PREFIX_PARAMETER, parameters, VALUE_PREFIX_DEFAULT);
		numberFormat = ConfigurationHelper.getString(NUMBER_FORMAT_PARAMETER, parameters,NUMBER_FORMAT_DEFAULT);
		super.configure(new TypeConfiguration().getBasicTypeRegistry().getRegisteredType(Long.class), parameters, serviceRegistry);
	}

}
