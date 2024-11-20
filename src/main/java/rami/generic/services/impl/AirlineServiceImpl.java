package rami.generic.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import rami.generic.dtos.airline.AirlineDTOPost;
import rami.generic.entities.AirlineEntity;
import rami.generic.models.AirlineModel;
import rami.generic.repositories.AirlineRepository;
import rami.generic.repositories.GenericRepository;
import rami.generic.services.AirlineService;

@Service
public class AirlineServiceImpl implements
        AirlineService<AirlineEntity, Long, AirlineModel, AirlineDTOPost> {

    @Autowired
    private AirlineRepository airlineRepository;

    @Autowired @Qualifier("strictMapper")
    private ModelMapper mapper;

    @Override
    public ModelMapper getMapper() {
        return mapper;
    }

    @Override
    public GenericRepository<AirlineEntity, Long> getRepository() {
        return airlineRepository;
    }

    @Override
    public Class entityClass() {
        return AirlineEntity.class;
    }

    @Override
    public Class modelClass() {
        return AirlineModel.class;
    }

}
