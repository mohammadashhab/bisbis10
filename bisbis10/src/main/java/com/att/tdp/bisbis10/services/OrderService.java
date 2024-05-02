package com.att.tdp.bisbis10.services;

import com.att.tdp.bisbis10.entities.Dish;
import com.att.tdp.bisbis10.entities.Order;
import com.att.tdp.bisbis10.entities.Restaurant;
import com.att.tdp.bisbis10.objectDTOs.OrderDTO;
import com.att.tdp.bisbis10.objectDTOs.OrderItemsDTO;
import com.att.tdp.bisbis10.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DishService dishService;

    @Autowired
    private RestaurantService restaurantService;


    public Map<String, String> order(OrderDTO orderDTO) throws Exception {
        Order order = Order.builder().build();
        UUID restaurantId = orderDTO.getRestaurantId();
        Restaurant restaurant = restaurantService.getRestaurant(restaurantId);
        order.setRestaurant(restaurant);
        orderRepository.save(order);
        float totalPrice = 0f;
        List<OrderItemsDTO> orderItemsDTOS = orderDTO.getOrderItems();
        for(OrderItemsDTO orderItems : orderItemsDTOS){
            UUID dishID = orderItems.getDishId();
            Dish dish = dishService.getDish(dishID);
            order.addDish(dish);
            totalPrice += dish.getPrice();
            dish.addOrder(order);
            dishService.saveDish(dish);
        }
        order.setTotalPrice(totalPrice);
        orderRepository.save(order);
        Map<String, String> map = new HashMap<>();
        map.put("orderId", order.getId().toString());
        return map;
    }

}
