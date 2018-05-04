package barBossHouse;

final public class Dish extends MenuItem {

    public Dish(String dishName, String dishDescription) {
        super(dishName, dishDescription);
    }

    public Dish(String dishName, String dishDescription, int dishCost) {
        super(dishName, dishDescription, dishCost);
    }
    @Override
    public String toString() {
        return ( super.toString() + (getDescription().isEmpty() ? "": getDescription()));
    }

    public boolean equals(Object obj){
            return (super.equals(obj) && getDescription().equals(((MenuItem) obj).getDescription()));
    }

    public int hashCode(){
        return super.hashCode()
                ^getDescription().hashCode();
    }
}
