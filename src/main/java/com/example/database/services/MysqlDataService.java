package com.example.database.services;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Service;

import com.example.models.Message;

@Service
public class MysqlDataService {

  @Autowired
  @Qualifier("db1JdbcTemplate")
  private NamedParameterJdbcTemplate jdbcTemplate;

  public int saveMessage(Message message) {
    final String query = "insert into message(message_id, text, datetime) "
        + "values (:messageId, :text, :datetime)";
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("messageId", message.getMessageId());
    parameters.put("text", message.getText());
    parameters.put("datetime", message.getDateTime());
    return jdbcTemplate.update(query, parameters);

  }

  public Message getMessage(String messageId) {
    final String query = "select message_id, text, datetime from message "
        + "where message_id = :messageId";
    Map<String, Object> parameters = new HashMap<>();
    parameters.put("messageId", messageId);
    return jdbcTemplate.queryForObject(query, parameters,
        (rs, rowNum) -> new Message(rs.getString("message_id"),
            rs.getString("text"),
            rs.getTimestamp("datetime").toLocalDateTime()));
  }
}
