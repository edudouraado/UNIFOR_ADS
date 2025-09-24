class MyClass:
    def __init__(self):
        pass

    def metodo_principal(self):
        a = 10
        b = [1, 2, 3]
        c = a + b[0] #11
        print(c)
        d = a + b
        print(d)

if __name__ == "__main__":
    minha_instancia = MyClass()
    minha_instancia.metodo_principal()