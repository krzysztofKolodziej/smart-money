package com.krzysztof_kolodziej.project.smart_money.model;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Currency {
    private String currency;
    private String code;
    private float mid;
}
