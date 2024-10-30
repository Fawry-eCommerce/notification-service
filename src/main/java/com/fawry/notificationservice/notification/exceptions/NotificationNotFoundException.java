package com.fawry.notificationservice.notification.exceptions;

import java.util.NoSuchElementException;

public class NotificationNotFoundException extends NoSuchElementException {
    public NotificationNotFoundException(String message) {
        super(message);
    }
}
