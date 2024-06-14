package org.AutoMappingObjectsExercise.service;

import org.AutoMappingObjectsExercise.service.dtos.CartItemDTO;

public interface ShoppingCartService {

    String addItem(CartItemDTO item);
    String deleteItem(CartItemDTO item);
    String buyItem();
}
