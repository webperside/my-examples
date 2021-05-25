package com.example.springbootplsql.service.impl;

import com.example.springbootplsql.dto.UserDto;
import com.example.springbootplsql.models.Address;
import com.example.springbootplsql.models.User;
import com.example.springbootplsql.repository.UserRepository;
import com.example.springbootplsql.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @PostConstruct
    public void init(){
        UserDto userDto = UserDto.builder()
                .name("Hamid 2")
                .surname("Sutlanzadeh 2")
                .addresses(Arrays.asList("Yeni Suraxani 2", "Suraxani 2"))
                .build();

        System.out.println(save(userDto));
    }

    @Override
    public UserDto save(UserDto userDto) {

        User user = User.builder()
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .build();

        List<Address> addresses = new ArrayList<>();

        if(userDto.getAddresses() != null){
            userDto.getAddresses().forEach(address -> {
                addresses.add(Address.builder()
                        .user(user)
                        .addressName(address)
                        .addressType(Address.AddressType.HOME)
                        .isActive(true)
                        .build()
                );
            });
        }

        user.setAddresses(addresses);

        userRepository.save(user);

        userDto.setId(user.getId());
        return userDto;
    }

    @Override
    public void delete(UserDto userDto) {

    }

    @Override
    public UserDto findById(Long id) {
        return null;
    }

    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        return null;
    }
}
