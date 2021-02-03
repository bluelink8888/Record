package com.github.bluelink.restapi.model.serialize;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Setter @Getter @NoArgsConstructor @EqualsAndHashCode
public class SerializableChild extends SerializableParent implements Serializable {

  private String description;

  private Integer number;

}
