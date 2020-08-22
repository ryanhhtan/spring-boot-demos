package com.example.springdatajdbcjooqdemo.domain.user;

import org.springframework.data.relational.core.mapping.Table;
import lombok.Data;

/**
 * Address
 */
@Data
@Table("addresses")
public class Address {
  private String line1;
  private String line2;
  private String city;
  private String province;
  private String postalCode;
}
