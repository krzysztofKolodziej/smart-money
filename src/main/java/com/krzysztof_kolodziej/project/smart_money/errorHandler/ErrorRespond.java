package com.krzysztof_kolodziej.project.smart_money.errorHandler;

import org.springframework.http.HttpStatusCode;

public record ErrorRespond(HttpStatusCode status, String message) {
}
