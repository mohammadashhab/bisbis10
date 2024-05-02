package com.att.tdp.bisbis10.objectDTOs;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDTO {

    private UUID restaurantId;

    private List<OrderItemsDTO> orderItems;

}
