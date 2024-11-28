package com.example.webcsdl.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ScholarshipDto {
    public Long id;
    public String scholarshipName;
    public BigDecimal amount;
    public Long studentId;
}
