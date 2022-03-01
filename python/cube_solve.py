from Cube import Cube

CUBE_NUMBER = [[95, 30, 25, 60],
               [55, 25, 20, 70],
               [20, 50, 55, 10],
               [45, 55, 65, 15],
               [90, 55, 90, 80],
               [60, 90, 85, 55],
               [45, 95, 15, 55],
               [75, 65, 55, 55],
               [55, 70, 35, 55],
               [55, 55, 60, 75]]
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


if __name__ == "__main__":
    cubes = [Cube(CUBE_NUMBER[i], num_of_faces) for i in range(num_of_cubes)]
    num_sol = 0
    for i in range(num_of_faces**(num_of_cubes - 1)):
        num = number_to_base(i, num_of_faces)
        num = [0]*(num_of_cubes - len(num)) + num
        sums = [0 for _ in range(num_of_faces)]
        for j in range(num_of_cubes):
            cubes[j].permute(num[j])
            for k in range(num_of_faces):
                sums[k] += cubes[j].face_list[k]
        if sums.count(sums[0]) == len(sums):
            print(''.join([str(cube_i) for cube_i in cubes]) + '\n')
            num_sol += 1
    print("Found {} solutions".format(num_sol))
