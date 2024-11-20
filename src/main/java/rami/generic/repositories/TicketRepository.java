package rami.generic.repositories;

import org.springframework.stereotype.Repository;
import rami.generic.entities.AirlineEntity;
import rami.generic.entities.TicketEntity;

@Repository
public interface TicketRepository extends GenericRepository<TicketEntity, Long> {
}
