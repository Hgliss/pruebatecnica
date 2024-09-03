package com.ventas.gestion_clientes.service;


import com.ventas.gestion_clientes.model.Client;
import com.ventas.gestion_clientes.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClienteRepository clientReposity;

   //Create or Update
    public Client saveClient(Client client){
        return clientReposity.save(client);
    }

    //Read All
    public List<Client> obtainAllClient(){
        return clientReposity.findAll();
    }

    //Read client by Id
    public Client obtainClientById(Long id){
        Optional<Client> client = clientReposity.findById(id);
        return client.orElse(null);
    }

    //Delete
    public void deleteClient(Long id){
        clientReposity.deleteById(id);
    }


}
