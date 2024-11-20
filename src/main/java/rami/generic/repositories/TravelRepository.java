package rami.generic.repositories;

import org.springframework.stereotype.Repository;
import rami.generic.entities.AirlineEntity;
import rami.generic.entities.TravelEntity;

@Repository
public interface TravelRepository extends GenericRepository<TravelEntity, Long> {
}
