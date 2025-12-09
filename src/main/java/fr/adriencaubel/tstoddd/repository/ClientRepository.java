package fr.adriencaubel.tstoddd.repository;

import fr.adriencaubel.tstoddd.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
