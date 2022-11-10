from sympy import *
x = symbols('x')
a = 1*x**2+5*x+6
d = a.coeff(x,2)
b = a.coeff(x,1)
c = a.coeff(x,0)
r1 = (-b+((b**2)-(4*d*c))**0.5)/2*d
print(round(r1,2))
r2 = (-b-((b**2)-(4*d*c))**0.5)/2*d
print(round(r2,2))
