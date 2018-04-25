package barBossHouse;

final public class Dish extends MenuItem {

    public Dish(String dishName, String dishDescription) {
        super(dishName, dishDescription);
    }

    public Dish(String dishName, String dishDescription, int dishCost) {
        super(dishName, dishDescription, dishCost);
    }
    //todo вызывай версию суперкласса COMPLITED
    @Override
    public String toString() {
        return ( super.toString() + (getDescription().isEmpty() ? "": getDescription()));
    }

    //todo вызывай версию суперкласса COMPLITED
    public boolean equals(Object obj){
        if (obj == this)
            return true;

        if (obj == null || obj.getClass() != this.getClass())
            return false;

        Dish dish = (Dish) obj;

        return (super.equals(dish) & getDescription().equals(dish.getDescription()));
    }

    //todo вызывай версию суперкласса COMPLITED
    public int hashCode(){
        return super.hashCode()
                ^getDescription().hashCode();
    }
}
