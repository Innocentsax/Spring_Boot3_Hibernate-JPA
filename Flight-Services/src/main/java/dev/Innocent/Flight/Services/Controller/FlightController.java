package dev.Innocent.Flight.Services.Controller;

import dev.Innocent.Flight.Services.DTO.FlightBookingAcknowledgement;
import dev.Innocent.Flight.Services.DTO.FlightBookingRequest;
import dev.Innocent.Flight.Services.Services.FlightBookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@EnableTransactionManagement
public class FlightController {

    private FlightBookingService flightBookingService;

    @Autowired
    public FlightController(FlightBookingService flightBookingService) {
        this.flightBookingService = flightBookingService;
    }

    @PostMapping("/bookFlightTicket")
    public FlightBookingAcknowledgement bookFlightTicket(@RequestBody FlightBookingRequest request){
        return flightBookingService.bookFlightTicket(request);
    }
}
