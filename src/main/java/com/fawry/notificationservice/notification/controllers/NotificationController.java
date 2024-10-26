package com.fawry.notificationservice.notification.controllers;

import com.fawry.notificationservice.notification.dtos.RequestNotificationDTO;
import com.fawry.notificationservice.notification.dtos.ResponseNotificationDTO;
import com.fawry.notificationservice.notification.services.NotificationService;
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
    public ResponseEntity<ResponseNotificationDTO> createNotification(@RequestBody RequestNotificationDTO requestNotificationDTO){
        return ResponseEntity.status(HttpStatus.SC_CREATED).body(notificationService.createNotification(requestNotificationDTO));
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseNotificationDTO> findNotificationById(@PathVariable Long id){
        ResponseNotificationDTO responseNotificationDTO = notificationService.findNotificationById(id);
        if(responseNotificationDTO != null) {
            return ResponseEntity.ok(responseNotificationDTO);
        }
        return (ResponseEntity<ResponseNotificationDTO>) ResponseEntity.badRequest();
    }

    @GetMapping("failed")
    public ResponseEntity<List<ResponseNotificationDTO>> findAllFailedNotifications(){
        List<ResponseNotificationDTO> responseNotificationDTOs = notificationService.listFailedNotifications();
        if(responseNotificationDTOs != null){
            return ResponseEntity.ok(responseNotificationDTOs);
        }
        return null;
    }

    @PutMapping("{id}")
    public ResponseEntity<ResponseNotificationDTO> updateNotificationById(@PathVariable Long id, @RequestBody RequestNotificationDTO requestNotificationDTO){
        ResponseNotificationDTO responseNotificationDTO = notificationService.updateNotificationById(id, requestNotificationDTO);
        if(responseNotificationDTO != null) {
            return ResponseEntity.ok(responseNotificationDTO);
        }
        return (ResponseEntity<ResponseNotificationDTO>) ResponseEntity.badRequest();
    }
}
