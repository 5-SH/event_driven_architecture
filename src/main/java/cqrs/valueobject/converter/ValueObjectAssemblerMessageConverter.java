package cqrs.valueobject.converter;

import java.io.IOException;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import cqrs.valueobject.ValueObjectAssembler;

public class ValueObjectAssemblerMessageConverter extends AbstractHttpMessageConverter<ValueObjectAssembler>{

	@Override
	protected boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected ValueObjectAssembler readInternal(Class<? extends ValueObjectAssembler> clazz,
			HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void writeInternal(ValueObjectAssembler t, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		// TODO Auto-generated method stub
		
	}

}
