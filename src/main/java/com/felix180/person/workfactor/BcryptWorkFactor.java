package com.felix180.person.workfactor;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
class BcryptWorkFactor {

  private int strength;
  private long duration;
}
