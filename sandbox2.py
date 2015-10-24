import sys
import os
import resource
from sandbox import Sandbox
import signal

def perform(cmd):
	os.system(cmd)

def time_exceeded(sig):
	print("TLE")

def main():
	filecpp=sys.argv[1]
	filetxt=sys.argv[2]
	filext=sys.argv[3]

	cmd="./bash2.sh"+" "+filecpp+" "+filetxt+" "+filext

	resource.setrlimit(resource.RLIMIT_CPU,(1,3))
	#The maximum amount of processor time (in seconds) that a process can use. 
	soft, hard = 10**10, 10**10
	resource.setrlimit(resource.RLIMIT_AS,(soft,hard))
	#The maximum area (in bytes) of address space which may be taken by the process.

	sandbox=Sandbox()
	sandbox.call(perform,cmd)
	signal.signal(signal.SIGXCPU,time_exceeded)
	soft, hard = resource.getrlimit(resource.RLIMIT_CPU)


main()
sys.exit(0)



# we can provide more restriction by using these..
#resource.setrlimit(resource.RLIMIT_DATA,(s,h))
#The maximum size (in bytes) of the process s heap.
#resource.setrlimit(resource.RLIMIT_STACK(s,h))
#The maximum size (in bytes) of the call stack for the current process.
#resource.setrlimit(resource.RLIMIT_NPROC,(4,4))
#The maximum number of processes the current process may create.
#soft,hard=resource.getrlimit(resource.RLIMIT_CPU)
#print 'cpu Soft limit starts as  :', soft
#print 'cpu hard limit starts as  :', hard
