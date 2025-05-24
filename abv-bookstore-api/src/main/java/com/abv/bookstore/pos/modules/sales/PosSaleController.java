package com.abv.bookstore.pos.modules.sales;
import com.abv.bookstore.pos.common.controller.BaseController;
import com.abv.bookstore.pos.modules.orders.dt.OrderRequestDTO;
import com.abv.bookstore.pos.modules.orders.dt.OrderResponseDTO;
import com.abv.bookstore.pos.modules.orders.service.OrderService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vi/pos/sales")
public class PosSaleController extends BaseController<OrderRequestDTO, OrderResponseDTO,Long> {
    private final OrderService orderService;

    public PosSaleController(OrderService orderService) {
        super(orderService);
        this.orderService = orderService;
    }
}

//    List<SellerBookDTO> searchBooks(String query);     // Seller
//    SellerBookDTO getBookBySku(String sku);            // Seller
//}


//POST /api/orders
//{
//    "customerId": 123,
//        "items": [
//    { "bookId": 1, "quantity": 2 },
//    { "bookId": 5, "quantity": 1 }
//  ]
//}
