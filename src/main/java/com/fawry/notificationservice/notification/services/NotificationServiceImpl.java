package com.fawry.notificationservice.notification.services;

import com.fawry.notificationservice.notification.dtos.RequestNotificationDTO;
import com.fawry.notificationservice.notification.dtos.ResponseNotificationDTO;
import com.fawry.notificationservice.notification.entities.Notification;
import com.fawry.notificationservice.notification.exceptions.NotificationNotFoundException;
import com.fawry.notificationservice.notification.mappers.RequestNotificationMapper;
import com.fawry.notificationservice.notification.mappers.ResponseNotificationMapper;
import com.fawry.notificationservice.notification.repositories.NotificationRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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

        return notificationRepository.findById(id).orElseThrow(() -> new NotificationNotFoundException("Notification Not Found"));
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
}
