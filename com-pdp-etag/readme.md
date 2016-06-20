endpoints:
1) GET: http://localhost:8080/com-pdp-etag/user-service/users/1

headers:
Content-Type:application/soap+xml
Cache-Control:public, max-age=86400
if-none-match:XXXXXXX

2) PUT: http://localhost:8080/com-pdp-etag/user-service/users/1
