import java.util.Arrays;

public class Cube
{
    private int[] values;

    public Cube(int[] values)
    {
        this.values = Arrays.copyOf(values, values.length);
    }

    public Cube(Cube c)
    {
        this(c.getValues());
    }

    public int[] getValues()
    {
        return this.values;
    }

    public int getValue(int index)
    {
        assert index >= 0;
        return this.values[index];
    }

    public int getNumberOfValues()
    {
        return this.values.length;
    }

    public void turnClockwise()
    {
        int last = this.values[this.values.length - 1];

        for (int i = this.values.length - 2; i >= 0; i--) {
            this.values[i+1] = this.values[i];
        }

        this.values[0] = last;
    }

    @Override
    public String toString()
    {
        return Arrays.toString(this.values);
    }
}
