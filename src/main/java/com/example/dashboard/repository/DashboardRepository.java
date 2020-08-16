package com.example.dashboard.repository;

import com.example.dashboard.domain.DashboardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DashboardRepository extends JpaRepository<DashboardEntity, Integer> {
}
