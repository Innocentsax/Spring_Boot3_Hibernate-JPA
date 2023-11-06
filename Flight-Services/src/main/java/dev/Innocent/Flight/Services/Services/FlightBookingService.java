package dev.Innocent.Flight.Services.Services;

import dev.Innocent.Flight.Services.DTO.FlightBookingAcknowledgement;
import dev.Innocent.Flight.Services.DTO.FlightBookingRequest;
import dev.Innocent.Flight.Services.Entity.PassengerInfo;
import dev.Innocent.Flight.Services.Entity.PaymentInfo;
import dev.Innocent.Flight.Services.Repository.PassengerInfoRepository;
import dev.Innocent.Flight.Services.Repository.PaymentInfoRepository;
import dev.Innocent.Flight.Services.Utils.PaymentUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class FlightBookingService {
    private PassengerInfoRepository passengerInfoRepository;
    private PaymentInfoRepository paymentInfoRepository;

    @Autowired
    public FlightBookingService(PassengerInfoRepository passengerInfoRepository, PaymentInfoRepository paymentInfoRepository) {
        this.passengerInfoRepository = passengerInfoRepository;
        this.paymentInfoRepository = paymentInfoRepository;
    }

    @Transactional //(readOnly = false, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public FlightBookingAcknowledgement bookFlightTicket(FlightBookingRequest request){
        // Save Passenger info to DataBase
        PassengerInfo passengerInfo = request.getPassengerInfo();
        passengerInfo=passengerInfoRepository.save(passengerInfo);
        // Validate the account of the passenger
        PaymentInfo paymentInfo = request.getPaymentInfo();
        PaymentUtils.validateCreditLimit(paymentInfo.getAccountNo(), passengerInfo.getFare());

        paymentInfo.setPassengerId(passengerInfo.getPId());
        paymentInfo.setAmount(passengerInfo.getFare());
        paymentInfoRepository.save(paymentInfo);
        return new FlightBookingAcknowledgement("SUCCESS", passengerInfo.getFare(),
                UUID.randomUUID().toString().split("-")[0],passengerInfo);
    }
}
