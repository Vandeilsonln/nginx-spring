# Nginx as Load Balancer

The purpose of this project is to use Nginx as a load balancer for two instances of a Spring Boot application.
In this project, Nginx is also used for rate limiting and authentication.
The setup uses Docker and Docker Compose to containerize the application to manage the services
and it also contains the Postgres initialization file.

## Table of Contents
- [Nginx Configuration](#nginx-configuration)
- [Docker Compose Setup](#docker-compose-setup)
- [Dockerfile](#dockerfile)
- [Database Initialization](#database-initialization)
- [How to Run](#how-to-run)
- [Conclusion](#conclusion)

## 1 -  Nginx Configuration
The `nginx.conf` file holds the Nginx configurations:

###  - Security
```nginx
http {
    auth_basic "Restricted Access";
    auth_basic_user_file /etc/nginx/.htpasswd;
    limit_req_zone $binary_remote_addr zone=mylimit:10m rate=5r/s;
}
```
The code above shows that a basic authentication is used, and that there is a file named `.htpasswd` that stores the allowed users and their respective passwords.
#### *For learning purposes, a sample entry `user1:123` is included directly in the file. However, in a production environment, it's crucial to secure these credentials properly and not expose them in the configuration file or the repository.*

### - Rate Limiting
The rate limit is defined in the `http` block in this line:
```nginx
limit_req_zone $binary_remote_addr zone=mylimit:10m rate=5r/s;
```
In this example, it is allowed no more than 5 requests por second from each unique IP Address. This will protect the application from excessive load.

Also, in the `location` block (under the `server` block), there is this piece of code:
```nginx
limit_req zone=mylimit burst=10 delay=3;
```
`burst=10:` Allows a burst of up to 10 requests before the rate limit is enforced. This means if more than 5 requests per second (the rate set in `limit_req_zone`) are received, up to 10 additional requests will be accepted before Nginx starts delaying or rejecting requests.

`delay=3`: Sets a delay of 3 seconds for requests that exceed the burst limit. This helps to smooth out the request rate rather than rejecting requests immediately.

## 2 - DOCKERFILE
The DOCKERFILE makes use of multi-stage build in order to reduce the final image size and to make sure
that the image will contain only the necessary files to run the application (the `.jar file`, in this case).

#### *Note: The `-DskipTests` flag is used here to skip the tests in order to speed up the build process. However, in this case, it is extremely important to run the tests in a Continuous Integration (CI) environment to ensure code quality.*

# 3 - Docker Compose
The file configures 2 instances of the application, each one of them with a memory limit of `255MB` and `0.7 CPU`.

The nginx contains 2 volumes mounted: one for the nginx configuration and another for the HTTP basic authentication.

The database contains 1 volume mounted for the DB initialization

# 4 - Postgres Initialization
The file `init.sql` is responsible for creating the schema and to insert some initial data into the tables for the application

# 5 - How to run
Clone the repository: `git clone https://github.com/Vandeilsonln/nginx-spring.git`

Then, navigate to the project root `cd nginx-spring`

Finally, build and start the services using `docker-compose up -d --build`

As a final step, read the API section to check how to use it.

# 6 - API Usage
The entrypoint is the Nginx at `localhost:9999`. The curl bellow already include a authenticated user

#### - Create Transaction
```curl
curl --location --request POST 'localhost:9999/transaction/2' \
--header 'Authorization: Basic dXNlcjE6MTIz' \
--header 'Content-Type: application/json' \
--data-raw '{
    "amount": 2204,
    "type": "D"
}'
```

#### - Get all transaction from consumer
```curl
curl --location --request GET 'localhost:9999/customer/2/transactions-history' \
--header 'Authorization: Basic dXNlcjE6MTIz'
```