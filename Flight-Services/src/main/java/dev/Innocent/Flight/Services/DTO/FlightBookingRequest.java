package dev.Innocent.Flight.Services.DTO;

import dev.Innocent.Flight.Services.Entity.PassengerInfo;
import dev.Innocent.Flight.Services.Entity.PaymentInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FlightBookingRequest {
    private PassengerInfo passengerInfo;
    private PaymentInfo paymentInfo;
}
