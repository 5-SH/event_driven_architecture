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
import cqrs.valueobject.ValueObjectAssembler;

public class ValueObjectAssemblerMessageConverter extends AbstractHttpMessageConverter<ValueObjectAssembler> {

  public ValueObjectAssemblerMessageConverter() {
    super(new MediaType("application", "json", Charset.forName("UTF-8")));
  }

  @Override
  protected boolean supports(Class<?> clazz) {
    return ValueObjectAssembler.class.isAssignableFrom(clazz);
  }

  @Override
  protected ValueObjectAssembler readInternal(Class<? extends ValueObjectAssembler> clazz,
      HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {

    String body = getBodyMessage(inputMessage.getBody());

    if (logger.isDebugEnabled()) {
      logger.debug(">>>>>>>>>>>>>>>>>>>>>>>>>>>> REQUEST");
      logger.debug(body);
    }

    return JsonHelper.parseJson2ValueObjectAssembler(body);

  }

  @Override
  protected void writeInternal(ValueObjectAssembler t, HttpOutputMessage outputMessage)
      throws IOException, HttpMessageNotWritableException {

    Writer writer = null;
    writer = new OutputStreamWriter(outputMessage.getBody(), "UTF-8");

    String body = JsonHelper.parseValueObjectAssembler2Json(t);

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
