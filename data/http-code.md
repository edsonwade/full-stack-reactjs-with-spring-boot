# API README 🚀

# HTTP Status Codes 🚦

## Informational 1xx ℹ️

- **100 Continue**: The server has received the initial part of the request and is waiting for the client to send the remainder.
- **101 Switching Protocols**: The server understands and is willing to comply with the client's request to switch protocols.

## Success 2xx ✅

- **200 OK**: The request has succeeded.
- **201 Created**: The request has been fulfilled and a new resource has been created.
- **202 Accepted**: The request has been accepted for processing, but the processing has not been completed.
- **204 No Content**: The server has successfully fulfilled the request, but there is no content to send in the response.
- **206 Partial Content**: The server has fulfilled the partial GET request for the resource.
- **208 Already Reported**: The members of a DAV binding have already been enumerated in a preceding part of the (multistatus) response, and are not being included again.

## Redirection 3xx 🔄

- **300 Multiple Choices**: The requested resource has multiple representations, each with its own specific location.
- **301 Moved Permanently**: The requested resource has been permanently moved to a new location.
- **302 Found**: The requested resource has been temporarily moved to a different location.
- **304 Not Modified**: The client's cached copy is still valid and the server has not modified the requested resource.

## Client Errors 4xx ❌

- **400 Bad Request**: The server cannot process the request due to a client error (e.g., malformed request syntax).
- **401 Unauthorized**: The request requires user authentication.
- **403 Forbidden**: The server understood the request but refuses to authorize it.
- **404 Not Found**: The server cannot find the requested resource.
- **405 Method Not Allowed**: The method specified in the request is not allowed for the resource.
- **408 Request Timeout**: The client did not produce a request within the time that the server was prepared to wait.
- **409 Conflict**: The request could not be completed due to a conflict with the current state of the resource.
- **410 Gone**: The requested resource is no longer available at the server and no forwarding address is known.

## Server Errors 5xx 🛠️

- **500 Internal Server Error**: The server encountered an unexpected condition that prevented it from fulfilling the request.
- **501 Not Implemented**: The server does not support the functionality required to fulfill the request.
- **502 Bad Gateway**: The server received an invalid response from an upstream server while attempting to fulfill the request.
- **503 Service Unavailable**: The server is currently unable to handle the request due to temporary overloading or maintenance of the server.
- **504 Gateway Timeout**: The server did not receive a timely response from an upstream server while attempting to fulfill the request.
- **505 HTTP Version Not Supported**: The server does not support or refuses to support the HTTP protocol version that was used in the request message.



## HTTP Status Codes for Each Endpoint Operation

### GET 📥

- **200 OK**: The request has succeeded, and the response contains the requested data.
- **404 Not Found**: The requested resource does not exist.
- **400 Bad Request**: The server cannot process the request due to client error, such as invalid parameters.
- **401 Unauthorized**: The client must authenticate to access the resource.
- **403 Forbidden**: The client does not have permission to access the resource.
- **500 Internal Server Error**: The server encountered an unexpected condition while processing the request.

### POST 📤

- **201 Created**: The request has been fulfilled, and a new resource has been created. The URI of the created resource is returned in the response.
- **400 Bad Request**: The server cannot process the request due to client error, such as missing required parameters or invalid data.
- **401 Unauthorized**: The client must authenticate to perform the action.
- **403 Forbidden**: The client does not have permission to perform the action.
- **500 Internal Server Error**: The server encountered an unexpected condition while processing the request.

### PUT 🔄

- **200 OK**: The request has succeeded, and the resource has been updated.
- **201 Created**: The request has been fulfilled, and a new resource has been created (if applicable).
- **204 No Content**: The request has succeeded, but there is no content to return in the response.
- **400 Bad Request**: The server cannot process the request due to client error, such as invalid parameters or data.
- **401 Unauthorized**: The client must authenticate to perform the action.
- **403 Forbidden**: The client does not have permission to perform the action.
- **404 Not Found**: The requested resource does not exist.
- **500 Internal Server Error**: The server encountered an unexpected condition while processing the request.

### PATCH 🔨

- **200 OK**: The request has succeeded, and the resource has been updated.
- **204 No Content**: The request has succeeded, but there is no content to return in the response.
- **400 Bad Request**: The server cannot process the request due to client error, such as invalid parameters or data.
- **401 Unauthorized**: The client must authenticate to perform the action.
- **403 Forbidden**: The client does not have permission to perform the action.
- **404 Not Found**: The requested resource does not exist.
- **500 Internal Server Error**: The server encountered an unexpected condition while processing the request.

### DELETE 🗑️

- **200 OK**: The request has succeeded, and the resource has been deleted.
- **204 No Content**: The request has succeeded, but there is no content to return in the response.
- **401 Unauthorized**: The client must authenticate to perform the action.
- **403 Forbidden**: The client does not have permission to perform the action.
- **404 Not Found**: The requested resource does not exist.
- **500 Internal Server Error**: The server encountered an unexpected condition while processing the request.