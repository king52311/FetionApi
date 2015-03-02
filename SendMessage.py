 #coding:utf-8
 #author Find
 #date:2014-11-27
import httplib
import urllib
#post方式访问
httpclient=None;
try:
	params=urllib.urlencode({'user':'12345678','key':'123456','number':'123456','text':'Findspace.name crashed!'})
	headers={'Content-type':'application/x-www-form-urlencoded','Accept':'text/plain'}
	httpclient=httplib.HTTPConnection('openfetionapi.sinaapp.com')
	httpclient.request('POST','/fetion.php',params,headers)
	response=httpclient.getresponse()
	if(response.status>=300 and response.status<200):
		print('Sending Fetion Message Failed')
	else:
		print(response.read())
finally:
	if httpclient:
		httpclient.close()