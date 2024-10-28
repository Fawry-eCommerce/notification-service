package com.fawry.notificationservice.notification.controllers;

import com.fawry.notificationservice.notification.dtos.RequestNotificationDTO;
import com.fawry.notificationservice.notification.dtos.ResponseNotificationDTO;
import com.fawry.notificationservice.notification.services.NotificationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("notifications")
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<ResponseNotificationDTO> createNotification(@Valid @RequestBody RequestNotificationDTO requestNotificationDTO){
        return ResponseEntity.status(HttpStatus.SC_CREATED).body(notificationService.createNotification(requestNotificationDTO));
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseNotificationDTO> findNotificationById(@PathVariable Long id){
        ResponseNotificationDTO responseNotificationDTO = notificationService.findNotificationById(id);
        return ResponseEntity.ok(responseNotificationDTO);
    }

    @GetMapping("failed")
    public ResponseEntity<List<ResponseNotificationDTO>> findAllFailedNotifications(){
        List<ResponseNotificationDTO> responseNotificationDTOs = notificationService.listFailedNotifications();
        return ResponseEntity.ok(responseNotificationDTOs);
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseNotificationDTO> updateNotificationById(@PathVariable Long id, @Valid @RequestBody RequestNotificationDTO requestNotificationDTO){
        ResponseNotificationDTO responseNotificationDTO = notificationService.updateNotificationById(id, requestNotificationDTO);
        return ResponseEntity.ok(responseNotificationDTO);
    }
}
