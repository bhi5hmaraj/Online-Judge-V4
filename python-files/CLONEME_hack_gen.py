N = 10**5
Q = 10**5
hack = range(1 , N - 3 + 1)
hack += [N]*3
hack[1] = hack[2] = 1
print "1"
print N , Q
print ' '.join(map(str , hack))
for _ in range(Q):
	print 1 , N - 3 , 2 , N - 2