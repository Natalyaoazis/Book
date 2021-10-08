package ru.netology.manager;


import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import java.util.Arrays;

public class ProductManager {
    private ProductRepository repository;

    public ProductManager(ProductRepository repository) {
        this.repository = repository;
    }

    public void add(Product product) {
        repository.save((product));
    }

    public Product[] searchBy(String text) {
        Product[] result = new Product[0];
        for (Product products : repository.findAll()) {
            if (matches(products, text)) {
                Product[] tmp = new Product[result.length + 1];
                System.arraycopy(result, 0, tmp, 0, result.length);
                tmp[tmp.length - 1] = products;
                result = tmp;
            }
        }
        return result;
    }

    public boolean matches(Product products, String search) {
        if (products instanceof Book) {
            Book book = (Book) products;
            if (book.getAuthor().contains(search)) {
                return true;
            }
            if (book.getName().contains(search)) {
                return true;
            }
            return false;
        }
        if (products instanceof Smartphone) {
            Smartphone smartphone = (Smartphone) products;
            if (smartphone.getManufacturer().contains(search)) {
                return true;
            }
            if (smartphone.getName().contains(search)) {
                return true;
            }
            return false;
        }
        return false;
    }
}




