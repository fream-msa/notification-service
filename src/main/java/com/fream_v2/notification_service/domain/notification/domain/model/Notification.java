package com.fream_v2.notification_service.domain.notification.domain.model;

import lombok.Builder;
import lombok.Getter;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * 알림 도메인 모델 (순수 도메인)
 */
@Getter
@Builder
public class Notification {
    private final Long notificationId;
    private final Long userId;
    private final NotificationType type;
    private final NotificationChannel channel;
    private final NotificationPriority priority;
    private final String title;
    private final String message;
    private final String deepLink;
    private final Map<String, Object> metadata;
    private final NotificationStatus status;
    private final Integer retryCount;
    private final LocalDateTime createdAt;
    private final LocalDateTime sentAt;
    private final LocalDateTime readAt;
    private final LocalDateTime expiredAt;

    /**
     * 알림 생성
     */
    public static Notification createNotification(Long userId, NotificationType type,
                                                  NotificationChannel channel,
                                                  NotificationPriority priority,
                                                  String title, String message,
                                                  Map<String, Object> metadata) {
        return Notification.builder()
                .userId(userId)
                .type(type)
                .channel(channel)
                .priority(priority)
                .title(title)
                .message(message)
                .metadata(metadata)
                .status(NotificationStatus.PENDING)
                .retryCount(0)
                .createdAt(LocalDateTime.now())
                .expiredAt(LocalDateTime.now().plusDays(7))
                .build();
    }

    /**
     * 결제 완료 알림 생성
     */
    public static Notification createPaymentCompleteNotification(Long userId, Long orderId,
                                                                 Long amount) {
        Map<String, Object> metadata = Map.of(
                "orderId", orderId,
                "amount", amount,
                "type", "PAYMENT_COMPLETE"
        );

        return createNotification(
                userId,
                NotificationType.PAYMENT_COMPLETED,
                NotificationChannel.ALL,
                NotificationPriority.HIGH,
                "결제 완료",
                String.format("%,d원 결제가 완료되었습니다.", amount),
                metadata
        );
    }

    /**
     * 구매 요청 알림 생성
     */
    public static Notification createBuyRequestNotification(Long sellerId, Long buyerId,
                                                            String productName, Long price) {
        Map<String, Object> metadata = Map.of(
                "buyerId", buyerId,
                "productName", productName,
                "price", price,
                "type", "BUY_REQUEST"
        );

        return createNotification(
                sellerId,
                NotificationType.BUY_REQUEST_RECEIVED,
                NotificationChannel.ALL,
                NotificationPriority.HIGH,
                "구매 요청",
                String.format("%s 상품에 구매 요청이 있습니다. (%,d원)", productName, price),
                metadata
        );
    }

    /**
     * 발송 시작 알림 생성
     */
    public static Notification createShipmentStartNotification(Long userId, String trackingNumber,
                                                               String courierCompany) {
        Map<String, Object> metadata = Map.of(
                "trackingNumber", trackingNumber,
                "courierCompany", courierCompany,
                "type", "SHIPMENT_START"
        );

        return createNotification(
                userId,
                NotificationType.SHIPMENT_STARTED,
                NotificationChannel.ALL,
                NotificationPriority.NORMAL,
                "발송 시작",
                String.format("상품이 발송되었습니다. 운송장: %s (%s)", trackingNumber, courierCompany),
                metadata
        );
    }

    /**
     * 알림 발송 완료
     */
    public Notification markAsSent() {
        return Notification.builder()
                .notificationId(this.notificationId)
                .userId(this.userId)
                .type(this.type)
                .channel(this.channel)
                .priority(this.priority)
                .title(this.title)
                .message(this.message)
                .deepLink(this.deepLink)
                .metadata(this.metadata)
                .status(NotificationStatus.SENT)
                .retryCount(this.retryCount)
                .createdAt(this.createdAt)
                .sentAt(LocalDateTime.now())
                .readAt(this.readAt)
                .expiredAt(this.expiredAt)
                .build();
    }

    /**
     * 알림 읽음 처리
     */
    public Notification markAsRead() {
        return Notification.builder()
                .notificationId(this.notificationId)
                .userId(this.userId)
                .type(this.type)
                .channel(this.channel)
                .priority(this.priority)
                .title(this.title)
                .message(this.message)
                .deepLink(this.deepLink)
                .metadata(this.metadata)
                .status(NotificationStatus.READ)
                .retryCount(this.retryCount)
                .createdAt(this.createdAt)
                .sentAt(this.sentAt)
                .readAt(LocalDateTime.now())
                .expiredAt(this.expiredAt)
                .build();
    }

    /**
     * 발송 실패 처리
     */
    public Notification markAsFailed() {
        return Notification.builder()
                .notificationId(this.notificationId)
                .userId(this.userId)
                .type(this.type)
                .channel(this.channel)
                .priority(this.priority)
                .title(this.title)
                .message(this.message)
                .deepLink(this.deepLink)
                .metadata(this.metadata)
                .status(NotificationStatus.FAILED)
                .retryCount(this.retryCount + 1)
                .createdAt(this.createdAt)
                .sentAt(this.sentAt)
                .readAt(this.readAt)
                .expiredAt(this.expiredAt)
                .build();
    }

    /**
     * 재시도 가능 여부
     */
    public boolean canRetry() {
        return this.status == NotificationStatus.FAILED && this.retryCount < 3;
    }

    /**
     * 만료 여부
     */
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.expiredAt);
    }

    /**
     * 발송 가능 여부
     */
    public boolean isSendable() {
        return this.status == NotificationStatus.PENDING && !isExpired();
    }
}