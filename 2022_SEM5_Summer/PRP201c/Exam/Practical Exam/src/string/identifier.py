st = 'Python'
print(st.isidentifier())

st = 'Py thon'
print(st.isidentifier())

st = '22Python'
print(st.isidentifier())

st = ''
print(st.isidentifier())

st = 'root33'
if st.isidentifier() == True:
    print(st, 'is a valid identifier.')
else:
    print(st, 'is not a valid identifier.')
  
st = '33root'
if st.isidentifier() == True:
    print(st, 'is a valid identifier.')
else:
    print(st, 'is not a valid identifier.')
  
st = 'root 33'
if st.isidentifier() == True:
    print(st, 'is a valid identifier.')
else:
    print(st, 'is not a valid identifier.')
