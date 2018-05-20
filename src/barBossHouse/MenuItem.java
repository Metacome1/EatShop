package barBossHouse;


public abstract class MenuItem implements java.lang.Comparable<MenuItem> {
    final static int DEFAULT_COST = 0;
    String name;
    int cost;
    String description;

    protected MenuItem(String dishName, String dishDescription) {
        this(dishName, dishDescription, DEFAULT_COST);
    }

    protected MenuItem(String dishName, String dishDescription, int dishCost) {
        if (dishCost < 0) throw new IllegalArgumentException("Вам не будут платить за еду");
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
    public int compareTo(MenuItem o) {
        if (o.cost > cost) return -1;
        if (o.cost < cost) return 1;
        return 0;
    }

    @Override
    public String toString() {
        return String.format("%1$s%2$s%3$s",
                (name != null && !name.isEmpty()) ? name : "",
                (cost != 0) ? ", " + cost + "р." : "",
                (!description.isEmpty()) ? description : "").trim();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (obj == null || obj.getClass() != this.getClass())
            return false;

        MenuItem menuItem = (MenuItem) obj;

        return (name.equals(menuItem.getName()) && cost == menuItem.getCost());
    }

    @Override
    public int hashCode() {
        return name.hashCode()
                ^ cost;
    }
}