package com.hamidsultanzadeh.client.contract;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO {

    private String id ;
    private String username;
    private String name;
    private String surname;
    private Date dateOfBirth;
    private String email;

    public String getNameSurname(){
        return this.name + " " + this.surname;
    }

}
