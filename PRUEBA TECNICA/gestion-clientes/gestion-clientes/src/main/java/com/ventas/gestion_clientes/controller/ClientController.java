package com.ventas.gestion_clientes.controller;


import com.ventas.gestion_clientes.dto.ApiResponse;
import com.ventas.gestion_clientes.model.Client;
import com.ventas.gestion_clientes.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    
    @Autowired
    private ClientService clientService;

    //Read all Client
    @GetMapping
    public List<Client>obtainAllClient(){
        return clientService.obtainAllClient();
    }

    //Read client by Id
    @GetMapping("/{id}")
    public Client obtainClientById(@PathVariable Long id){
        return clientService.obtainClientById(id);
    }

    //Create new client
    @PostMapping
    public Client saveClient (@RequestBody Client client){
        return clientService.saveClient(client);
    }

    //Update
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Client>> updateClient(@PathVariable Long id, @RequestBody Client detailsClient) {
        Client existingClient = clientService.obtainClientById(id);
        if (existingClient != null) {
            existingClient.setFirstName(detailsClient.getFirstName());
            existingClient.setSecondName(detailsClient.getSecondName());
            existingClient.setFirstLastName(detailsClient.getFirstLastName());
            existingClient.setSecondLastName(detailsClient.getSecondLastName());
            existingClient.setEmail(detailsClient.getEmail());
            existingClient.setTelephone(detailsClient.getTelephone());
            existingClient.setAddress(detailsClient.getAddress());
            Client updatedClient = clientService.saveClient(existingClient);
            return ResponseEntity.ok(new ApiResponse<>("Cliente actualizado con éxito", updatedClient));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse<>("Cliente no encontrado", null));
    }

    //Delete
    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id){
        clientService.deleteClient(id);
    }



}
