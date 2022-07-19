package br.com.alura.ecommerce;

import org.apache.kafka.common.MetricName;

public enum ProducersNames {
    ECOMMERCE,
    ECOMMERCE_SEND_EMAIL,
    ECOMMERCE_NEW_ORDER,
    ECOMMERCE_ORDER_REJECTED,
    ECOMMERCE_ORDER_APROVED;
}
