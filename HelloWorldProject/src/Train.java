public class Train {
    private String name, product, origin, destination;
    private int weight, miles;
    //constuctors
    public Train(String n, String p,String o, String d, int w, int m)
    {
        name = n;
        product = p;
        origin = o;
        destination = d;
        weight = w;
        miles = m;
    }
    //getters
    public String getName()
    {
        return name;
    }
    public String getProduct()
    {
        return product;
    }
    public String getOrigin()
    {
        return origin;
    }
    public String getDestination()
    {
        return destination;
    }
    public int getWeight()
    {
        return weight;
    }
    public int getMiles()
    {
        return miles;
    }
    //setters
    public void setMiles(int newMiles)
    {
        miles = newMiles;
    }
    public String toString()
    {
        return name + product + weight;
    }
}
