MyClass = {}
function MyClass:main()
    local a = "The answer is "
    local b = 42
    local result = a .. b
    print(result)
end

local sample = MyClass
sample:main()
