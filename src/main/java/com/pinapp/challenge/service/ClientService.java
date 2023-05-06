package com.pinapp.challenge.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pinapp.challenge.repository.IClientRepository;

@Service
public class ClientService {

    @Autowired
    private IClientRepository clientRepository;
    
    
}
