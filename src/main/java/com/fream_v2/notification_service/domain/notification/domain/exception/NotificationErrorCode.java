package com.fream_v2.notification_service.domain.notification.domain.exception;

import com.fream_v2.notification_service.global.presentation.exception.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 알림 도메인 에러 코드
 */
@Getter
@RequiredArgsConstructor
public enum NotificationErrorCode implements ErrorCode {

    // ===== 알림 조회 관련 (NOTI_001 ~ NOTI_099) =====
    NOTIFICATION_NOT_FOUND("NOTI_001", "알림을 찾을 수 없습니다.", 404),
    NOTIFICATION_ID_NOT_FOUND("NOTI_002", "해당 ID의 알림을 찾을 수 없습니다.", 404),
    USER_NOTIFICATIONS_NOT_FOUND("NOTI_003", "사용자의 알림을 찾을 수 없습니다.", 404),
    NOTIFICATION_DATA_CORRUPTED("NOTI_004", "알림 데이터가 손상되었습니다.", 500),

    // ===== 알림 생성 관련 (NOTI_100 ~ NOTI_199) =====
    NOTIFICATION_CREATION_FAILED("NOTI_100", "알림 생성에 실패했습니다.", 500),
    INVALID_NOTIFICATION_TYPE("NOTI_101", "유효하지 않은 알림 타입입니다.", 400),
    INVALID_NOTIFICATION_CHANNEL("NOTI_102", "유효하지 않은 알림 채널입니다.", 400),
    RECIPIENT_NOT_FOUND("NOTI_103", "수신자를 찾을 수 없습니다.", 404),
    TEMPLATE_NOT_FOUND("NOTI_104", "알림 템플릿을 찾을 수 없습니다.", 404),
    DUPLICATE_NOTIFICATION("NOTI_105", "중복된 알림입니다.", 409),
    NOTIFICATION_CONTENT_EMPTY("NOTI_106", "알림 내용이 비어있습니다.", 400),
    TITLE_TOO_LONG("NOTI_107", "알림 제목이 너무 깁니다.", 400),
    MESSAGE_TOO_LONG("NOTI_108", "알림 메시지가 너무 깁니다.", 400),
    INVALID_METADATA("NOTI_109", "유효하지 않은 메타데이터입니다.", 400),

    // ===== 알림 발송 관련 (NOTI_200 ~ NOTI_299) =====
    NOTIFICATION_SEND_FAILED("NOTI_200", "알림 발송에 실패했습니다.", 500),
    ALREADY_SENT("NOTI_201", "이미 발송된 알림입니다.", 409),
    CHANNEL_NOT_AVAILABLE("NOTI_202", "사용할 수 없는 알림 채널입니다.", 400),
    RECIPIENT_BLOCKED("NOTI_203", "수신 차단된 사용자입니다.", 403),
    NOTIFICATION_EXPIRED("NOTI_204", "만료된 알림입니다.", 400),
    SEND_LIMIT_EXCEEDED("NOTI_205", "알림 발송 한도를 초과했습니다.", 429),
    RETRY_LIMIT_EXCEEDED("NOTI_206", "재시도 횟수를 초과했습니다.", 429),
    NOTIFICATION_QUEUE_FULL("NOTI_207", "알림 큐가 가득 찼습니다.", 503),
    SCHEDULED_TIME_INVALID("NOTI_208", "예약 발송 시간이 유효하지 않습니다.", 400),

    // ===== 푸시 알림 관련 (NOTI_300 ~ NOTI_349) =====
    PUSH_TOKEN_INVALID("NOTI_300", "유효하지 않은 푸시 토큰입니다.", 400),
    PUSH_TOKEN_EXPIRED("NOTI_301", "만료된 푸시 토큰입니다.", 400),
    PUSH_SERVICE_ERROR("NOTI_302", "푸시 서비스 오류가 발생했습니다.", 502),
    FCM_CONNECTION_ERROR("NOTI_303", "FCM 연결 오류가 발생했습니다.", 502),
    APNS_CONNECTION_ERROR("NOTI_304", "APNS 연결 오류가 발생했습니다.", 502),
    PUSH_PAYLOAD_TOO_LARGE("NOTI_305", "푸시 페이로드가 너무 큽니다.", 400),

