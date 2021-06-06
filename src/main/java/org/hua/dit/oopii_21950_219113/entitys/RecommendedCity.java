package org.hua.dit.oopii_21950_219113.entitys;

public class RecommendedCity
{
    private City City;
    private int rank;
    public RecommendedCity(City city, int rank) {
        super();
        City = city;
        this.rank = rank;
    }
    public City getCity() {
        return City;
    }
    public void setCity(City city) {
        City = city;
    }
    public int getRank() {
        return rank;
    }
    public void setRank(int rank) {
        this.rank = rank;
    }

}
