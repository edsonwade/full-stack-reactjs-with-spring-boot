package code.with.vanilson.persistence.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "tb_order_line_item")
public class OrderLineItem implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_items_id",
            nullable = false,
            length = 11)
    @JsonProperty("id")
    private Integer order_items_id;
    @Column(name = "sku_code ",
            nullable = false,
            length = 250)
    private String skuCode;
    @Column(nullable = false,
            precision = 6,
            scale = 2)
    private BigDecimal price;

    private Integer quantity;

    public OrderLineItem() {
    }

    public OrderLineItem(String skuCode, BigDecimal price, Integer quantity) {
        this.skuCode = skuCode;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getOrder_items_id() {
        return order_items_id;
    }

    public void setOrder_items_id(Integer order_items_id) {
        this.order_items_id = order_items_id;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}
        OrderLineItem that = (OrderLineItem) o;
        return Objects.equals(order_items_id, that.order_items_id) &&
                Objects.equals(skuCode, that.skuCode) &&
                Objects.equals(price, that.price) &&
                Objects.equals(quantity, that.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(order_items_id, skuCode, price, quantity);
    }

    @Override
    public String toString() {
        return "OrderLineItem{" +
                "order_items_id=" + order_items_id +
                ", skuCode='" + skuCode + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}
