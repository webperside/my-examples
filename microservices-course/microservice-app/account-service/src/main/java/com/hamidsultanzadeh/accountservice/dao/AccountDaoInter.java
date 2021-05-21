package com.hamidsultanzadeh.accountservice.dao;

import com.hamidsultanzadeh.accountservice.entity.Account;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountDaoInter extends CassandraRepository<Account,String> {
}
