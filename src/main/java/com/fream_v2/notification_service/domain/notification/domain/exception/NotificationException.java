package com.fream_v2.notification_service.domain.notification.domain.exception;

import com.fream_v2.notification_service.global.presentation.exception.ErrorCode;
import com.fream_v2.notification_service.global.presentation.exception.GlobalException;

/**
 * 알림 도메인 예외
 */
public class NotificationException extends GlobalException {

    public NotificationException(ErrorCode errorCode) {
        super(errorCode);
    }

    public NotificationException(ErrorCode errorCode, String message) {
        super(errorCode, message);
    }

    public NotificationException(ErrorCode errorCode, Throwable cause) {
        super(errorCode, cause);
    }

    public NotificationException(ErrorCode errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    // ===== 자주 사용되는 예외 생성 정적 팩토리 메서드 =====

    // 조회 관련
    public static NotificationException notFound() {
        return new NotificationException(NotificationErrorCode.NOTIFICATION_NOT_FOUND);
    }

    public static NotificationException notFound(Long notificationId) {
        return new NotificationException(NotificationErrorCode.NOTIFICATION_ID_NOT_FOUND,
                String.format("알림을 찾을 수 없습니다. ID: %d", notificationId));
    }

    // 생성 관련
    public static NotificationException invalidType(String type) {
        return new NotificationException(NotificationErrorCode.INVALID_NOTIFICATION_TYPE,
                String.format("유효하지 않은 알림 타입입니다: %s", type));
    }

    public static NotificationException invalidChannel(String channel) {
        return new NotificationException(NotificationErrorCode.INVALID_NOTIFICATION_CHANNEL,
                String.format("유효하지 않은 알림 채널입니다: %s", channel));
    }

    public static NotificationException recipientNotFound(Long userId) {
        return new NotificationException(NotificationErrorCode.RECIPIENT_NOT_FOUND,
                String.format("수신자를 찾을 수 없습니다. 사용자 ID: %d", userId));
    }

    public static NotificationException duplicateNotification(Long userId, String type) {
        return new NotificationException(NotificationErrorCode.DUPLICATE_NOTIFICATION,
                String.format("중복된 알림입니다. 사용자 ID: %d, 타입: %s", userId, type));
    }

    // 발송 관련
    public static NotificationException sendFailed(Long notificationId) {
        return new NotificationException(NotificationErrorCode.NOTIFICATION_SEND_FAILED,
                String.format("알림 발송에 실패했습니다. ID: %d", notificationId));
    }

    public static NotificationException alreadySent(Long notificationId) {
        return new NotificationException(NotificationErrorCode.ALREADY_SENT,
                String.format("이미 발송된 알림입니다. ID: %d", notificationId));
    }

    public static NotificationException recipientBlocked(Long userId) {
        return new NotificationException(NotificationErrorCode.RECIPIENT_BLOCKED,
                String.format("수신 차단된 사용자입니다. ID: %d", userId));
    }

    public static NotificationException expired(Long notificationId) {
        return new NotificationException(NotificationErrorCode.NOTIFICATION_EXPIRED,
                String.format("만료된 알림입니다. ID: %d", notificationId));
    }

    public static NotificationException sendLimitExceeded(Long userId, int limit) {
        return new NotificationException(NotificationErrorCode.SEND_LIMIT_EXCEEDED,
                String.format("알림 발송 한도를 초과했습니다. 사용자 ID: %d, 한도: %d", userId, limit));
    }

    // 푸시 알림 관련
    public static NotificationException invalidPushToken(String token) {
        return new NotificationException(NotificationErrorCode.PUSH_TOKEN_INVALID,
                String.format("유효하지 않은 푸시 토큰입니다: %s", maskToken(token)));
    }

    public static NotificationException pushServiceError(String service) {
        return new NotificationException(NotificationErrorCode.PUSH_SERVICE_ERROR,
                String.format("푸시 서비스 오류가 발생했습니다: %s", service));
    }

    // 이메일 관련
    public static NotificationException invalidEmail(String email) {
        return new NotificationException(NotificationErrorCode.INVALID_EMAIL_ADDRESS,
                String.format("유효하지 않은 이메일 주소입니다: %s", email));
    }

    public static NotificationException emailSendFailed(String email) {
        return new NotificationException(NotificationErrorCode.EMAIL_SEND_FAILED,
                String.format("이메일 발송에 실패했습니다: %s", email));
    }

    // SMS 관련
    public static NotificationException invalidPhoneNumber(String phoneNumber) {
        return new NotificationException(NotificationErrorCode.INVALID_PHONE_NUMBER,
                String.format("유효하지 않은 전화번호입니다: %s", maskPhoneNumber(phoneNumber)));
    }

    public static NotificationException smsSendFailed(String phoneNumber) {
        return new NotificationException(NotificationErrorCode.SMS_SEND_FAILED,
                String.format("SMS 발송에 실패했습니다: %s", maskPhoneNumber(phoneNumber)));
    }

    public static NotificationException smsLengthExceeded(int length, int maxLength) {
        return new NotificationException(NotificationErrorCode.SMS_LENGTH_EXCEEDED,
                String.format("SMS 길이를 초과했습니다. 현재: %d, 최대: %d", length, maxLength));
    }

    // 알림 설정 관련
    public static NotificationException notificationDisabled(Long userId) {
        return new NotificationException(NotificationErrorCode.NOTIFICATION_DISABLED,
                String.format("알림이 비활성화되어 있습니다. 사용자 ID: %d", userId));
    }

    public static NotificationException channelDisabled(Long userId, String channel) {
        return new NotificationException(NotificationErrorCode.CHANNEL_DISABLED,
                String.format("해당 채널의 알림이 비활성화되어 있습니다. 사용자 ID: %d, 채널: %s",
                        userId, channel));
    }

    // 템플릿 관련
    public static NotificationException templateNotFound(String templateId) {
        return new NotificationException(NotificationErrorCode.TEMPLATE_NOT_FOUND,
                String.format("알림 템플릿을 찾을 수 없습니다: %s", templateId));
    }

    public static NotificationException templateVariableMissing(String variable) {
        return new NotificationException(NotificationErrorCode.TEMPLATE_VARIABLE_MISSING,
                String.format("필수 템플릿 변수가 누락되었습니다: %s", variable));
    }

    // 외부 서비스 관련
    public static NotificationException externalServiceError(String service) {
        return new NotificationException(NotificationErrorCode.EXTERNAL_SERVICE_ERROR,
                String.format("외부 알림 서비스 오류가 발생했습니다: %s", service));
    }

    public static NotificationException serviceQuotaExceeded(String service) {
        return new NotificationException(NotificationErrorCode.SERVICE_QUOTA_EXCEEDED,
                String.format("서비스 할당량을 초과했습니다: %s", service));
    }

    // 헬퍼 메서드
    private static String maskToken(String token) {
        if (token == null || token.length() < 8) {
            return "****";
        }
        return token.substring(0, 4) + "****";
    }

    private static String maskPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.length() < 8) {
            return "****";
        }
        return phoneNumber.substring(0, 3) + "****" + phoneNumber.substring(phoneNumber.length() - 4);
    }
}