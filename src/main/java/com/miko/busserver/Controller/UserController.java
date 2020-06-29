package com.miko.busserver.Controller;

import com.miko.busserver.Entity.*;
import com.miko.busserver.Service.*;
import com.miko.busserver.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    BusStopService busStopService;

    @Autowired
    TicketService ticketService;

    @Autowired
    SeatService seatService;

    @Autowired
    BusService busService;

    @Autowired
    MailService mailService;

    @PostMapping("/registration")
    public ResponseEntity<?> register(@RequestBody User user){
        if(userService.findByUsername(user.getUsername())!=null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        user.setRole(Role.USER);
        return new ResponseEntity<>(userService.saveUser(user),HttpStatus.CREATED);
    }


    @GetMapping("/busstops")
    public ResponseEntity<?> getAllBusStops(){
        return new ResponseEntity<>(busStopService.findAllBusStopsNames(), HttpStatus.OK);
    }

    @GetMapping("/busstop/{name}")
    public ResponseEntity<?> getStops(@PathVariable("name")String name){
        return new ResponseEntity<>(busStopService.findByName(name), HttpStatus.OK);
    }

    @GetMapping("/route/{id}/{origin}/{destination}")
    public ResponseEntity<?> getBus(@PathVariable("id")String id, @PathVariable("origin")String origin,@PathVariable("destination")String destination){
        Long lId = new Long(id);
        Bus selectedBus = busService.findBusById(lId);
        selectedBus.setPrice(busService.calculatePrice(origin, destination, selectedBus));
        return new ResponseEntity<>(selectedBus, HttpStatus.OK);
    }



    @GetMapping("/login")
    public ResponseEntity<?> getUser(Principal principal){
        if(principal == null){
            return ResponseEntity.ok(principal);
        }
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) principal;
        User user = userService.findByUsername(authenticationToken.getName());
        user.setToken(jwtTokenProvider.generateToken(authenticationToken));
        if(user!=null){
            return new ResponseEntity<>(user, HttpStatus.OK);

        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }

    @PostMapping("/route")
    public ResponseEntity<?> getBuses(@RequestBody BusQuery busQuery){
        return new ResponseEntity<>(busService.getBusses(busQuery.getOrigin(),busQuery.getArrival()),HttpStatus.OK);
    }

    @GetMapping("/seats/{id}")
    public ResponseEntity<?> getSeats(@PathVariable("id")String id){
        Long lId = new Long(id);
        return new ResponseEntity<>(seatService.getSeats(id),HttpStatus.OK);
    }

    @PostMapping("/buyTicket")
    public ResponseEntity<?> buyTicket(@RequestBody Ticket jsonTicket) throws MessagingException {
        Ticket newTicket = new Ticket(jsonTicket);
        List<Seat> seatList = newTicket.getSeats();
        User user = jsonTicket.getUser();
        if(user.getId()==null){
            user.setRole(Role.GUEST);
            userService.saveUser(user);
        }
        seatService.reserveSeats(seatList, newTicket);
        mailService.sendMail(user.getEmail(), "Reservation "+newTicket.getTicketNumber(),newTicket.toString(),false);
        return new ResponseEntity<>(ticketService.save(newTicket),HttpStatus.OK);
    }

    @GetMapping("/confirmation/{reservationNumber}")
    public ResponseEntity<?> getConfirmation(@PathVariable("reservationNumber")String reservationNumber){
        Ticket ticket = ticketService.findTicketByReservationNumber(reservationNumber);
        if(ticket!=null){
            return new ResponseEntity<>(ticket,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    @GetMapping("/tickets/{id}")
    public ResponseEntity<?> getAllTicketsByUserId(@PathVariable("id")String id){
        Long lId = new Long(id);
        User user = userService.findById(lId);
        if(user!=null){
            return new ResponseEntity<>(user.getTickets(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

}
