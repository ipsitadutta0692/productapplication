package co.ipsita.product.app;

public enum Category {
    PCS("PCS"),
    PUR ("PUR"),
    CAS("CAS");

    private String category;

    Category(String category) {
        this.category = category;
    }

    public String getCategory(){
        return this.category;
    }
}
