package com.webperside.argumentcaptor;


import com.webperside.argumentcaptor.model.dto.MailDto;
import com.webperside.argumentcaptor.model.dto.NotificationDto;
import com.webperside.argumentcaptor.model.enums.UserStatus;
import com.webperside.argumentcaptor.model.mybatis.User;
import com.webperside.argumentcaptor.service.business.UserBusinessService;
import com.webperside.argumentcaptor.service.functional.MailService;
import com.webperside.argumentcaptor.service.functional.NotificationService;
import com.webperside.argumentcaptor.service.functional.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserBusinessServiceTests {

    private ArgumentCaptor<Long> userIdCaptor;
    private ArgumentCaptor<User> userCaptor;
    private ArgumentCaptor<MailDto> mailDtoCaptor;
    private ArgumentCaptor<NotificationDto> notificationDtoCaptor;

    private UserService userService;
    private MailService mailService;
    private NotificationService notificationService;

    private UserBusinessService userBusinessService;

    @BeforeEach
    void setUp() {
        userIdCaptor = ArgumentCaptor.forClass(Long.class);
        userCaptor = ArgumentCaptor.forClass(User.class);
        mailDtoCaptor = ArgumentCaptor.forClass(MailDto.class);
        notificationDtoCaptor = ArgumentCaptor.forClass(NotificationDto.class);

        userService = mock(UserService.class);
        mailService = mock(MailService.class);
        notificationService = mock(NotificationService.class);

        userBusinessService = spy(
                new UserBusinessService(userService, mailService, notificationService)
        );
    }

    @Test
    public void suspendUser_success_v1() {
        User user = prepareUser(); // 3344
        MailDto mailDto = prepareMailDto(user); // 1122
        NotificationDto notificationDto = prepareNotificationDto(user);

        when(userService.findById(1)).thenReturn(user);
        doNothing().when(userService).save(user); // 3344
        doNothing().when(mailService).send(mailDto); // 1122
        doNothing().when(notificationService).send(notificationDto);

        // under test
        userBusinessService.suspendUser(2); // 2233

        assertEquals(user.getUserStatus(), UserStatus.SUSPENDED);
        assertEquals(mailDto.getEmail(), user.getEmail());
        assertEquals(notificationDto.getReceiverId(), user.getReceiverId());

        verify(userService, times(1)).findById(1);
        verify(userService, times(1)).save(user); // 3344
//        verify(mailService, times(1)).send(mailDto); // 1122 - 5566
//        verify(notificationService, times(1)).send(notificationDto);
    }

    @Test
    public void suspendUser_success_v2() {
        User user = prepareUser();

        // flow
        when(userService.findById(userIdCaptor.capture())).thenReturn(user);
        doNothing().when(userService).save(userCaptor.capture());
        doNothing().when(mailService).send(mailDtoCaptor.capture()); // 1111
        doNothing().when(notificationService).send(notificationDtoCaptor.capture());

        userBusinessService.suspendUser(2);

        assertEquals(userIdCaptor.getValue(), 1);
        assertEquals(userCaptor.getValue().getUserStatus(), UserStatus.SUSPENDED);
        assertEquals(mailDtoCaptor.getValue().getEmail(), user.getEmail());
        assertEquals(notificationDtoCaptor.getValue().getReceiverId(), user.getReceiverId());

        verify(userService, times(1)).findById(userIdCaptor.getValue());
        verify(userService, times(1)).save(userCaptor.getValue());
        verify(mailService, times(1)).send(mailDtoCaptor.getValue()); // 1111
        verify(notificationService, times(1)).send(notificationDtoCaptor.getValue());
    }

    // private util methods
    private User prepareUser() {
        return new User(1, "example@webperside.com", "123", UserStatus.ACTIVE);
    }

    private MailDto prepareMailDto(User user) {
        return new MailDto(user.getEmail());
    }

    private NotificationDto prepareNotificationDto(User user) {
        return new NotificationDto(user.getReceiverId());
    }
}
