package code.with.vanilson.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderLineItemDTO implements Serializable {
    private static final Long serialVersionUID = 1L;
    @NotNull
    private String skuCode;
    @NotNull
    private BigDecimal price;
    @NotNull
    @Size(max = 200)
    private Integer Quantity;


}
