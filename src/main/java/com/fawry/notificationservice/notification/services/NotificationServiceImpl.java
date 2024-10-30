package com.fawry.notificationservice.notification.services;

import com.fawry.notificationservice.notification.dtos.RequestNotificationDTO;
import com.fawry.notificationservice.notification.dtos.ResponseNotificationDTO;
import com.fawry.notificationservice.notification.entities.Notification;
import com.fawry.notificationservice.notification.mappers.RequestNotificationMapper;
import com.fawry.notificationservice.notification.mappers.ResponseNotificationMapper;
import com.fawry.notificationservice.notification.repositories.NotificationRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.stream.Collectors;
import javax.mail.*;
import javax.mail.internet.*;
@Service
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService{
    private final NotificationRepository notificationRepository;
    private final ResponseNotificationMapper responseNotificationMapper;
    private final RequestNotificationMapper requestNotificationMapper;

    @Override
    public ResponseNotificationDTO createNotification(RequestNotificationDTO requestNotificationDTO) {
        Notification notification = requestNotificationMapper.toNotification(requestNotificationDTO);
        notification.setCreatedAt(LocalDateTime.now());
        notification = notificationRepository.save(notification);
        return responseNotificationMapper.toResponseNotificationDTO(notification);
    }

    @Override
    public Notification findById(Long id) {

        return notificationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Notification Not Found"));
    }

    @Override
    public ResponseNotificationDTO findNotificationById(Long id) {
        return responseNotificationMapper.toResponseNotificationDTO(findById(id));
    }

    @Override
    public List<ResponseNotificationDTO> listFailedNotifications() {
        List<Notification> failedNotifications = notificationRepository.findAllBySentIsFalse();
        return failedNotifications
                .stream()
                .map(responseNotificationMapper::toResponseNotificationDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseNotificationDTO updateNotificationById(Long id, RequestNotificationDTO requestNotificationDTO) {
        Notification notification = findById(id);
        Optional.ofNullable(requestNotificationDTO.getReceiverEmail()).ifPresent(notification::setReceiverEmail);
        Optional.ofNullable(requestNotificationDTO.getContent()).ifPresent(notification::setContent);
        Optional.ofNullable(requestNotificationDTO.getSent()).ifPresent(notification::setSent);
        return responseNotificationMapper.toResponseNotificationDTO(notificationRepository.save(notification));
    }

    @Override
    public ResponseNotificationDTO send(RequestNotificationDTO requestNotificationDTO) {
        ResponseNotificationDTO responseNotificationDTO = createNotification(requestNotificationDTO);
        sendToEmail(responseNotificationDTO);
        responseNotificationDTO.setSent(true);
        requestNotificationDTO = requestNotificationMapper.toRequestNotificationDTO(responseNotificationMapper.toNotification(responseNotificationDTO));
        return updateNotificationById(responseNotificationDTO.getId(), requestNotificationDTO);
    }

    @Override
    public void sendToEmail(ResponseNotificationDTO responseNotificationDTO) {
        String to = responseNotificationDTO.getReceiverEmail();
        String from = "basmala11gad@gmail.com";
        String host = "smtp.gmail.com";
        String username = "basmala11gad@gmail.com";
        String password = "adnz wplq rloe hpef";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(responseNotificationDTO.getSubject());
            message.setText(responseNotificationDTO.getContent());

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}


