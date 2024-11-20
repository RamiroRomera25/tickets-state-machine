package rami.generic.repositories;

import org.springframework.stereotype.Repository;
import rami.generic.entities.AirlineEntity;
import rami.generic.entities.PlaneEntity;

@Repository
public interface PlaneRepository extends GenericRepository<PlaneEntity, Long> {
}
