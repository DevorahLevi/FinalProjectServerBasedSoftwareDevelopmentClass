package com.example.FinalProject.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Person
{
    private String name;
    private int age;
}
