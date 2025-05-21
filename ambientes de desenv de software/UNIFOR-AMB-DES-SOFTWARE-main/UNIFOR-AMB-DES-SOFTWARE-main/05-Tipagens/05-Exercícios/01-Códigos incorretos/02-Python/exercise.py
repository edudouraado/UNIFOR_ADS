class Person:
    def __init__(self, name, age):
        self.name = name
        self.age = age

def display_info(person):
    if not isinstance(person, Person):
        raise TypeError("Expected a Person instance")
    print(f"Name: {person.name}, Age: {person.age}")

person1 = Person("Alice", "30")
display_info(person1)

result = "The age is: " + person1.age
print(result)
