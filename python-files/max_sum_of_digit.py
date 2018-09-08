def sum_of_d(n):
    return sum(map(int , str(n)))

inp = raw_input()
l = len(inp)
greedy = int(str((int(inp[0]) - 1)) + '9' * (l - 1))

print greedy if sum_of_d(greedy) > sum_of_d(inp) else inp