package fr.adriencaubel.tstoddd.service;

import fr.adriencaubel.tstoddd.domain.Client;
import fr.adriencaubel.tstoddd.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public Client findById(Long clientId) {
        return clientRepository.findById(clientId).orElseThrow(() -> new IllegalStateException("Client not found with id " + clientId));
    }

    public boolean existsById(Long clientId) {
        return clientRepository.existsById(clientId);
    }
}
