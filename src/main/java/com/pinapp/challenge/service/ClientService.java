package com.pinapp.challenge.service;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.pinapp.challenge.model.Client;
import com.pinapp.challenge.repository.IClientRepository;

@Service
public class ClientService {

    @Autowired
    private IClientRepository clientRepository;
    private static final Integer LIFE_EXPECTANCY = 75;

    public Client save(Client client) {
        client.setPotentialDateOfDeath(calculatePotentialDateOfDeath(client.getBirthday()));
        client.setAge(calculateAge(client.getBirthday()));
        return clientRepository.save(client);
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public HashMap<String, Double> getAverageAgeAndStandardDeviation() {
        HashMap<String, Double> response = new HashMap<>();
        Double averageAge = clientRepository.getAverageAge();
        Double standardDeviation = calculateStandardDeviation();
        response.put("Average Age", averageAge != null ? averageAge : 0.0);
        response.put("Standard Deviation", standardDeviation != null ? standardDeviation : 0.0);
        return response;
    }

    private LocalDate calculatePotentialDateOfDeath(LocalDate birthday) {
        return birthday.plusYears(LIFE_EXPECTANCY);
    }

    private Integer calculateAge(LocalDate birthday){
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthday, currentDate).getYears();         
    }

    // σ = √(Σ(xi - μ)² / N)
    private Double calculateStandardDeviation() {
        List<Integer> ageList = clientRepository.getAgeList();
        Double averageAge = clientRepository.getAverageAge();
        Double result = 0.0;

        if (ageList.size() != 0) {
            // Σ(xi - μ)²
            for (Integer age : ageList) {
                result += Math.pow(age - averageAge, 2);
            }
            result = Math.sqrt(result / ageList.size());
        }

        return result;
    }

}
