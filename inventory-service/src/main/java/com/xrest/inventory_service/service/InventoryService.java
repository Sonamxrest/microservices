package com.xrest.inventory_service.service;

import com.xrest.inventory_service.dto.InventoryResponse;
import com.xrest.inventory_service.model.Inventory;
import com.xrest.inventory_service.repository.InventoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public InventoryService(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skucode) {
        return inventoryRepository.findInventoriesBySkuCodeIn(skucode).stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private InventoryResponse mapToDto(Inventory d) {
        return InventoryResponse.builder().skuCode(d.getSkuCode())
                .isInStock(d.getQty() > 0).build();
    }
}
