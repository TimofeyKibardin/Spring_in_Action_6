package com.sia.tacos.web;

import com.sia.tacos.TacoOrder;
import com.sia.tacos.User;
import com.sia.tacos.data.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.validation.Valid;
import java.security.Principal;

@Slf4j
@Controller
@RequestMapping("/orders") //Будет обрабатывать реквесты от тех, чей путь начинается с /orders
@SessionAttributes("tacoOrder")
public class OrderController {

    //Подключаем интерфейс
    private OrderRepository orderRepo;

    public OrderController(OrderRepository orderRepo) {
        this.orderRepo = orderRepo;
    }

    //Будет обработан ГЕТ-запрос из /orders/current
    @GetMapping("/current")
    public String orderFrom(@AuthenticationPrincipal User user,
                            @ModelAttribute TacoOrder order) {

        if (order.getDeliveryName() == null) {
            order.setDeliveryName(user.getFullname());
        }
        if (order.getDeliveryStreet() == null) {
            order.setDeliveryStreet(user.getStreet());
        }
        if (order.getDeliveryCity() == null) {
            order.setDeliveryCity(user.getCity());
        }
        if (order.getDeliveryState() == null) {
            order.setDeliveryState(user.getState());
        }
        if (order.getDeliveryZip() == null) {
            order.setDeliveryZip(user.getZip());
        }

        return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors, SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user) {

        if (errors.hasErrors()) {
            return "orderForm";
        }

        order.setUser(user);;

        orderRepo.save(order);
        log.info("Order submitted: {}", order);
        sessionStatus.setComplete(); //Сеанс закончен и готов к новому заказу, когда пользователь снова создаст тако

        return "redirect: /";
    }
}
