package com.restkea.food.adapter.boot.spring.application;

import com.restkea.food.application.checkout.CheckoutFoodBusinessIdentityConverter;
import com.restkea.food.application.checkout.CheckoutFoodCommandHandler;
import com.restkea.food.application.checkout.overdue.CloseOverdueFoodCheckoutWatcher;
import com.restkea.food.application.checkout.overdue.EnableOverdueFoodCheckoutWatcher;
import com.restkea.food.application.command.CheckoutFoodCommand;
import com.restkea.food.application.command.MakeFoodPaymentCommand;
import com.restkea.food.application.payment.MakeFoodPaymentCommandHandler;
import com.restkea.food.domain.order.CheckoutFoodBusinessIdentity;
import com.restkea.food.domain.order.FoodOrder;
import com.restkea.food.domain.order.FoodOrderFactory;
import com.restkea.food.domain.payment.FoodPayment;
import com.thebund1st.wusong.application.checkout.CheckoutBusinessIdentityConverter;
import com.thebund1st.wusong.application.checkout.CheckoutGivenChain;
import com.thebund1st.wusong.application.checkout.CheckoutThenChain;
import com.thebund1st.wusong.application.payment.MakePaymentCommandHandlerAspect;
import com.thebund1st.wusong.application.payment.MakePaymentThenChain;
import com.thebund1st.wusong.domain.order.OrderRepository;
import com.thebund1st.wusong.domain.payment.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Autowired
    private FoodOrderFactory foodOrderFactory;

    @Autowired
    private OrderRepository<String, FoodOrder> foodOrderRepository;

    @Autowired
    private PaymentRepository<String, FoodPayment> foodPaymentRepository;

    @Bean
    public CheckoutFoodCommandHandler checkoutFoodCommandHandler() {
        CheckoutFoodCommandHandler bean = new CheckoutFoodCommandHandler();
        bean.setCheckoutBusinessIdentityConverter(checkoutFoodBusinessIdentityConverter());
        bean.setOrderFactory(foodOrderFactory);
        bean.setOrderRepository(foodOrderRepository);
        bean.setCheckoutGiven(checkoutFoodGivenChain());
        bean.setCheckoutThen(checkoutFoodThenChain());
        return bean;
    }

    @Bean
    public CheckoutGivenChain<CheckoutFoodCommand, FoodOrder> checkoutFoodGivenChain() {
        return new CheckoutGivenChain<>();
    }

    @Bean
    public CheckoutThenChain<CheckoutFoodCommand, FoodOrder> checkoutFoodThenChain() {
        CheckoutThenChain<CheckoutFoodCommand, FoodOrder> bean = new CheckoutThenChain<>();
        bean.add(enableOverdueFoodCheckoutWatcher());
        return bean;
    }

    @Bean
    public CheckoutBusinessIdentityConverter<CheckoutFoodCommand, CheckoutFoodBusinessIdentity>
    checkoutFoodBusinessIdentityConverter() {
        return new CheckoutFoodBusinessIdentityConverter();
    }

    @Bean
    public EnableOverdueFoodCheckoutWatcher enableOverdueFoodCheckoutWatcher() {
        return new EnableOverdueFoodCheckoutWatcher(foodOrderRepository);
    }

    @Bean
    public MakeFoodPaymentCommandHandler makeFoodPaymentCommandHandler() {
        MakeFoodPaymentCommandHandler bean = new MakeFoodPaymentCommandHandler();
        bean.setPaymentRepository(foodPaymentRepository);
        bean.setOrderRepository(foodOrderRepository);
        return bean;
    }

    @Bean
    public MakePaymentThenChain<MakeFoodPaymentCommand, FoodPayment> makeFoodPaymentThenChain() {
        MakePaymentThenChain<MakeFoodPaymentCommand, FoodPayment> bean = new MakePaymentThenChain<>();
        bean.add(closeOverdueFoodCheckoutWatcher());
        return bean;
    }

    @Bean
    public CloseOverdueFoodCheckoutWatcher closeOverdueFoodCheckoutWatcher() {
        CloseOverdueFoodCheckoutWatcher bean = new CloseOverdueFoodCheckoutWatcher();
        bean.setOrderRepository(foodOrderRepository);
        return bean;
    }

    @Bean
    public MakePaymentCommandHandlerAspect<MakeFoodPaymentCommand, FoodPayment> makePaymentCommandHandlerAspect() {
        MakePaymentCommandHandlerAspect<MakeFoodPaymentCommand, FoodPayment> bean =
                new MakePaymentCommandHandlerAspect<>();
        bean.setTarget(makeFoodPaymentCommandHandler());
        bean.setThen(makeFoodPaymentThenChain());
        return bean;
    }
}
