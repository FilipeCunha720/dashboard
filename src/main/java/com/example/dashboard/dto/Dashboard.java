package com.example.dashboard.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dashboard {

    private int id;

    private String title;

    private String description;

    private Timestamp createdAt;

    private Timestamp updatedAt;
}
