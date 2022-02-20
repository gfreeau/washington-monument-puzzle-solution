#include <iostream>
const unsigned int NUM_FACES = 4;
const unsigned int NUM_CUBES = 10;
unsigned int CUBE_ARRAY [NUM_CUBES][NUM_FACES] =  {
	55, 65, 15, 45, 
	60, 95, 30, 25, 
	75, 65, 55, 55, 
	60, 75, 55, 55, 
	55, 10, 20, 50, 
	70, 55, 25, 20, 
	55, 70, 35, 55, 
	55, 45, 95, 15, 
	80, 90, 55, 90, 
    85, 55, 60, 90
};
	 
class Cube {
  private:
	unsigned int _permute_id;
	unsigned int _faces[NUM_FACES];
  public:
    Cube() = default;
	Cube (unsigned int permute_id, unsigned int  faces[NUM_FACES]) : _permute_id(permute_id) {
		for(int i=0; i<NUM_FACES; i++) {_faces[i] = faces[i];}
	};
    unsigned int get_permute_id();
    unsigned int get_face_value(unsigned int i);
	void print_faces();
	void permute();
} ;

unsigned int Cube::get_permute_id() {
    return _permute_id;
}

unsigned int Cube::get_face_value(unsigned int i) {
    return _faces[i];
}

void Cube::print_faces(){
	std::cout << "(" ;
    for(int i=0; i<NUM_FACES; i++) {
		if (i == NUM_FACES -1 ) {
			std::cout << _faces[i];	
		} else {
		std::cout << _faces[i] << ", ";
		}
	}
    std::cout << ")" ;      
}

void Cube::permute() {
	unsigned int temp[NUM_FACES];
	for(int i=0; i<NUM_FACES; i++) {temp[i] = _faces[i];}
	for(int i=1; i<NUM_FACES; i++) {_faces[i] = temp[i-1];}
	_faces[0] = temp[NUM_FACES-1];
	_permute_id =(_permute_id+1)%NUM_FACES;	
}

unsigned int get_digit_base(unsigned int n,unsigned int b,unsigned int digit) {
	if (n == 0){
		return 0;
    }
    for (int i=0; i < digit; i++)
        n /= b;
		if (n == 0) {
			return 0;
		}
    return (n % b);
}

unsigned  int powi (unsigned int base, unsigned int exp)
{
    unsigned int res = 1;
    while (exp) {
        if (exp & 1)
            res *= base;
        exp >>= 1;
        base *= base;
    }
    return res;
}

int main() {
   
	Cube *cubes_array = new Cube[NUM_CUBES];
    for (int i=0; i<NUM_CUBES; i++) {
		cubes_array[i] = Cube(0, CUBE_ARRAY[i]);
	}
	const unsigned int number_iteration = powi(NUM_FACES, NUM_CUBES - 1); 
	for (int i=0; i<number_iteration; i++) {
		unsigned int sums[NUM_FACES] = {0};	
		for (int j=0; j<NUM_CUBES; j++){
			if (get_digit_base(i, NUM_FACES, NUM_CUBES - j - 1) != cubes_array[j].get_permute_id()) {
				cubes_array[j].permute();
			}
			for (int k=0; k<NUM_FACES; k++) {
				sums[k] += cubes_array[j].get_face_value(k);				
			}
		}
		bool flag_print = true;
		for (int k=1; k<NUM_FACES; k++) {
			flag_print = (flag_print && (sums[0]== sums[k]));
		}
		if (flag_print) {
			std::cout << "[";
			for (int j=0; j<NUM_CUBES; j++) {
				cubes_array[j].print_faces();
				if (j != NUM_CUBES-1) {
					std::cout << ", ";
				}
			}
			std::cout << "]\n";
		}
		
	}
  
    return 0;
}
