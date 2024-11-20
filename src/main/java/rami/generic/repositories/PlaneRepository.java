package rami.generic.repositories;

import org.springframework.stereotype.Repository;
import rami.generic.entities.AirlineEntity;

@Repository
public interface PlaneRepository extends GenericRepository<AirlineEntity, Long> {
}
