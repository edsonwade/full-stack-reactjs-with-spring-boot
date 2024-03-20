package code.with.vanilson.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderDTO implements Serializable {
    private static final Long serialVersionUID = 1L;
    @NotNull
    private List<OrderLineItemDTO> orderLineItemDTOList;


}
