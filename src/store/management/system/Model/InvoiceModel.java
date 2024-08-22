package store.management.system.Model;



import java.math.BigDecimal;

public class InvoiceModel {
    private String productField;
    private String category;
    private BigDecimal total;
    private int quantity;
    
    // Constructor
    public InvoiceModel(String productField, String category, int quantity, BigDecimal total) {
        this.productField = productField;
        this.category = category;
        this.quantity = quantity;
        this.total = total;
    }
    
    
    // Getters
    public String getProductField() {
        return this.productField;
    }

    public String getCategoryField() {
        return this.category;
    }

    public int getQuantityField() {
        return this.quantity;
    }
    
    public BigDecimal getTotalField() {
        return this.total;
    }
    
    
    // Method to calculate the total amount
    public void calculateTotal(BigDecimal pricePerUnit) {
        if (pricePerUnit != null) {
            this.total = pricePerUnit.multiply(BigDecimal.valueOf(this.quantity));
        }
    }
    

    // Setters
    public void setProductField(String productId) {
        this.productField = productId;
    }

    public void setCategoryField(String category) {
        this.category = category;
    }

   public void setQuantityField(int quantity) {
        this.quantity = quantity;
    }
   
    public void setTotalField(BigDecimal total) {
        this.total = total;
    }
}
