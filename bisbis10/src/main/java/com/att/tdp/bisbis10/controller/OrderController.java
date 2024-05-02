package com.att.tdp.bisbis10.controller;


import com.att.tdp.bisbis10.objectDTOs.OrderDTO;
import com.att.tdp.bisbis10.objectDTOs.RatingDTO;
import com.att.tdp.bisbis10.services.OrderService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.Map;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/order")
    public ResponseEntity<Map<String, String>> addOrder(@RequestBody OrderDTO orderDTO) throws Exception {
        return new ResponseEntity<>(orderService.order(orderDTO), HttpStatus.OK);
    }
}
