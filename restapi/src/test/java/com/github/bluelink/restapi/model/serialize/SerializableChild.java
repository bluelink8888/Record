package com.github.bluelink.restapi.model.serialize;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter @NoArgsConstructor
public class SerializableChild extends SerializableParent implements Serializable {

  private String description;

  private Integer number;

}
