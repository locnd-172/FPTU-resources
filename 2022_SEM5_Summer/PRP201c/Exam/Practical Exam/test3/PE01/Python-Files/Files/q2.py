class Student:
    name = ""
    age = 0
    score = 0.0
    
    def __init__(self, name_, age_, score_):
        self.name = name_
        self.age = age_
        self.score = score_
        
    def get_name(self):
        return self.name
 
student_list = list()   
    
def sort_student_list(student_list):
    n = len(student_list)
    for i in range(n):
        for j in range(n - 1):
            if student_list[j].name > student_list[j + 1].name:
                student_list[j], student_list[j + 1] = student_list[j + 1], student_list[j]
            
def main():
    
    n = int(input("Number of students: "))
    
    for i in range(n):
        name, age, score = map(str, input().strip().split(" "))
        student = Student(name, age, score)
        student_list.append(student)
        
    print("Student list before sorting: ")
    for student in student_list:
        print(student.name, student.age, student.score)
        
    sort_student_list(student_list)
    print("Student list after sorting: ")
    for student in student_list:
        print(student.name, student.age, student.score)

main()

# nam 18 9.8
# ha 19 8.6
# quang 18 9.2
# duc 19 8.4
# bao 18 7.9
# anh 19 8.0
