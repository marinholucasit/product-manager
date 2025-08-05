package model;

import entity.Product;

import java.util.List;

public class PaginatedProducts {
    private final List<Product> products;
    private final int currentPage;
    private final int totalPages;
    private final long totalItems;

    public PaginatedProducts(List<Product> products, int currentPage, int totalPages, long totalItems) {
        this.products = products;
        this.currentPage = currentPage;
        this.totalPages = totalPages;
        this.totalItems = totalItems;
    }

    public List<Product> getProducts() {
        return products;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public long getTotalItems() {
        return totalItems;
    }
}
