package cqrs.control.event.repository.event;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cqrs.control.event.data.ControlEventData;

@Repository
public class ControlEventGateway {
  final String CONTROLEVENT_NAMESPACE = "cqrs.device.control.repository.event.ControlEventRepository.";

  @Autowired
  private SqlSessionTemplate sqlSession;

  protected void printQueryId(String query, Object params) {
    System.out.println("ControlEventRepository.printQuery() : " + query);
    System.out.println("ControlEventRepository.printObject : " + params.toString());
  }

  public Object save(String query, Object params) {
    query = CONTROLEVENT_NAMESPACE + query;
    printQueryId(query, params);

    int i = sqlSession.insert(query, (ControlEventData) params);
    System.out.println("ControlEventGateway.save() : " + ((i > 0) ? "success" : "fail"));

    return i;
  }

  public List<ControlEventData> findWithIdentifierAfterVersion(String table, String identifier, Long version) {
    String query = CONTROLEVENT_NAMESPACE + "findwithidentifierafterversion";

    HashMap<String, String> params = new HashMap<String, String>();
    params.put("identifier", identifier);
    params.put("version", Long.toString(version));
    params.put("table", table);

    List<ControlEventData> deviceControlResult = sqlSession.selectList(query, params);

    return deviceControlResult;
  }

  public List<ControlEventData> findWithIdentifier(String table, String identifier) {
    String query = CONTROLEVENT_NAMESPACE + "findcontrolevent";

    HashMap<String, String> params = new HashMap<String, String>();
    params.put("identifier", identifier);
    params.put("table", table);

    List<ControlEventData> controlResult = sqlSession.selectList(query, params);

    return controlResult;
  }
}