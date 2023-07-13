package com.example.webfluxmongodb.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document
public record Event(@Id String id, @Indexed LocalDate date, String message) {
}
