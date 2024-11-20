package rami.generic.services;

import rami.generic.entities.base.BaseEntity;
import rami.generic.services.genericSegregation.basicCRUD.GenericCreate;
import rami.generic.services.genericSegregation.basicCRUD.GenericGetAllList;
import rami.generic.services.genericSegregation.basicCRUD.GenericSoftDelete;
import rami.generic.services.genericSegregation.basicCRUD.GenericUpdate;

public interface PlaneService<E extends BaseEntity, I, M, DTOPOST, DTOPUT>
        extends GenericGetAllList<E, I, M>,
        GenericCreate<E, I, M, DTOPOST>,
        GenericUpdate<E, I, M, DTOPUT>,
        GenericSoftDelete<E, I, M> {
}
