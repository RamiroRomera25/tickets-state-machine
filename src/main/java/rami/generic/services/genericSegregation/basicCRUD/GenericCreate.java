package rami.generic.services.genericSegregation.basicCRUD;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import rami.generic.dtos.clients.ClientDTOPost;
import rami.generic.repositories.GenericRepository;

public interface GenericCreate<E, I, M, DTOPOST> {
    ModelMapper getMapper();

    GenericRepository<E, I> getRepository();

    Class<E> entityClass();

    Class<M> modelClass();

    default M create(DTOPOST dtoPost) {
        E entityToSave = getMapper().map(dtoPost, entityClass());
        return getMapper().map(getRepository().save(entityToSave), modelClass());
    }
}
