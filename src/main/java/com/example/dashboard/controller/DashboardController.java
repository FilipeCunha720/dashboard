package com.example.dashboard.controller;

import com.example.dashboard.dto.Dashboard;
import com.example.dashboard.repository.DashboardRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Transactional
@RequestMapping("/dashboards")
public class DashboardController {

    private final DashboardRepository dashboardRepository;
    private final ObjectMapper mapper;

    @Autowired
    public DashboardController(DashboardRepository dashboardRepository, ObjectMapper mapper) {
        this.dashboardRepository = dashboardRepository;
        this.mapper = mapper;
    }

    @GetMapping
    public List<Dashboard> findAllDashboards() {
        return dashboardRepository.findAll().stream()
                .map(dash -> mapper.convertValue(dash,Dashboard.class))
                .collect(Collectors.toList());
    }
}
