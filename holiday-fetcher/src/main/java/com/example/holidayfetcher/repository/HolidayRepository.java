package com.example.holidayfetcher.repository;
import com.example.holidayfetcher.model.Holiday;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidayRepository extends JpaRepository<Holiday, Long> {
}