    // ===== 이메일 알림 관련 (NOTI_350 ~ NOTI_399) =====
    INVALID_EMAIL_ADDRESS("NOTI_350", "유효하지 않은 이메일 주소입니다.", 400),
    EMAIL_SEND_FAILED("NOTI_351", "이메일 발송에 실패했습니다.", 500),
    EMAIL_TEMPLATE_ERROR("NOTI_352", "이메일 템플릿 처리 오류가 발생했습니다.", 500),
    SMTP_CONNECTION_ERROR("NOTI_353", "SMTP 연결 오류가 발생했습니다.", 502),
    EMAIL_BOUNCE("NOTI_354", "이메일이 반송되었습니다.", 400),
    EMAIL_SPAM_DETECTED("NOTI_355", "스팸으로 분류된 이메일입니다.", 400),

    // ===== SMS 알림 관련 (NOTI_400 ~ NOTI_449) =====
    INVALID_PHONE_NUMBER("NOTI_400", "유효하지 않은 전화번호입니다.", 400),
    SMS_SEND_FAILED("NOTI_401", "SMS 발송에 실패했습니다.", 500),
    SMS_CARRIER_ERROR("NOTI_402", "통신사 오류가 발생했습니다.", 502),
    SMS_LENGTH_EXCEEDED("NOTI_403", "SMS 길이를 초과했습니다.", 400),
    SMS_BLOCKED_NUMBER("NOTI_404", "차단된 전화번호입니다.", 403),
    SMS_BALANCE_INSUFFICIENT("NOTI_405", "SMS 발송 잔액이 부족합니다.", 402),

    // ===== 알림 읽음/삭제 관련 (NOTI_500 ~ NOTI_599) =====
    ALREADY_READ("NOTI_500", "이미 읽은 알림입니다.", 409),
    CANNOT_MARK_AS_READ("NOTI_501", "읽음 처리할 수 없는 알림입니다.", 400),
    ALREADY_DELETED("NOTI_502", "이미 삭제된 알림입니다.", 409),
    CANNOT_DELETE("NOTI_503", "삭제할 수 없는 알림입니다.", 400),
    BULK_OPERATION_FAILED("NOTI_504", "일괄 처리에 실패했습니다.", 500),

    // ===== 알림 설정 관련 (NOTI_600 ~ NOTI_699) =====
    NOTIFICATION_DISABLED("NOTI_600", "알림이 비활성화되어 있습니다.", 403),
    CHANNEL_DISABLED("NOTI_601", "해당 채널의 알림이 비활성화되어 있습니다.", 403),
    INVALID_NOTIFICATION_SETTINGS("NOTI_602", "유효하지 않은 알림 설정입니다.", 400),
    SETTINGS_UPDATE_FAILED("NOTI_603", "알림 설정 업데이트에 실패했습니다.", 500),
    DO_NOT_DISTURB_ACTIVE("NOTI_604", "방해 금지 모드가 활성화되어 있습니다.", 403),

    // ===== 템플릿 관련 (NOTI_700 ~ NOTI_799) =====
    TEMPLATE_PARSING_ERROR("NOTI_700", "템플릿 파싱 오류가 발생했습니다.", 500),
    TEMPLATE_VARIABLE_MISSING("NOTI_701", "필수 템플릿 변수가 누락되었습니다.", 400),
    TEMPLATE_NOT_APPROVED("NOTI_702", "승인되지 않은 템플릿입니다.", 403),
    TEMPLATE_EXPIRED("NOTI_703", "만료된 템플릿입니다.", 400),

    // ===== 외부 서비스 연동 관련 (NOTI_800 ~ NOTI_899) =====
    EXTERNAL_SERVICE_ERROR("NOTI_800", "외부 알림 서비스 오류가 발생했습니다.", 502),
    SERVICE_AUTHENTICATION_FAILED("NOTI_801", "서비스 인증에 실패했습니다.", 401),
    SERVICE_QUOTA_EXCEEDED("NOTI_802", "서비스 할당량을 초과했습니다.", 429),
    SERVICE_UNAVAILABLE("NOTI_803", "알림 서비스를 사용할 수 없습니다.", 503),
    API_KEY_INVALID("NOTI_804", "유효하지 않은 API 키입니다.", 401),

    // ===== 검증 관련 (NOTI_900 ~ NOTI_999) =====
    NOTIFICATION_DATA_INVALID("NOTI_900", "알림 데이터가 유효하지 않습니다.", 400),
    REQUIRED_FIELD_MISSING("NOTI_901", "필수 입력 항목이 누락되었습니다.", 400),
    INVALID_DEEP_LINK("NOTI_902", "유효하지 않은 딥링크입니다.", 400),
    INVALID_PRIORITY("NOTI_903", "유효하지 않은 우선순위입니다.", 400);

    private final String code;
    private final String message;
    private final int status;
}