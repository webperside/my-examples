package com.hamidsultanzadeh.accountservice.entity;

import lombok.*;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
@Table
@Getter
@Setter
public class Account implements Serializable {

    @PrimaryKey
    private String id;
    @Column("username")
    private String username;
    @Column("name")
    private String name;
    @Column("surname")
    private String surname;
    @Column("date_of_birth")
    private Date dateOfBirth;
    @Column("email")
    private String email;
    @Column("pass")
    private String pass;
    @Column("insert_time")
    Date insertTime;
    @Column("is_enabled")
    boolean enabled;


}
