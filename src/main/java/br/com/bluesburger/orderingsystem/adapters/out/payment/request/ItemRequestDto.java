package br.com.bluesburger.orderingsystem.adapters.out.payment.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class ItemRequestDto {

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("category")
    private String category;

    @JsonProperty("unit_price")
    private BigDecimal unitPrice;

    @JsonProperty("quantity")
    private Integer quantity;

    @JsonProperty("unit_measure")
    private String unitMeasure;

    @JsonProperty("total_amount")
    private BigDecimal totalAmount;

}
