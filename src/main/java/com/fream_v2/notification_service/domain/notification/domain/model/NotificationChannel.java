package com.fream_v2.notification_service.domain.notification.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.util.Set;

/**
 * 알림 채널 값 객체
 */
@Getter
@RequiredArgsConstructor
public enum NotificationChannel {
    PUSH("푸시 알림", "모바일 앱 푸시 알림", true),
    EMAIL("이메일", "이메일 알림", true),
    SMS("문자 메시지", "SMS 문자 알림", true),
    IN_APP("인앱 알림", "앱 내 알림센터", false),
    ALL("모든 채널", "모든 알림 채널로 발송", true);

    private final String name;
    private final String description;
    private final boolean requiresExternalService;

    /**
     * 실제 발송 채널 목록 반환
     */
    public Set<NotificationChannel> getActualChannels() {
        if (this == ALL) {
            return Set.of(PUSH, EMAIL, SMS, IN_APP);
        }
        return Set.of(this);
    }

    /**
     * 외부 서비스 필요 여부
     */
    public boolean needsExternalService() {
        return this.requiresExternalService;
    }

    /**
     * 즉시 발송 가능 여부
     */
    public boolean isInstant() {
        return this == PUSH || this == IN_APP;
    }
}
