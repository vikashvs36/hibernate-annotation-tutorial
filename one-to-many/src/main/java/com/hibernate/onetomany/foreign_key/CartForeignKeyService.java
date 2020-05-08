package com.hibernate.onetomany.foreign_key;

import com.hibernate.onetomany.foreign_key.domain.Cart;
import com.hibernate.onetomany.foreign_key.domain.Item;
import com.hibernate.onetomany.foreign_key.repository.CartRepository;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

@Component
public class CartForeignKeyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartForeignKeyService.class);

    @Autowired
    private CartRepository cartRepository;

    public void saveCart() {

        Set<Item> cart1 = new HashSet<>();
        cart1.add(new Item("SCJP", new BigDecimal(546.00), 20));
        cart1.add(new Item("Design pattern", new BigDecimal(236.00), 30));

        Set<Item> cart2 = new HashSet<>();
        cart2.add(new Item("Phone", new BigDecimal(23546.00), 5));
        cart2.add(new Item("Laptop", new BigDecimal(43546.00)));

        List<Cart> cartList = Arrays.asList(
                new Cart("Books", cart1),
                new Cart("Electronic", cart2)
        );
        cartRepository.saveAll(cartList);
    }

    public Cart findById(long id) {
        Optional<Cart> cart = cartRepository.findById(id);
        if (cart.isPresent()) {
            LOGGER.info(cart.get().toString());
            return cart.get();
        }
        return null;
    }

    public void delete(long id) {
        Cart cart = findById(id);
        if(cart!= null) {
            cartRepository.delete(cart);
        }
    }

    public void findAll() {
        List<Cart> cartList = cartRepository.findAll();
        cartList.forEach(obj-> LOGGER.info(obj.toString()));
    }
}
