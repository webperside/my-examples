package com.example.springbootplsql.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "address")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Address implements Serializable {

    @Id
    @SequenceGenerator(name = "seq_address", allocationSize = 1)
    @GeneratedValue(generator = "seq_address", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "address_name", length = 100)
    private String addressName;

    @Enumerated
    @Column(name = "address_type")
    private AddressType addressType;

    @Column(name = "is_active")
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    public enum AddressType{
        HOME,
        WORK,
        OTHER;
    }
}
