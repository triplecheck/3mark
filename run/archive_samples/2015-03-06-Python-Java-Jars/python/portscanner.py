# Script Name	: portscanner.py
# Author		: Craig Richards
# Created		: 20 May 2013 
# Last Modified	: 
# Version		: 1.0

# Modifications	: 

# Description	: Port Scanner, you just pass the host and the ports

import optparse				# Import the module
from socket import *		# Import the module
from threading import *		# Import the module

screenLock = Semaphore(value=1)		# Prevent other threads from preceeding

def connScan(tgtHost, tgtPort):		# Start of the function
	try:
		connSkt = socket(AF_INET, SOCK_STREAM)	# Open a socket
		connSkt.connect((tgtHost, tgtPort))
		connSkt.send('')
		results=connSkt.recv(100)
		screenLock.acquire()		# Acquire the lock
		print '[+] %d/tcp open'% tgtPort
		print '[+] ' + str(results)
	except:
		screenLock.acquire()
		print '[-] %d/tcp closed '% tgtPort
	finally:
		screenLock.release()
		connSkt.close()
		

