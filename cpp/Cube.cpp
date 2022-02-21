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
    std::cout << "(";
    for (unsigned int i = 0; i < NUM_FACES; i++)
    {
        if (i == NUM_FACES - 1)
        {
            std::cout << _faces[i];
        }
        else
        {
            std::cout << _faces[i] << ",";
        }
    }
    std::cout << ")";
}

void Cube::permute()
{
    unsigned int temp[NUM_FACES];
    for (unsigned int i = 0; i < NUM_FACES; i++)
    {
        temp[i] = _faces[i];
    }
    for (unsigned int i = 1; i < NUM_FACES; i++)
    {
        _faces[i] = temp[i - 1];
    }
    _faces[0] = temp[NUM_FACES - 1];
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
