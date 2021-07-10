package com.felix180.person.exception;

import java.util.function.Supplier;

public class NoSuchElementFoundException extends RuntimeException
    implements Supplier<NoSuchElementFoundException> {

  public NoSuchElementFoundException(String message) {
    super(message);
  }

  /**
   * Gets a result.
   *
   * @return a result
   */
  @Override
  public NoSuchElementFoundException get() {
    return this;
  }
}
