package com.pinapp.challenge.controller;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.pinapp.challenge.Utils.ClientValidator;
import com.pinapp.challenge.model.Client;
import com.pinapp.challenge.service.ClientService;



@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private ClientValidator clientValidator;
    @Autowired
    private MessageSource messageSource;
    
    @PostMapping("/creacliente")
    public ResponseEntity<String> save(@RequestBody Client client, BindingResult result) {
        ResponseEntity<String> response;
        clientValidator.validate(client, result);
        if(!result.hasErrors()){
            clientService.save(client);
            response = ResponseEntity.ok(messageSource.getMessage("client.created.succesfully",null,null));
        }else{
            List<String> errorCodes = new ArrayList<>();
            result.getAllErrors().forEach(error -> {String errorCode = error.getCode();errorCodes.add(errorCode);});
            response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorCodes.toString());
        }
        return response;
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
