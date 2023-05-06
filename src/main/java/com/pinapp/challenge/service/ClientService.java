package com.pinapp.challenge.service;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pinapp.challenge.model.Client;
import com.pinapp.challenge.repository.IClientRepository;

@Service
public class ClientService {

    @Autowired
    private IClientRepository clientRepository;
    
    public Client save(Client client){
        LocalDate potentialDateOfDeath = calculatePotentialDateOfDeath(client.getBirthday()); //Null pointer
        client.setPotentialDateOfDeath(potentialDateOfDeath);
        return clientRepository.save(client);        
    }

    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    //Life expectancy: 75 years
    private LocalDate calculatePotentialDateOfDeath(LocalDate birthday){
        Integer lifeExpectancy = 75;
        return birthday.plusYears(lifeExpectancy);
    }

    public HashMap<String,Double> getAverageAgeAndStandardDeviation(){
        HashMap<String, Double> response = new HashMap<>();
        response.put("Average Age", clientRepository.getAverageAge());
        response.put("Standard Deviation", new Double("1"));
        return response;
    }
    
}
