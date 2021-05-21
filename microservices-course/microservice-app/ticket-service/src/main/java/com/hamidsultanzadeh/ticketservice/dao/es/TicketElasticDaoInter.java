package com.hamidsultanzadeh.ticketservice.dao.es;

import com.hamidsultanzadeh.ticketservice.entity.es.TicketModel;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TicketElasticDaoInter extends ElasticsearchRepository<TicketModel,String> {
}
