package com.miko.busserver.Controller;

import com.miko.busserver.Entity.*;
import com.miko.busserver.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    TicketService ticketService;

    @Autowired
    BusStopService busStopService;

    @Autowired
    SeatService seatService;

    @Autowired
    BusService busService;

    @GetMapping("/user-all")
    public ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<>(userService.findAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/user-number")
    public ResponseEntity<?> getNumberOfUsers(){
        return new ResponseEntity<>(userService.getNumbersOfUsers(), HttpStatus.OK);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id")String id){
        Long lId = Long.parseLong(id);
        return new ResponseEntity<>(userService.findByUsernameById(lId), HttpStatus.OK);
    }


    @GetMapping("/ticket-number")
    public ResponseEntity<?> getNumberOfTickets(){
        return new ResponseEntity<>(ticketService.getNumberOfTickets(), HttpStatus.OK);
    }

    @GetMapping("/ticket-all")
    public ResponseEntity<?> getAllTickets(){
        return new ResponseEntity<>(ticketService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/bus-number")
    public ResponseEntity<?> getNumberOfBusses(){
        return new ResponseEntity<>(busService.getNumberOfBusses(), HttpStatus.OK);
    }

    @GetMapping("/bus-all")
    public ResponseEntity<?> getAllBusses(){
        return new ResponseEntity<>(busService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/bus/{id}")
    public ResponseEntity<?> getBusDetails(@PathVariable("id")String id){
        Long lId = new Long(id);
        return new ResponseEntity<>(busService.getBusDetails(lId), HttpStatus.OK);
    }

    @GetMapping("/busStops/{id}")
    public ResponseEntity<?> getBusStopsByBusId(@PathVariable("id")String id){
        Long lId = new Long(id);
        return new ResponseEntity<>(busStopService.getBusStopsByBusId(lId), HttpStatus.OK);
    }

    @PostMapping("/seat")
    public ResponseEntity<?> newSeat(@RequestBody Seat seat){

        Long busId = seat.getBus().getId();
        Bus bus = busService.findBusById(busId);
        Seat newSeat = seat;
        newSeat.setBus(bus);
        if(seatService.addNewSeat(newSeat)==null){
            new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/bus-stop")
    public ResponseEntity<?> newBusStop(@RequestBody BusStop busStop){
        Long busId = busStop.getBus().getId();
        Bus bus = busService.findBusById(busId);
        BusStop newBusStop = busStop;
        newBusStop.setBus(bus);
        if(busStopService.addNewBusStop(newBusStop)==null){
            new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/bus-stop/{id}")
    public ResponseEntity<?> getBusStop(@PathVariable("id")String id){
        Long lId = new Long(id);
        BusStop selectedBusStop = busStopService.findById(lId);
        if(selectedBusStop!=null){
            return new ResponseEntity<>(selectedBusStop, HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id")String id){
        Long lId = new Long(id);
        User user = userService.findById(lId);
        if(user!=null){
            List<Ticket> userTicketList = user.getTickets();
            for (Ticket userTicket:userTicketList) {
                List<Seat> seatList = userTicket.getSeats();
                for (Seat seat:seatList) {
                    seat.setAvaiable(true);
                    seat.setTicket(null);
                    seatService.setSeatFree(seat);
                }
            }
            userService.deleteUser(user);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/seat/{id}")
    public ResponseEntity<?> deleteSeat(@PathVariable("id")String id){
        Long lId = new Long(id);
        Seat seat = seatService.findById(lId);
        if(seat!=null){
            seatService.deleteSeat(seat);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/bus/{id}")
    public ResponseEntity<?> deleteBus(@PathVariable("id")String id){
        Long lId = new Long(id);
        Bus bus = busService.findBusById(lId);
        if(bus!=null){
            busService.deleteBus(bus);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/ticket/{id}")
    public ResponseEntity<?> deleteTicket(@PathVariable("id")String id){
        Long lId = new Long(id);
        Ticket ticket = ticketService.findById(lId);
        if(ticket!=null){
            ticketService.deleteTicket(ticket);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/new-bus")
    public ResponseEntity<?> newBus(@RequestBody Bus bus){
        busService.addNewBus(bus);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
