package com.webperside.argumentcaptor.service.business;

import com.webperside.argumentcaptor.model.dto.MailDto;
import com.webperside.argumentcaptor.model.dto.NotificationDto;
import com.webperside.argumentcaptor.model.enums.UserStatus;
import com.webperside.argumentcaptor.model.mybatis.User;
import com.webperside.argumentcaptor.service.functional.MailService;
import com.webperside.argumentcaptor.service.functional.NotificationService;
import com.webperside.argumentcaptor.service.functional.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserBusinessService {

    private final UserService userService;
    private final MailService mailService;
    private final NotificationService notificationService;

    public UserBusinessService(UserService userService, MailService mailService, NotificationService notificationService) {
        this.userService = userService;
        this.mailService = mailService;
        this.notificationService = notificationService;
    }

    public void suspendUser(long id) {
        User user = userService.findById(id);

        user.setUserStatus(UserStatus.SUSPENDED);
        userService.save(user);

        mailService.send(
                prepareMailDto(user)
        );

        notificationService.send(
                prepareNotificationDto(user)
        );
    }

    // private util methods
    private MailDto prepareMailDto(User user) {
        return new MailDto(user.getEmail());
    }

    private NotificationDto prepareNotificationDto(User user) {
        return new NotificationDto(user.getReceiverId());
    }

}
