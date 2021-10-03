package ru.netology.manager;


import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

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
        if (products instanceof Book) { // если в параметре product лежит объект класса Book
            Book book = (Book) products; // положем его в переменную типа Book чтобы пользоваться методами класса Book
            if (book.getAuthor().contains(search)) { // проверим есть ли поисковое слово в данных об авторе
                return true;
            }
            if (book.getName().contains(search)) {
                return true;
            }
            return false;
        }
        if (products instanceof Smartphone) { // если в параметре product лежит объект класса Book
            Smartphone smartphone = (Smartphone) products; // положем его в переменную типа Book чтобы пользоваться методами класса Book
            if (smartphone.getManufacturer().contains(search)) { // проверим есть ли поисковое слово в данных об авторе
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




