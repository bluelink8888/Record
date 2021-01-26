package com.github.bluelink.restapi.model.serialize;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class NoSerializableChild extends NoSerializableParent{

  private String description;

}
