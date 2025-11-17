package com.fream_v2.notification_service.domain.notification.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 알림 우선순위 값 객체
 */
@Getter
@RequiredArgsConstructor
public enum NotificationPriority {
    CRITICAL("긴급", "즉시 발송이 필요한 알림", 0),
    HIGH("높음", "중요한 알림", 1),
    NORMAL("보통", "일반적인 알림", 2),
    LOW("낮음", "중요도가 낮은 알림", 3);

    private final String name;
    private final String description;
    private final int order;

    /**
     * 즉시 발송 필요 여부
     */
    public boolean requiresImmediateSend() {
        return this == CRITICAL || this == HIGH;
    }

    /**
     * 방해 금지 모드 무시 여부
     */
    public boolean bypassDoNotDisturb() {
        return this == CRITICAL;
    }
}
