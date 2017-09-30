import itertools

possible = []
for perm in itertools.permutations(range(6)):
    flag = True
    for i in range(6):
        if perm[i] is i or perm[perm[i]] is not i:
            flag = False
            break

    if flag : possible.append(perm)


T = int(raw_input())
for tc in range(T):
    N = int(raw_input())
    A = [i - 1 for  i in map(int , raw_input().split())]
    found = []
    for perm in possible:
        can = True
        for i in range(N - 1):
            can &= not (A[i] is A[i + 1] or A[i + 1] is perm[A[i]])

        if can:
            found = perm
            break

    if len(found) > 0:
        print ' '.join(map(str , [i + 1 for i in found]))
    else:
        print -1
    
