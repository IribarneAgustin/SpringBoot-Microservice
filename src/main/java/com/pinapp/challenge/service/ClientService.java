package com.pinapp.challenge.service;
import java.time.LocalDate;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pinapp.challenge.model.Client;
import com.pinapp.challenge.repository.IClientRepository;

@Service
public class ClientService {

    @Autowired
    private IClientRepository clientRepository;
    
    public Client save(Client client){
        LocalDate potentialDateOfDeath = calculatePotentialDateOfDeath(client.getBirthday());
        client.setPotentialDateOfDeath(potentialDateOfDeath);
        return clientRepository.save(client);        
    }

    public List<Client> findAll(){
        return clientRepository.findAll();
    }

    public HashMap<String,Double> getAverageAgeAndStandardDeviation(){
        HashMap<String, Double> response = new HashMap<>();
        response.put("Average Age", clientRepository.getAverageAge());
        response.put("Standard Deviation", calculateStandardDeviation());
        return response;
    }

    //Life expectancy: 75 years
    private LocalDate calculatePotentialDateOfDeath(LocalDate birthday){
        Integer lifeExpectancy = 75;
        return birthday.plusYears(lifeExpectancy);
    }   

    // σ = √(Σ(xi - μ)² / N)
    private Double calculateStandardDeviation(){
        List<Integer> ageList = clientRepository.getAgeList();
        Double averageAge = clientRepository.getAverageAge();
        Double result = 0.0;
        //Σ(xi - μ)²
        for (Integer age : ageList) {
            result += Math.pow(age - averageAge,2);                    
        }
        result = Math.sqrt(result / ageList.size());
        
        return result;
    }
    
}
