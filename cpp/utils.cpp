#include "utils.h"

unsigned int get_digit_base(unsigned int n, unsigned int b, unsigned int digit)
{
	if (n == 0)
	{
		return 0;
	}
	for (int i = 0; i < digit; i++)
		n /= b;
	if (n == 0)
	{
		return 0;
	}
	return (n % b);
}

unsigned int powi(unsigned int base, unsigned int exp)
{
	unsigned int res = 1;
	while (exp)
	{
		if (exp & 1)
			res *= base;
		exp >>= 1;
		base *= base;
	}
	return res;
}

bool all_even(unsigned int in_array [], unsigned int size_array ) {
    bool flag = true;
    for (int k = 1; k < size_array; k++)
	{
	    flag = (flag && (in_array[0] == in_array[k]));
    }
    return flag;
}