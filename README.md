#### Chat API

a simple ephemeral text message (chat) service
#### Build application

    gradle build
#### Run Tests

    ./gradlew test
#### Run Application

    gradle bootRun

### End points

    POST /api/chats
##### Post body

    {
		"text": "string",
		"userName": "string",
		"timeout":"integer"
	}
##### Response Body

    {
		"id": "integer"
	}
GET /chats/:id
##### Response Body

    {
	    "userName": "string",
	    "text": "string",
	    "expirationDate": "date"
    }
GET /chats/:username
##### Response Body

    [
	    {
	        "id": "integer",
	        "text": "string"
	    }
    ]
See included **Swagger doc** for thorough documantion of API.