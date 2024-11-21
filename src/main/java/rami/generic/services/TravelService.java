package rami.generic.services;

import org.springframework.stereotype.Service;
import rami.generic.entities.base.BaseEntity;
import rami.generic.services.genericSegregation.basicCRUD.GenericCreate;
import rami.generic.services.genericSegregation.basicCRUD.GenericGetAllList;
import rami.generic.services.genericSegregation.basicCRUD.GenericGetById;

@Service
public interface TravelService<E extends BaseEntity, I, M, DTOPOST>
        extends GenericGetAllList<E, I, M>,
        GenericCreate<E, I, M, DTOPOST>,
        GenericGetById<E, I, M> {

    M delete(I travelId);
}
