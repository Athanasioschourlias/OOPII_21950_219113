package org.hua.dit.oopii_21950_219113.entitys;

import java.util.*;
import java.util.stream.Collectors;

public class CollaborativeFiltering
{
    public City bestCity(Traveller candidateTraveller , ArrayList<Traveller> collectionTravellers)
    {
        int[] candidateTravellerCriteria=candidateTraveller.getTermVector();
        Optional<RecommendedCity> recommendedCity=
                collectionTravellers.stream().map(i-> new RecommendedCity(i.getVisit(),innerDot(i.getTermVector(),candidateTravellerCriteria))).max(Comparator.comparingInt(RecommendedCity::getRank));

        return recommendedCity.get().getCity();
    }

    private static int innerDot(int[] currentTraveller, int[] candidateTraveller)
    {
        int sum=0;
        for (int i=0; i<currentTraveller.length;i++)
            sum+=currentTraveller[i]*candidateTraveller[i];
        return sum;
    }

}
