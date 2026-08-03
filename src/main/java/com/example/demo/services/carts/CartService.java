package com.example.demo.services.carts;

import com.example.demo.entities.Cart;
import com.example.demo.repositories.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService implements ICartService{
    @Autowired
    private CartRepository cartRepository ;

    @Override
    public Cart addcart(Cart cart) {
        return cartRepository.save(cart);
    }
}
