package barBossHouse;


public abstract class MenuItem {
    private static final int DEFAULT_COST = 0;
    private String name;
    private int cost;
    private String description;

    protected MenuItem(String dishName, String dishDescription)
    {
        this(dishName, dishDescription, DEFAULT_COST);
    }

    protected MenuItem(String dishName, String dishDescription, int dishCost)
    {
        name = dishName;
        description = dishDescription;
        cost = dishCost;
    }


    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }


    @Override
    public String toString() {
        return ((name.isEmpty() ? "" : name + ",") + (cost == 0 ? "" : cost + "р."));
    }

    @Override
    public boolean equals(Object obj){
        if (obj == this)
            return true;

        if (obj == null || obj.getClass() != this.getClass())
            return false;

        MenuItem menuItem = (MenuItem) obj;

        return (menuItem.hashCode() == (name.hashCode() ^ cost));
    }

    @Override
    public int hashCode(){
        return name.hashCode()
                ^cost;
    }
}