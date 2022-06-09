package br.edu.ifpb.padroes.visitor;

import br.edu.ifpb.padroes.model.Book;
import br.edu.ifpb.padroes.model.Electronic;
import br.edu.ifpb.padroes.model.Product;


import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;


public class DiscountVisitor implements Visitor{

    public BigDecimal discount;

    private static final BigDecimal BOOK_DISCOUNT = BigDecimal.valueOf(0.3); // 30 %
    private static final BigDecimal ELECTRONIC_DISCOUNT = BigDecimal.valueOf(0.05); // 5 %


    public Consumer<? super BigDecimal> getDiscount() {
        return (Consumer<? super BigDecimal>) discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal applyDiscount(Map<Product, Integer> disc){
        return disc.keySet()
                .stream()
                .map(product -> product.accept(this)
                        .multiply(BigDecimal.valueOf(disc.get(product))))
                .reduce(BigDecimal::add)
                .orElse(BigDecimal.ZERO);
    }

    @Override
    public BigDecimal visitBook(Book book) {
        return book.getPrice().multiply(BOOK_DISCOUNT);

    }

    @Override
    public BigDecimal visitEletronic(Electronic electronic) {
        return electronic.getPrice().multiply(ELECTRONIC_DISCOUNT);
    }
}
