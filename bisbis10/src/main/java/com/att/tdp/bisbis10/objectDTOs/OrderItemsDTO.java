package com.att.tdp.bisbis10.objectDTOs;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderItemsDTO {

    private UUID dishId;

    private int amount;

}
