# How to publish flights to GCP/PUB-SUB in your springboot application

- ### Create a service account in GCP and provide it with the following access
    - Pub/Sub Message Producer 

- ### Download the private key in .json format

- ### Create a project in GCP and a dataset in bigquery , load a table with sample csv data through the GCP console.

- ### Add following properties to application properties file.
    - server.port=8080
    - spring.cloud.gcp.credentials.location=classpath:gcp_creds.json
   
- ### Build the following simple REST endpoint to publish flight to GCP pub/sub
    - POST ```/ingestor/publish``` : publish a flight to gcp pub/sub.


### Architechture Diagram

![alt text](https://github.com/Nit-1997/Global-Distribution-System-MonoRepo/blob/main/realTimeIngestorService/ingestorRTarch.png)
