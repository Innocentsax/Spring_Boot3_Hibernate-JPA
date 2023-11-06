package dev.Innocent.Flight.Services.Repository;

import dev.Innocent.Flight.Services.Entity.PaymentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentInfoRepository extends JpaRepository<PaymentInfo, String>{
}
