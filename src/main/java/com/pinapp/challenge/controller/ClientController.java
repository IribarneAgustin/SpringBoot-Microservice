package com.pinapp.challenge.controller;
import java.util.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.pinapp.challenge.model.Client;
import com.pinapp.challenge.service.ClientService;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/")
    public String home() {
        return "Hello world";
    }

    @PostMapping("/creacliente")
    public Client save(@Valid @RequestBody Client client, BindingResult result) {
        return clientService.save(client);
    }

    @GetMapping("/listclientes")
    public List<Client> findAll() {
        return clientService.findAll();
    }

    @GetMapping("/kpideclientes")
    public HashMap<String, Double> getAverageAgeAndStandardDeviation() {
        return clientService.getAverageAgeAndStandardDeviation();
    }

}
