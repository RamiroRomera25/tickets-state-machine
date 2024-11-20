package rami.generic.repositories;

import org.springframework.stereotype.Repository;
import rami.generic.entities.ClientEntity;

@Repository
public interface ClientRepository extends GenericRepository<ClientEntity, Long> {
}
