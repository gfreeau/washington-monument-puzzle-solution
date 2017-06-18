import java.util.ArrayList;

public class CubePermutation
{
    ArrayList<Integer> values = new ArrayList<>();

    public CubePermutation() {

    }

    public CubePermutation(CubePermutation c)
    {
        this.values = new ArrayList<>(c.getValues());
    }

    public boolean add(int value)
    {
        return this.values.add(value);
    }

    public ArrayList<Integer> getValues()
    {
        return this.values;
    }

    public int getValue(int index)
    {
        assert index >= 0;
        return this.values.get(index);
    }
}
