package com.elysantos.desafiosouth.model.domain;

public enum AssociadoEnabled {
  ABLE_TO_VOTE("ABLE_TO_VOTE"),
  UNABLE_TO_VOTE("UNABLE_TO_VOTE");
  private final String status;

  AssociadoEnabled(String status) {
    this.status = status;
  }


  public static AssociadoEnabled fromStatus(String status) {
    for (AssociadoEnabled a : AssociadoEnabled.values()) {
      if (a.status.equals(status)) {
        return a;
      }
    }
    return null;
  }
}
