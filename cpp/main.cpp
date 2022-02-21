#include <iostream>
#include "Cube.h"
#include "utils.h"

int main()
{

	Cube *cubes_array = new Cube[NUM_CUBES];
	for (int i = 0; i < NUM_CUBES; i++)
	{
		cubes_array[i] = Cube(0, CUBE_ARRAY[i]);
	}
	const unsigned int number_iteration = powi(NUM_FACES, NUM_CUBES - 1);
	for (int i = 0; i < number_iteration; i++)
	{
		unsigned int sums[NUM_FACES] = {0};
		for (int j = 0; j < NUM_CUBES; j++)
		{
			if (get_digit_base(i, NUM_FACES, NUM_CUBES - j - 1) != cubes_array[j].get_permute_id())
			{
				cubes_array[j].permute();
			}
			for (int k = 0; k < NUM_FACES; k++)
			{
				sums[k] += cubes_array[j].get_face_value(k);
			}
		}
		
		if (all_even(sums, NUM_FACES))
		{
			std::cout << "[";
			for (int j = 0; j < NUM_CUBES; j++)
			{
				cubes_array[j].print_faces();
				if (j != NUM_CUBES - 1)
				{
					std::cout << ", ";
				}
			}
			std::cout << "]\n";
		}
	}

	return 0;
}
