package com.fream_v2.notification_service.domain.notification.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 알림 타입 값 객체
 */
@Getter
@RequiredArgsConstructor
public enum NotificationType {

    // ===== 구매자 알림 =====
    PAYMENT_COMPLETED("결제 완료", "구매자의 결제가 완료되었을 때", "payment"),
    PAYMENT_FAILED("결제 실패", "구매자의 결제가 실패했을 때", "payment"),
    SHIPMENT_STARTED("발송 시작", "판매자가 상품을 발송했을 때", "shipment"),
    SHIPMENT_IN_TRANSIT("배송 중", "상품이 배송 중일 때", "shipment"),
    SHIPMENT_DELIVERED("배송 완료", "상품이 배송 완료되었을 때", "shipment"),
    SELL_REQUEST_RECEIVED("판매 요청 도착", "판매자가 구매 요청에 판매 제안을 했을 때", "match"),
    PRICE_DROP("가격 인하", "관심 상품의 가격이 인하되었을 때", "product"),

    // ===== 판매자 알림 =====
    BUY_REQUEST_RECEIVED("구매 요청 도착", "구매자가 판매 상품에 구매 요청을 했을 때", "match"),
    PAYMENT_CONFIRMED("결제 확인", "구매자의 결제가 확인되었을 때", "payment"),
    SETTLEMENT_COMPLETED("정산 완료", "판매 대금이 정산되었을 때", "payment"),
    PRODUCT_SOLD("상품 판매 완료", "상품 거래가 완료되었을 때", "trade"),

    // ===== 공통 알림 =====
    MATCH_COMPLETED("매칭 완료", "구매자와 판매자가 매칭되었을 때", "match"),
    MATCH_CANCELLED("매칭 취소", "매칭이 취소되었을 때", "match"),
    ORDER_CANCELLED("주문 취소", "주문이 취소되었을 때", "order"),
    REVIEW_REQUESTED("리뷰 요청", "거래 후 리뷰 작성 요청", "review"),
    REVIEW_RECEIVED("리뷰 도착", "상대방이 리뷰를 작성했을 때", "review"),

    // ===== 시스템 알림 =====
    SYSTEM_NOTICE("시스템 공지", "시스템 공지사항", "system"),
    SYSTEM_MAINTENANCE("시스템 점검", "시스템 점검 안내", "system"),
    PROMOTION("프로모션", "이벤트 및 프로모션 안내", "promotion"),
    GRADE_UPGRADED("등급 상승", "사용자 등급이 상승했을 때", "user"),
    VERIFICATION_REQUIRED("인증 필요", "추가 인증이 필요할 때", "user");

    private final String title;
    private final String description;
    private final String category;

    /**
     * 카테고리별 알림 필터링
     */
    public boolean isPaymentRelated() {
        return "payment".equals(this.category);
    }

    public boolean isShipmentRelated() {
        return "shipment".equals(this.category);
    }

    public boolean isMatchRelated() {
        return "match".equals(this.category);
    }

    public boolean isSystemRelated() {
        return "system".equals(this.category);
    }

    /**
     * 중요도 판단
     */
    public boolean isCritical() {
        return this == PAYMENT_COMPLETED || this == PAYMENT_FAILED ||
                this == MATCH_COMPLETED || this == ORDER_CANCELLED;
    }
}