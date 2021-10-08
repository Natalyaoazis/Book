package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;


class ProductManagerTest {
    ProductRepository repository = new ProductRepository();
    ProductManager manager = new ProductManager(repository);
    Product book1 = new Book(1, "book1", 500, "автор1");
    Product book2 = new Book(2, "book2", 600, "автор2");
    Product smartphone1 = new Smartphone(11, "iPhone", 25000, "Apple");
    Product smartphone2 = new Smartphone(22, "iPhone1", 50000, "Apple");

    @BeforeEach
    public void setUp() {
        manager.add(book1);
        manager.add(book2);
        manager.add(smartphone1);
        manager.add(smartphone2);
    }

    @Test
    public void shouldSearchByName() {
        Product[] expected = {book2};
        Product[] actual = manager.searchBy("book2");
        assertArrayEquals(expected, actual);
    }
    @Test
    public void shouldSearchByAuthor() {
        Product[] expected = {book1};
        Product[] actual = manager.searchBy("автор1");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByManufacture() {
        Product[] expected = {smartphone1, smartphone2};
        Product[] actual = manager.searchBy("Apple");
        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearchByNull() {
        Product[] expected = {};
        Product[] actual = manager.searchBy("null");
        assertArrayEquals(expected, actual);
    }

}