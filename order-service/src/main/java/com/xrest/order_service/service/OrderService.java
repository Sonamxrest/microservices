package com.xrest.order_service.service;

import com.xrest.order_service.dto.InventoryResponse;
import com.xrest.order_service.dto.OrderLineItemsDto;
import com.xrest.order_service.dto.OrderRequestDto;
import com.xrest.order_service.model.Order;
import com.xrest.order_service.model.OrderLineItems;
import com.xrest.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    private final WebClient.Builder webClientBuilder;

    public void placeOrder(OrderRequestDto orderRequestDto) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        order.setOrderLineItemsList(orderRequestDto.getOrderLineItemsList().stream().map(this::mapToDto).collect(Collectors.toList()));
        List<String> skuCodes = orderRequestDto.getOrderLineItemsList().stream().map(OrderLineItemsDto::getSkuCode).toList();
        //block will create a syncronus communication
        InventoryResponse [] inventoryResponses = webClientBuilder.build().get()
                .uri("http://inventory-service/v1/inventory", uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
        assert inventoryResponses != null;
        boolean allProductsInStock = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);
        if (allProductsInStock)
            orderRepository.save(order);
        else
            throw new IllegalArgumentException("Product is not in stock");
    }

    private OrderLineItems mapToDto(OrderLineItemsDto d) {
        OrderLineItems orderLineItemsDto = new OrderLineItems();
        orderLineItemsDto.setPrice(d.getPrice());
        orderLineItemsDto.setQty(d.getQty());
        orderLineItemsDto.setSkuCode(d.getSkuCode());
        return orderLineItemsDto;
    }


}
