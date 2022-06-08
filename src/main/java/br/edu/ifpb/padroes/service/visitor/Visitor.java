package br.edu.ifpb.padroes.service.visitor;

import br.edu.ifpb.padroes.model.Book;
import br.edu.ifpb.padroes.model.Electronic;
import br.edu.ifpb.padroes.model.Product;

import java.math.BigDecimal;
import java.util.Map;

public interface Visitor {
    BigDecimal visit(Book book);
    BigDecimal visit(Electronic book);

    default BigDecimal getTotalDiscount(Map<Product, Integer> products) {
        return products.keySet().stream()
                .map(product -> product.accept(this).multiply(BigDecimal.valueOf(products.get(product))))
                .reduce(BigDecimal::add).orElse(BigDecimal.ZERO);
    }
}
