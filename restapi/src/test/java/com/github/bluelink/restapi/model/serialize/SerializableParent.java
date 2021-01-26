package com.github.bluelink.restapi.model.serialize;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter
public class SerializableParent implements Serializable {

  private String id;

  private String name;
}
