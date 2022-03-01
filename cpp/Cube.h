
#ifndef CUBE_H_
#define CUBE_H_

#include "consts.h"

class Cube
{
private:
    unsigned int _permute_id;
    unsigned int _faces[NUM_FACES];

public:
    Cube() = default;
    Cube(unsigned int permute_id, const unsigned int faces[NUM_FACES]);
    unsigned int get_permute_id();
    unsigned int get_face_value(unsigned int i);
    void print_faces();
    void permute();
};

#endif /* CUBE_H_ */