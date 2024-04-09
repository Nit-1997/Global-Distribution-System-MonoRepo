# How to access BigQuery in your springboot application

- ### Create a service account in GCP and provide it with the following access
  - BigQuery Admin
  - BigQuery Data Viewer
  - BigQuery Data Editor

- ### Download the private key in .json format

- ### Create a project in GCP and a dataset in bigquery , load a table with sample csv data through the GCP console.
  
- ### Add following properties to application properties file.
  - spring.application.name=gcpBQ
  - server.port=8080
  - spring.cloud.gcp.credentials.location=file:<PATH>/gcp_creds.json
  - spring.cloud.gcp.bigquery.dataset-name=flight_schedule

- ### Build the following simple REST endpoint to query the big query Database over a specific timestamp
  - POST ```/ingestCSV``` : ingest CSV file data to bigQuery Table.
  - POST ```/ingestJSON``` : ingest JSON file data to bigQuery Table.
  - GET ```/cheapestTrip?stops=2&timestampStart=213432&timestampEnd=243434&src=JFK&dest=DFW``` : Gets the cheapest flights sequence cost with k stops between certain timestamps from a particular source to a particular destination using Bellman Ford Algorithm. 
  - GET ```/list?timestampStart=243325&timestampEnd=332452&src=JFK&dest=DFW``` : Get all flights in the search duration between a source and destination.


