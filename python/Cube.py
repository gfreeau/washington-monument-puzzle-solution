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
        return "[{0},{1},{2},{3}]\n".format(self.face_list[0], self.face_list[1], self.face_list[2], self.face_list[3])