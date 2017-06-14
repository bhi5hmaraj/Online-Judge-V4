num = map(int , raw_input().split())
(n , t , k , d) = num
opt1 = ((n / k) + (1 if n % k is not 0 else 0)) * t
time_to_build = (d / t) + (1 if d % t is not 0 else 0)
cakes_in_this_time = time_to_build * k
opt2 = 0
if(cakes_in_this_time >= n):
	opt2 = time_to_build
else:
	remain_cakes = n - cakes_in_this_time
	opt2 = t * ((remain_cakes / (2*k)) + (1 if remain_cakes % (2*k) is not 0 else 0))

print opt1 , opt2

print "YES" if opt2 < opt1 else "NO"