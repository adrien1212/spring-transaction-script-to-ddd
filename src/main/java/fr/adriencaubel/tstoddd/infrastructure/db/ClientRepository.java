package fr.adriencaubel.tstoddd.infrastructure.db;

import fr.adriencaubel.tstoddd.domain.model.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
