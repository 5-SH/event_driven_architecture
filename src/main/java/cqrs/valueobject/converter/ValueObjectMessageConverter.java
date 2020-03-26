package cqrs.valueobject.converter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.charset.Charset;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import cqrs.util.json.JsonHelper;
import cqrs.valueobject.ValueObject;

public class ValueObjectMessageConverter extends AbstractHttpMessageConverter<ValueObject> {

  public ValueObjectMessageConverter() {
    super(new MediaType("application", "json", Charset.forName("UTF-8")));
  }

  @Override
  protected boolean supports(Class<?> clazz) {
    return ValueObject.class.isAssignableFrom(clazz);
  }

  @Override
  protected ValueObject readInternal(Class<? extends ValueObject> clazz, HttpInputMessage inputMessage)
      throws IOException, HttpMessageNotReadableException {

    String body = getBodyMessage(inputMessage.getBody());

    return JsonHelper.parseJson2ValueObject(body);

  }

  @Override
  protected void writeInternal(ValueObject t, HttpOutputMessage outputMessage)
      throws IOException, HttpMessageNotWritableException {

    Writer writer = null;
    writer = new OutputStreamWriter(outputMessage.getBody(), "UTF-8");

    String body = JsonHelper.parseValueObject2Json(t);

    writer.write(body);

    writer.flush();

  }

  private String getBodyMessage(InputStream is) throws IOException {

    char[] buffer = new char[1024];
    Writer writer = new StringWriter();
    Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

    int n = 0;
    while ((n = reader.read(buffer)) != -1) {
      writer.write(buffer, 0, n);
    }

    is.close();

    return writer.toString();

  }

}
