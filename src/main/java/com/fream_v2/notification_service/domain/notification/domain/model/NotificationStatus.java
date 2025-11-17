package com.fream_v2.notification_service.domain.notification.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 알림 상태 값 객체
 */
@Getter
@RequiredArgsConstructor
public enum NotificationStatus {
    PENDING("대기", "발송 대기 중"),
    QUEUED("큐 등록", "발송 큐에 등록됨"),
    SENDING("발송 중", "발송 진행 중"),
    SENT("발송 완료", "발송 완료"),
    DELIVERED("전달 완료", "수신자에게 전달 완료"),
    READ("읽음", "수신자가 읽음"),
    FAILED("실패", "발송 실패"),
    EXPIRED("만료", "유효 기간 만료"),
    CANCELLED("취소", "발송 취소");

    private final String name;
    private final String description;

    /**
     * 재시도 가능 상태
     */
    public boolean isRetryable() {
        return this == FAILED;
    }

    /**
     * 최종 상태 여부
     */
    public boolean isFinal() {
        return this == READ || this == EXPIRED || this == CANCELLED;
    }

    /**
     * 진행 중 상태
     */
    public boolean isInProgress() {
        return this == QUEUED || this == SENDING;
    }
}
