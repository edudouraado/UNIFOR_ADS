class Person
    attr_accessor :name, :age

    def initialize(name, age)
        @name = name
        @age = age
    end
end

def display_info(person)
    raise TypeError, "Expected Person instance" unless person.is_a?(Person)
    puts "Name: #{person.name}, Age: #{person.age}"
end

alice = Person.new("Alice", 30)
display_info(alice)

# Erro de tipo é uma pessoa
invalid_data = [1, 2, 3]
display_info(invalid_data)  # Erro: TypeError

# Acesso a métodos/propriedades inexistentes
result = "The number is: " + 10  # Erro: TypeError
puts result
