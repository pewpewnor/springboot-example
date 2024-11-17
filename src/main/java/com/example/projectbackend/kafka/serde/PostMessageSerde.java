package com.example.projectbackend.kafka.serde;

import com.example.projectbackend.kafka.model.PostMessage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import org.apache.kafka.common.serialization.Deserializer;
import org.apache.kafka.common.serialization.Serializer;

/*
  Notes:
  - NOT USED, USE JSON SERIALIZER AND JSON DESERIALIZER FROM KAFKA LIBRARY INSTEAD
  - PostMessage also needs to implement Serializable in order for this to work
*/

public class PostMessageSerde implements Serializer<PostMessage>, Deserializer<PostMessage> {
  @Override
  public void configure(Map<String, ?> configs, boolean isKey) {
    // needed for this class, but no additional configuration needed
  }

  @Override
  public byte[] serialize(String topic, PostMessage data) {
    try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
      objectOutputStream.writeObject(data);
      return byteArrayOutputStream.toByteArray();
    } catch (IOException e) {
      throw new RuntimeException(
          "Error serializing object during Kafka message object serialization", e);
    }
  }

  @Override
  public PostMessage deserialize(String topic, byte[] data) {
    try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(data);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream)) {
      return (PostMessage) objectInputStream.readObject();
    } catch (IOException | ClassNotFoundException e) {
      throw new RuntimeException(
          "Error deserializing object during Kafka message object deserialization", e);
    }
  }

  @Override
  public void close() {
    // needed for this class, but no resource to close
  }
}
