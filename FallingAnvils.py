T = int(raw_input())

for _ in range(T):
    (a ,b) = map(int, raw_input().split())
    if b == 0:
        print 1
    elif a == 0:
        print 0.5
    else:
        total_area = 2 * a * b
        req_area = a * b 
        if a > 4 * b:
            req_area += 2 * b**2 + b * (a - 4 * b)
        else:
            req_area += a**2 / 8.0

        print float(req_area) / float(total_area)