package com.ntaxeidis.blog.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ErrorMessage {
  private int status;
  private Date date;
  private String message;
  private String description;
}
