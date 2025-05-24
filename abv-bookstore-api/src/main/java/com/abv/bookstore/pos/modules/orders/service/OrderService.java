package com.abv.bookstore.pos.modules.orders.service;

import com.abv.bookstore.pos.common.service.BaseService;
import com.abv.bookstore.pos.modules.orders.dt.OrderRequestDTO;
import com.abv.bookstore.pos.modules.orders.dt.OrderResponseDTO;

public interface OrderService extends BaseService<OrderRequestDTO, OrderResponseDTO,Long> {
}
