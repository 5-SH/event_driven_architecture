package cqrs.control.event.repository.domain;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import cqrs.control.domain.DeviceStatus;

public class DeviceControlDomainRepository {
  final String DEVICECONTROLDOMAIN_NAMPESPACE = "cqrs.device.control.repository.domain.DeviceControlDomainRepository.";

  @Autowired
  private SqlSessionTemplate sqlSession;

  protected void printQueryId(String queryId, Object params) {
    System.out.println("DeviceControlDomainRepository.printQueryId() : " + queryId);
    System.out.println("DeviceControlDomainRepository.printObject : " + params.toString());
  }

  public Object save(String queryId, Object params) {
    queryId = DEVICECONTROLDOMAIN_NAMPESPACE + queryId;

    int i = 0;
    try {
      i = sqlSession.insert(queryId, (DeviceStatus) params);
      System.out.println("DeviceControlDomainRepository.save() : " + ((i > 0) ? "success" : "fail"));
    } catch (Exception e) {
      System.out.println("DeviceControlDomainRepository.save() Exception : " + e.getMessage());
      for (String msg : ExceptionUtils.getRootCauseStackTrace(e)) {
        System.out.println(msg);
      }
    }
    return i;
  }
}