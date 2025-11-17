package com.fream_v2.notification_service.domain.notification.infrastructure.persistence.entity;

import com.fream_v2.notification_service.domain.notification.domain.model.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDateTime;

/**
 * 알림 엔티티 (Infrastructure Layer)
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("notifications")
public class NotificationEntity {
    @Id
    private Long id;
    private Long userId;
    private String type;
    private String channel;
    private String priority;
    private String title;
    private String message;
    private String deepLink;
    private String metadata; // JSON 문자열로 저장
    private String status;
    private Integer retryCount;
    private LocalDateTime createdAt;
    private LocalDateTime sentAt;
    private LocalDateTime readAt;
    private LocalDateTime expiredAt;

    /**
     * 도메인 모델로 변환
     */
    public Notification toDomain() {
        return Notification.builder()
                .notificationId(this.id)
                .userId(this.userId)
                .type(NotificationType.valueOf(this.type))
                .channel(NotificationChannel.valueOf(this.channel))
                .priority(NotificationPriority.valueOf(this.priority))
                .title(this.title)
                .message(this.message)
                .deepLink(this.deepLink)
                .metadata(parseMetadata(this.metadata))
                .status(NotificationStatus.valueOf(this.status))
                .retryCount(this.retryCount)
                .createdAt(this.createdAt)
                .sentAt(this.sentAt)
                .readAt(this.readAt)
                .expiredAt(this.expiredAt)
                .build();
    }

    /**
     * 도메인 모델로부터 엔티티 생성 (신규)
     */
    public static NotificationEntity fromDomain(Notification notification) {
        return NotificationEntity.builder()
                .userId(notification.getUserId())
                .type(notification.getType().name())
                .channel(notification.getChannel().name())
                .priority(notification.getPriority().name())
                .title(notification.getTitle())
                .message(notification.getMessage())
                .deepLink(notification.getDeepLink())
                .metadata(toJsonString(notification.getMetadata()))
                .status(notification.getStatus().name())
                .retryCount(notification.getRetryCount())
                .createdAt(notification.getCreatedAt())
                .sentAt(notification.getSentAt())
                .readAt(notification.getReadAt())
                .expiredAt(notification.getExpiredAt())
                .build();
    }

    /**
     * 도메인 모델로부터 엔티티 업데이트 (기존 ID 포함)
     */
    public static NotificationEntity updateFromDomain(Notification notification) {
        return NotificationEntity.builder()
                .id(notification.getNotificationId())
                .userId(notification.getUserId())
                .type(notification.getType().name())
                .channel(notification.getChannel().name())
                .priority(notification.getPriority().name())
                .title(notification.getTitle())
                .message(notification.getMessage())
                .deepLink(notification.getDeepLink())
                .metadata(toJsonString(notification.getMetadata()))
                .status(notification.getStatus().name())
                .retryCount(notification.getRetryCount())
                .createdAt(notification.getCreatedAt())
                .sentAt(notification.getSentAt())
                .readAt(notification.getReadAt())
                .expiredAt(notification.getExpiredAt())
                .build();
    }

    // Metadata JSON 변환 헬퍼 메서드
    private static String toJsonString(Object metadata) {
        // 실제 구현 시 ObjectMapper 사용
        return metadata != null ? metadata.toString() : null;
    }

    private static java.util.Map<String, Object> parseMetadata(String json) {
        // 실제 구현 시 ObjectMapper 사용
        return json != null ? new java.util.HashMap<>() : null;
    }
}