package com.example.dashboard.controller;

import com.example.dashboard.domain.DashboardEntity;
import com.example.dashboard.dto.Dashboard;
import com.example.dashboard.repository.DashboardRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
                .map(dash -> mapper.convertValue(dash, Dashboard.class))
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public Dashboard findDashboardById(@PathVariable Integer id) {
        return mapper.convertValue(dashboardRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Id not found")),Dashboard.class);
    }
}
