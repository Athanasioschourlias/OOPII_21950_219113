package org.hua.dit.oopii_21950_219113.entitys;

public class Vectors
{
    public void printVector(int[] array)
    {
        System.out.print('[');
        for(int i=0 ; i < array.length;i++)
        {
            System.out.print(array[i]);
            if(i!= array.length-1)
                System.out.print(',');
            else
                continue;
        }
        System.out.print(']');
    }

    public void printVector(double[] array)
    {
        System.out.print('[');
        for(int i=0 ; i < array.length;i++)
        {
            System.out.print(array[i]);
            if(i!= array.length-1)
                System.out.print(',');
            else
                continue;
        }
        System.out.print(']');
    }

}
