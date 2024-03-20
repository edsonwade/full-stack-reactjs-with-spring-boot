package code.with.vanilson.services;

import code.with.vanilson.dto.OrderDTO;
import code.with.vanilson.dto.OrderLineItemDTO;
import code.with.vanilson.exception.OrderNotFoundException;
import code.with.vanilson.persistence.model.Order;
import code.with.vanilson.persistence.model.OrderLineItem;
import code.with.vanilson.persistence.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {


    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final OrderRepository orderRepository;


    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Integer id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent()) {
            logger.info(" founded the order with id {}", id);
            return order.get();
        }
        throw new OrderNotFoundException(" Order with id " + id + " not found");
    }

    public void placeOrder(OrderDTO order) {
        var order1 = new Order();
        var atomic = new AtomicInteger(0);
        order1.setOrder_id(atomic.incrementAndGet());

        var orderLineItems = order.getOrderLineItemDTOList()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());

        order1.setOrderLineItems(orderLineItems);

        orderRepository.save(order1);
    }

    private OrderLineItem mapToDto(OrderLineItemDTO orderLineItemDTO) {

        var orderItems = new OrderLineItem();

        orderItems.setSkuCode(orderLineItemDTO.getSkuCode());
        orderItems.setPrice(orderLineItemDTO.getPrice());
        orderItems.setQuantity(orderLineItemDTO.getQuantity());

        return orderItems;

    }
}
