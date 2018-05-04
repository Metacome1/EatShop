package barBossHouse;

final public class Drink extends MenuItem implements Alcoholable {
     private double alcoholVol;
     private DrinkTypeEnum type;

    public Drink(String dishName, String dishDescription) {
        super(dishName, dishDescription);
    }

    public Drink(String dishName, String dishDescription, int dishCost) {
        super(dishName, dishDescription, dishCost);
    }

    public Drink(String dishName, DrinkTypeEnum type){
        super(dishName, "", 0);
        this.type = type;
    }

    public Drink(String dishName, String dishDescription, int dishCost, DrinkTypeEnum type) {
        super(dishName, dishDescription, dishCost);
        this.type = type;
        this.alcoholVol = 0;
    }

    @Override
    public double getAlcoholVol() {
        return alcoholVol;
    }

    @Override
    public boolean isAlcoholicDrink() {
        if (alcoholVol> 0) return true;
        else return false;
    }

    public Drink(String dishName, String dishDescription, int dishCost, DrinkTypeEnum type, double alcoholVol) {
        super(dishName, dishDescription, dishCost);
        this.type = type;
        this.alcoholVol = alcoholVol;
    }

    public DrinkTypeEnum getType(){
        return type;
    }
    @Override
    public String toString() {
        return
                super.toString()
                + " "
                + (type)
                + (alcoholVol == 0 ? (" Alcohol: "  + alcoholVol + "%.") : (""))
                + (getDescription().isEmpty() ? "" : getDescription());
    }

    @Override
    public boolean equals(Object obj){
        //todo сделай так же как в Dish
        if (obj == this)
            return true;

        if (obj == null || obj.getClass() != this.getClass())
            return false;

        Drink drink = (Drink) obj;

        return (type.equals(drink.getType()) & super.equals(drink) & alcoholVol == drink.getAlcoholVol());
    }

    @Override
    public int hashCode(){
        return  super.hashCode()
                ^getType().hashCode()
                ^Double.valueOf(getAlcoholVol()).hashCode();
    }



}
