package rami.generic.services.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import rami.generic.dtos.clients.ClientDTOPost;
import rami.generic.dtos.ticket.TicketDTOPost;
import rami.generic.entities.ClientEntity;
import rami.generic.entities.TicketEntity;
import rami.generic.models.ClientModel;
import rami.generic.models.TicketModel;
import rami.generic.repositories.ClientRepository;
import rami.generic.repositories.GenericRepository;
import rami.generic.services.ClientService;
import rami.generic.services.TicketService;

@Service
public class TicketServiceImpl implements
        TicketService<TicketEntity, Long, TicketModel, TicketDTOPost> {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired @Qualifier("strictMapper")
    private ModelMapper mapper;
    @Override
    public Class<ClientEntity> entityClass() {
        return ClientEntity.class;
    }

    @Override
    public Class<ClientModel> modelClass() {
        return ClientModel.class;
    }

    @Override
    public ModelMapper getMapper() {
        return mapper;
    }

    @Override
    public GenericRepository<ClientEntity, Long> getRepository() {
        return clientRepository;
    }
}
