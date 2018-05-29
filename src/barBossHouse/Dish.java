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
        //todo return String.format(А уже вызов super.toString() внутри делается)
        return super.toString() + String.format("%1$s",
                (getDescription() != null && !getDescription().isEmpty()) ? " " + getDescription() : "").trim();
    }


    public boolean equals(Object obj){
        return (super.equals(obj) && getDescription().equals(((MenuItem) obj).getDescription()));
    }

    public int hashCode(){
        return super.hashCode()
                ^getDescription().hashCode();
    }
}
