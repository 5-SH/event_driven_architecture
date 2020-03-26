package cqrs.core.projector;

import java.lang.reflect.Method;

import org.springframework.stereotype.Component;

import cqrs.core.event.Event;

// 생성된 Events 를 Query Model 전용 DB로 Projection
@Component
public abstract class AbstractEventProjector implements EventProjector {
  protected static String APPLY_METHOD_NAME = "execute";

  @Override
  public void handle(Event event) {
    System.out.println("AbstractEventProjector.handle()");
    Method method;
    try {
      method = this.getClass().getDeclaredMethod(APPLY_METHOD_NAME, event.getClass());
      if (method != null) {
        method.setAccessible(true);
        method.invoke(this, event);
      }
    } catch (Exception e) {
      // throw Exception
    }
  }
}