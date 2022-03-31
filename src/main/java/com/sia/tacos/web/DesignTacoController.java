package com.sia.tacos.web;

import com.sia.tacos.Ingredient;
import com.sia.tacos.Taco;
import com.sia.tacos.TacoOrder;
import com.sia.tacos.User;
import com.sia.tacos.data.IngredientRepository;
import com.sia.tacos.data.TacoRepository;
import com.sia.tacos.data.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j //Генерирует логгер в рантайме
@Controller //Идентифицирует класс как Контроллер, Спринг автоматически создаст контейнер для этого класса
@RequestMapping("/design") //Индицирует, по какому пути будут обрабатываться реквесты (только /design)
@SessionAttributes("tacoOrder") //Объект класса TacoOrder должен поддерживаться в сессии
public class DesignTacoController {

    private final IngredientRepository ingredientRepo;
    private TacoRepository tacoRepo;
    private UserRepository userRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo) {
        this.ingredientRepo = ingredientRepo;
    }

    public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository tacoRepo, UserRepository userRepo) {
        this.ingredientRepo = ingredientRepo;
        this.tacoRepo = tacoRepo;
        this.userRepo = userRepo;
    }

    //При обращении к /design, реквест будет направлен на этот метод
    @GetMapping
    public String showDesignForm() {
        return "design";
    }

    @PostMapping
    public String processTaco(@Valid Taco taco, Errors errors, @ModelAttribute TacoOrder tacoOrder) {

        log.info("   ---Saving taco");

        //@Valid говорит спрингу валидировать полученный объект Taco
        if (errors.hasErrors()) {
            return "design";
        }

        Taco saved = tacoRepo.save(taco);
        tacoOrder.addTaco(saved);

        return "redirect:/orders/current";
    }

    //Будет задействован вместе с handle-методом showDesignForm()
    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(i -> ingredients.add(i));

        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
    }

    //Заказ, возвращает данные о заказе
    //Будет задействован вместе с handle-методом showDesignForm()
    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order() {
        return new TacoOrder();
    }

    //Будет задействован вместе с handle-методом showDesignForm()
    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @ModelAttribute
    public User user(Principal principal) {
        String username = principal.getName();
        User user = userRepo.findByUsername(username);
        return user;
    }

    //Метод, который возвращает лист ингредиентов нужного типа
    private Iterable<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }






















}
