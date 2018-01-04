n = int(raw_input())
prob = map(float, raw_input().split())
memo = {}


def rec(idx, f1, f2):
    global n, prob
    cache = (idx, f1, f2)
    if(idx >= n):
        return f1
    if cache in memo:
        return memo[cache]

    ret = max(rec(idx + 1, f1, f2),
              rec(idx + 1, f1 * (1 - prob[idx]) + f2 * prob[idx], f2 * (1 - prob[idx])))

    memo[cache] = ret
    return ret


print rec(0, 0, 1)
