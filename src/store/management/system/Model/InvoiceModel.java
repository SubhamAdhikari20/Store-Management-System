package store.management.system.Model;



import java.math.BigDecimal;

public class InvoiceModel {
    private int invoiceID;
    private int productID;
    private String productName;
    private String category;
    private BigDecimal price;
    private BigDecimal total;
    private int quantity;
    
    // Constructor
    public InvoiceModel(int invoiceID, int productID, String productName, String category, int quantity, BigDecimal price,BigDecimal total) {
        this.invoiceID = invoiceID;
        this.productID = productID;
        this.productName = productName;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }
    
    
    // Getters
    public int getInvoiceID() {
        return this.invoiceID;
    }
    
    public int getProductID() {
        return this.productID;
    }
    
    public String getProductName() {
        return this.productName;
    }

    public String getCategoryField() {
        return this.category;
    }

    public int getQuantityField() {
        return this.quantity;
    }
    
    public BigDecimal getPriceField() {
        return this.price;
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
    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public void setProductID(int productId) {
        this.productID = productId;
    }
    
    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setCategoryField(String category) {
        this.category = category;
    }

   public void setQuantityField(int quantity) {
        this.quantity = quantity;
    }
    
    public void setPriceField(BigDecimal price) {
        this.price = price;
    }
   
    public void setTotalField(BigDecimal total) {
        this.total = total;
    }
}
