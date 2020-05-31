package com.restkea.food.adapter.boot.spring.application;

import com.restkea.food.application.task.context.PlaceFoodOrderContext;
import com.restkea.food.application.task.context.ReceiveFoodPaymentContext;
import com.restkea.food.application.task.step.AssembleFoodOrder;
import com.restkea.food.application.task.step.DisableOverdueFoodCheckoutWatcher;
import com.restkea.food.application.task.step.DisableOverdueFoodCheckoutWatcherCondition;
import com.restkea.food.application.task.step.EnableOverdueFoodCheckoutWatcher;
import com.restkea.food.application.task.step.EnableOverdueFoodCheckoutWatcherCondition;
import com.restkea.food.application.task.step.PersistFoodOrder;
import com.restkea.food.application.task.step.PersistFoodPayment;
import com.restkea.food.application.task.step.ReceiveFoodPayment;
import com.restkea.food.application.task.step.SnapshotFoodPrice;
import com.restkea.food.domain.order.FoodOrder;
import com.restkea.food.domain.order.FoodOrderFactory;
import com.restkea.food.domain.order.FoodOrderRepository;
import com.restkea.food.domain.payment.FoodPayment;
import com.restkea.food.domain.payment.FoodPaymentRepository;
import com.restkea.food.domain.pricing.FoodPriceCalculator;
import org.thebund1st.tfb.task.step.GherkinTask;
import org.thebund1st.tfb.task.step.ThenChain;
import org.thebund1st.tfb.task.step.ThenConditional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thebund1st.wusong.application.task.step.when.ReceivePayment;

import static org.thebund1st.tfb.task.step.GivenDummy.dummyGiven;
import static java.util.Arrays.asList;

@Configuration
public class ApplicationConfiguration {

    @Autowired
    private FoodOrderFactory foodOrderFactory;

    @Autowired
    private FoodOrderRepository foodOrderRepository;

    @Autowired
    private FoodPaymentRepository foodPaymentRepository;

    @Autowired
    private FoodPriceCalculator foodPriceCalculator;

    @Bean
    public GherkinTask<PlaceFoodOrderContext, FoodOrder> placeFoodOrderTask() {
        GherkinTask<PlaceFoodOrderContext, FoodOrder> bean = new GherkinTask<>();
        bean.setGiven(snapshotFoodPrice());
        bean.setWhen(assembleFoodOrder());
        bean.setThen(new ThenChain<>(asList(optionallyEnableOverdueFoodCheckoutWatcher(), persistFoodOrder())));
        return bean;
    }

    @Bean
    public SnapshotFoodPrice snapshotFoodPrice() {
        return new SnapshotFoodPrice(foodPriceCalculator);
    }

    @Bean
    public AssembleFoodOrder assembleFoodOrder() {
        return new AssembleFoodOrder(foodOrderFactory);
    }

    @Bean
    public PersistFoodOrder persistFoodOrder() {
        return new PersistFoodOrder(foodOrderRepository);
    }

    @Bean
    public ThenConditional<PlaceFoodOrderContext, FoodOrder> optionallyEnableOverdueFoodCheckoutWatcher() {
        return new ThenConditional<>(enableOverdueFoodCheckoutWatcherCondition(), enableOverdueFoodCheckoutWatcher());
    }

    @Bean
    public EnableOverdueFoodCheckoutWatcher enableOverdueFoodCheckoutWatcher() {
        return new EnableOverdueFoodCheckoutWatcher();
    }

    @Bean
    public EnableOverdueFoodCheckoutWatcherCondition enableOverdueFoodCheckoutWatcherCondition() {
        return new EnableOverdueFoodCheckoutWatcherCondition();
    }

    @Bean
    public GherkinTask<ReceiveFoodPaymentContext, FoodPayment> receiveFoodPaymentTask() {
        GherkinTask<ReceiveFoodPaymentContext, FoodPayment> bean = new GherkinTask<>();
        bean.setGiven(dummyGiven());
        bean.setWhen(receiveFoodPayment());
        bean.setThen(new ThenChain<>(asList(optionallyCloseOverdueFoodCheckoutWatcher(), persistFoodPayment())));
        return bean;
    }

    @Bean
    public ReceivePayment receiveFoodPayment() {
        return new ReceiveFoodPayment(foodOrderRepository);
    }

    @Bean
    public PersistFoodPayment persistFoodPayment() {
        return new PersistFoodPayment(foodPaymentRepository);
    }

    @Bean
    public ThenConditional<ReceiveFoodPaymentContext, FoodPayment> optionallyCloseOverdueFoodCheckoutWatcher() {
        return new ThenConditional<>(closeOverdueFoodCheckoutWatcherCondition(), closeOverdueCheckoutWatcher());
    }

    @Bean
    public DisableOverdueFoodCheckoutWatcherCondition closeOverdueFoodCheckoutWatcherCondition() {
        return new DisableOverdueFoodCheckoutWatcherCondition();
    }

    @Bean
    public DisableOverdueFoodCheckoutWatcher closeOverdueCheckoutWatcher() {
        return new DisableOverdueFoodCheckoutWatcher();
    }
}
