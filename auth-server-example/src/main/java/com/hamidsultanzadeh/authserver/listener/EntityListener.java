package com.hamidsultanzadeh.authserver.listener;

import com.hamidsultanzadeh.authserver.model.BaseEntity;

import javax.persistence.PrePersist;
import java.time.Instant;

public class EntityListener {

    @PrePersist
    public void onPrePersist(BaseEntity model){
        model.setCreatedAt(Instant.now());
    }

}
