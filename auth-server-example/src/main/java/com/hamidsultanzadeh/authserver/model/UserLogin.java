package com.hamidsultanzadeh.authserver.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_login")
public class UserLogin extends BaseEntity{

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id_user_login")
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "fk_id_user", referencedColumnName = "id_user")
    private User user;

    @Column(name = "access_token", unique = true)
    private String accessToken;

    @Column(name = "access_token_expire_date")
    private LocalDateTime accessTokenExpireDate;

    @Column(name = "refresh_token", unique = true, length = 50)
    private String refreshToken;

    @Column(name = "refresh_token_expire_date")
    private LocalDateTime refreshTokenExpireDate;
}
