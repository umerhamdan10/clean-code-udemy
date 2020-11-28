package com.b.simple.design.business.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.b.simple.design.business.exception.DifferentCurrenciesException;
import com.b.simple.design.model.customer.Amount;
import com.b.simple.design.model.customer.AmountImpl;
import com.b.simple.design.model.customer.Currency;
import com.b.simple.design.model.customer.Product;
import com.b.simple.design.model.customer.ProductImpl;
import com.b.simple.design.model.customer.ProductType;

public class CustomerBOTest {

	private CustomerBO customerBO = new CustomerBOImpl();

	@Test
	public void testCustomerProductSum_TwoProductsSameCurrencies() throws DifferentCurrenciesException {

		AmountImpl amount1 = new AmountImpl(new BigDecimal("5.0"), Currency.EURO);
		AmountImpl amount2 = new AmountImpl(new BigDecimal("6.0"), Currency.EURO);
		Amount[] amounts = {amount1, amount2};
		Amount expected = new AmountImpl(new BigDecimal("11.0"), Currency.EURO);


		List<Product> products = createProductsWithAmounts(amounts);
		Amount actual = customerBO.getCustomerProductsSum(products);

		assertProduct(expected, actual);
	}

	@Test
	public void testCustomerProductSum_TwoProductsDifferentCurrencies() {

		AmountImpl amount1 = new AmountImpl(new BigDecimal("5.0"), Currency.EURO);
		AmountImpl amount2 = new AmountImpl(new BigDecimal("6.0"), Currency.UNITED_STATES_DOLLAR);

		Amount[] amounts = {amount1, amount2};
		List<Product> products = createProductsWithAmounts(amounts);

		Assertions.assertThrows(DifferentCurrenciesException.class, ()->{
			customerBO.getCustomerProductsSum(products);
		});
	}


	@Test
	public void testCustomerProductSum_EmptyProducts() throws DifferentCurrenciesException {

		Amount actual = customerBO.getCustomerProductsSum( new ArrayList<Product>());
		Amount expected = new AmountImpl(BigDecimal.ZERO, Currency.EURO);

		assertProduct(expected, actual);

	}


	private void assertProduct(Amount expected, Amount actual) {
		assertEquals(expected.getCurrency(), actual.getCurrency());
		assertEquals(expected.getValue(), actual.getValue());
	}

	private List<Product>  createProductsWithAmounts(Amount[] amounts) {
		return Arrays.stream(amounts).map( amount -> new ProductImpl(100, "Product 15", ProductType.BANK_GUARANTEE, amount)).collect(Collectors.toList());
//		List<Product> products = new ArrayList<Product>();//
//		for(Amount amount: amounts) {//
//			products.add(new ProductImpl(100, "Product 15", ProductType.BANK_GUARANTEE, amount));//
//		}
//		return products;
	}
}