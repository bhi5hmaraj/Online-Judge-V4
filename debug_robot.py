from itertools import *
n , m = map(int, raw_input().split())
grid = [raw_input() for i in range(n)]
move = map(int , raw_input().split())

dx = [-1 , 0 , 1 , 0]
dy = [0 , 1 , 0 , -1]

start = []
end = []

for i in range(n):
    s_pos = grid[i].find('S')
    e_pos = grid[i].find('E')
    if s_pos >= 0:
        start = [i , s_pos]
    if e_pos >= 0:
        end = [i , e_pos]

cnt = 0

for perm in permutations(range(4)):
    curr = list(start)
    reached = False
    for mv in move:
        curr[0] += dx[perm[mv]]
        curr[1] += dy[perm[mv]]
        if curr == end:
            reached = True
            break
        elif curr[0] < 0 or curr[0] >= n or curr[1] < 0 or curr[1] >= m:
            break

    cnt += 1 if reached else 0


print cnt

