package lab.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DishRepository {


    private List<Dish> dishes;

    public DishRepository() {
        this.dishes = new ArrayList<>();
    }

    public void addDish(Dish dish) {
        if (dish != null && !dishes.contains(dish)) {
            dishes.add(dish);
            System.out.println("Страву додано: " + dish);
        } else {
            System.out.println("Страва вже існує або є недійсною.");
        }
    }

    public void removeDishByName(String name) {
        dishes.removeIf(dish -> dish.getName().equalsIgnoreCase(name));
        System.out.println("Страву з назвою \"" + name + "\" видалено.");
    }

    public List<Dish> getAllDishes() {
        return new ArrayList<>(dishes);
    }

    public Optional<Dish> findDishByName(String name) {
        return dishes.stream()
                .filter(dish -> dish.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    public void updateDish(String name, Dish updatedDish) {
        Optional<Dish> existingDish = findDishByName(name);
        if (existingDish.isPresent()) {
            int index = dishes.indexOf(existingDish.get());
            dishes.set(index, updatedDish);
            System.out.println("Інформацію про страву оновлено: " + updatedDish);
        } else {
            System.out.println("Страву з назвою \"" + name + "\" не знайдено.");
        }
    }
}
