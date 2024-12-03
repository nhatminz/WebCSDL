package com.example.webcsdl.Entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public final class MajorDto {
    public Long id;
    public String majorName;
    public String description;
    public Long departmentId;
    public String departmentName;
}
