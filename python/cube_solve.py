CUBE_NUMBER = [[55, 65, 15, 45], [60, 95, 30, 25], [75, 65, 55, 55], [60, 75, 55, 55], [55, 10, 20, 50],
               [70, 55, 25, 20], [55, 70, 35, 55], [55, 45, 95, 15], [80, 90, 55, 90], [85, 55, 60, 90]]
num_of_cubes = len(CUBE_NUMBER)
num_of_faces = len(CUBE_NUMBER[0])


def number_to_base(n, b):
    if n == 0:
        return [0]
    digits = []
    while n:
        digits.append(int(n % b))
        n //= b
    return digits[::-1]


class Cube:
    def __init__(self, face_list, num_of_faces):
        self.face_list = face_list
        self._permute = 0
        self._num_of_faces = num_of_faces

    def permute(self, permute_id):
        if permute_id != self._permute:
            self.face_list = [self.face_list[-1]] + self.face_list[:-1]
            self._permute = (self._permute + 1) % self._num_of_faces
    
    def __repr__(self):
        return "({0},{1},{2},{3})".format(self.face_list[0], self.face_list[1], self.face_list[2], self.face_list[3])


if __name__ == "__main__":
    cubes = [Cube(CUBE_NUMBER[i], num_of_faces) for i in range(num_of_cubes)]
    for i in range(num_of_faces**(num_of_cubes - 1)):
        num = number_to_base(i, num_of_faces)
        num = [0]*(num_of_cubes - len(num)) + num
        sums = [0 for _ in range(num_of_faces)]
        for j in range(num_of_cubes):
            cubes[j].permute(num[j])
            for k in range(num_of_faces):
                sums[k] += cubes[j].face_list[k]
        if sums.count(sums[0]) == len(sums):
            print(cubes)

