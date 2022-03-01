#include <iostream>
#include "Cube.h"

Cube::Cube(unsigned int permute_id,const  unsigned int faces[NUM_FACES]) : _permute_id(permute_id)
{
    for (unsigned int i = 0; i < NUM_FACES; i++)
    {
        _faces[i] = faces[i];
    }
};

void Cube::print_faces()
{
    std::cout << "[";
    for (unsigned int i = 0; i < NUM_FACES; i++)
    {
        if (i == NUM_FACES - 1)
        {
            std::cout << _faces[i];
        }
        else
        {
            std::cout << _faces[i] << ", ";
        }
    }
    std::cout << "]";
}

void Cube::permute()
{
    unsigned int last = _faces[NUM_FACES - 1];
    
    for (unsigned int i = 0; i < NUM_FACES - 1 ; i++)
    {
        _faces[NUM_FACES - 1 - i] = _faces[NUM_FACES - 2 - i];
    }
    _faces[0] = last;
    _permute_id = (_permute_id + 1) % NUM_FACES;
}

unsigned int Cube::get_permute_id()
{
    return _permute_id;
}

unsigned int Cube::get_face_value(unsigned int i)
{
    return _faces[i];
}
