# Backend using spring

The backend for this application uses spring boot and jdk 11

## Run instructions

- install maven and add it to the environment variables path
- execute the command ` mvn spring-boot:run` this will start the application at the default port 8888
- navigate to http://localhost:8888/app/customers to get the full json response
- specify these query parameters to customize the default behaviour
    - country: to filter by country
    - state: to filter by phone state
    - page: to specify page
    - size: to change default page size
