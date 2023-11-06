package dev.Innocent.Flight.Services.Repository;

import dev.Innocent.Flight.Services.Entity.PassengerInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassengerInfoRepository extends JpaRepository<PassengerInfo, Long> {
}
