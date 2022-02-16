# PhoneNumberCategorizer

An app to categorized phone numbers by country 

Check both the [Backend](Backend/README.md) and the [Frontend](Frontend/README.md)

## Target platform
Any system with functioning web browser

## Credits 

I created this from scratch


## Run instructions

- install docker
- execute the command `docker-compose up` this will use docker to start the application with the backend at port 8888 and frontend at port 4200 (use `--build --force-recreate` options in case you need a clean start)
- navigate to http://localhost:4200/ to explore the application using the frontend
## How to use the UI
  - write the country name to filter by or leave empty to get all then click apply filter
  - select the desired phone state from the drop down then click apply filter
  - change the page size by change it's input field then clicking apply filter
  - use the navigation bar at the bottom to navigate throw pages (note: doesn't show all pages at once)
  - page size auto shrinks to fit displayed content
  - the phone field's color represents it's state (green is valid, red is invalid)
  
  hint: you can fetch 0 size pages too, cause why not?
