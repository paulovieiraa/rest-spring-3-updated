package br.com.pvprojects.restspring3.api.exceptionhandler;

public enum ProblemType {
  RESOURCE_NOT_FOUND("Resource not found"),
  BUSINESS_ERROR("Business rule violation"),
  INVALID_DATA("Invalid data"),
  SYSTEM_ERROR("System error");

  private String title;

  ProblemType(String title) {
    this.title = title;
  }

  public String getTitle() {
    return title;
  }
}
