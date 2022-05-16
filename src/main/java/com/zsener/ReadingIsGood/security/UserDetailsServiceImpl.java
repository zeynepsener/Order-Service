package com.zsener.ReadingIsGood.security;

import com.zsener.ReadingIsGood.model.Customer;
import com.zsener.ReadingIsGood.repository.CustomerRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public UserDetailsServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = customerRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username:" + username));
        return new UserDetailsImpl(customer.getName(), customer.getPassword());
    }
}
