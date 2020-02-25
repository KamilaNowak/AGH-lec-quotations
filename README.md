# AGH-lec-quotations


## An introduction to API
>API is provided for every purposes and can be used by anyone

>Project is primarly developed for self-improvement of web services


## Usage

API is deployed on https://agh-quots.herokuapp.com/

## API Endpoints
To access data user is obliged to geneate access token to authorize requests

#### Register user
URL: https://agh-quots.herokuapp.com/auth/register
```javascript
{
  "name": "Your Name",
  "password": "Your password",
  "email": "Your email",
}
```
#### Get access token
URL: https://agh-quots.herokuapp.com/auth/user
```javascript
{
	"name": "Your Name",
	"password": "Your password"
}
```
#### Example response
```javascript
{
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVc2VyIiwiaWF0IjoxNTgyNjU5MjQxfQ.hWSkGxK_Rcegze-YGAUG3Ls4oohOjtdLz**********",
    "userResponse": {
        "name": "User",
        "email": "user@email.com",
        "authorities": [
            {
                "id": 1,
                "authority": "ROLE_USER"
            }
        ]
    }
}
```

### Fetching data
#### Fetch JSON Array of quotations
URL: https://agh-quots.herokuapp.com/quotations

Put your authorization Bearer token in headers 
```
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVc2VyIiwiaWF0IjoxNTgyNjU5MjQxfQ.hWSkGxK_Rcegze-YGAUG3Ls4oohOjtdLz**********
```

#### Fetch JSON Array of quotations based on faculty
URL: https://agh-quots.herokuapp.com/quotations?f=<faculty>

Put your authorization Bearer token in headers 
```
eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJVc2VyIiwiaWF0IjoxNTgyNjU5MjQxfQ.hWSkGxK_Rcegze-YGAUG3Ls4oohOjtdLz**********
```
#### Faculty variables


Field | Description
------|------------
**parameter** | Faculty
WIMiIP | Faculty of Materials Engineering and Industrial CS
WFiIS | Faculty of Physics and Applied Computer Science
etc

## Built With

* [Spring Framework](https://spring.io/) - The web framework
* [Hibernate](https://hibernate.org/) - Framework for managing data
* [Maven](https://maven.apache.org/) - Dependency Management
* [MySQL](https://www.mysql.com/) - Database
* [Heroku](https://dashboard.heroku.com/) - Server where app is deployed

## Author

* **Kamila Nowak** - *Initial work* - [KamilaNowak](https://github.com/KamilaNowak)
