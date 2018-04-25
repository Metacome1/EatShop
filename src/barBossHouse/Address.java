package barBossHouse;

final public class Address {
    final  private String cityName;
    final  private int zipCode;
    final  private String streetName;
    final  private int buildingNumber;
    final  private char buildingLetter;
    final private int apartamentNumber;
     public final static Address EMPTY_ADDRESS = new Address();

    public Address() {
        this.cityName = "";
        this.zipCode = -1;
        this.streetName = "";
        this.buildingNumber = -1;
        this.buildingLetter = ' ';
        this.apartamentNumber = -1;
    }

    public Address(String cityName, int zipCode, String streetName, int buildingNumber, char buildingLetter, int apartamentNumber) {
        this.cityName = cityName;
        this.zipCode = zipCode;
        this.streetName = streetName;
        this.buildingNumber = buildingNumber;
        this.buildingLetter = buildingLetter;
        this.apartamentNumber = apartamentNumber;
    }

    public Address(String streetName, int buildingNumber, char buildingLetter, int apartamentNumber) {
        this.streetName = streetName;
        this.buildingNumber = buildingNumber;
        this.buildingLetter = buildingLetter;
        this.apartamentNumber = apartamentNumber;
        this.cityName = "Самара";
        this.zipCode = -1;
    }

    public char getBuildingLetter() {
        return buildingLetter;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public int getApartamentNumber() {
        return apartamentNumber;
    }

    public String getCityName() {
        return cityName;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getStreetName() {
        return streetName;
    }
    @Override
    public String toString() {
    return getClass().getSimpleName() + ":" + (cityName.isEmpty()  ? "" :cityName + " ") + (zipCode == -1 ? "":zipCode + ",") + (streetName.isEmpty() ? "": streetName + " ") + (buildingNumber == -1 ? "" : buildingNumber) + (buildingLetter == ' ' ? "" : buildingLetter + "-") + (apartamentNumber == -1 ? "" : apartamentNumber);
}

@Override
    public boolean equals(Object obj){
    if (obj == this)
        return true;

    if (obj == null || obj.getClass() != this.getClass())
        return false;

    Address address = (Address) obj;

    return (cityName.equals(address.getCityName()) & zipCode == address.getZipCode() & streetName.equals(address.getStreetName()) & buildingLetter == address.getBuildingLetter() & buildingLetter == address.getBuildingLetter() & apartamentNumber == address.getApartamentNumber());
    }

    @Override
    public int hashCode(){
        return cityName.hashCode()
                ^ zipCode
                ^streetName.hashCode()
                ^buildingNumber
                ^buildingLetter
                ^apartamentNumber;
    }
}
