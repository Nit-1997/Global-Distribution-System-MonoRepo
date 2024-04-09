# Global Distribution System

### Project Components
- ```Stream Ingestor``` : Ingest the flight data continuously to a pub-sub in GCP (Similar to a continuous addition of flights)
- ```Batch Ingestor``` : Ingest the flight data in bulk , via JSON or CSV (Similar to bulk additions by big airlines)
- ```Data Aggeregator/Processor``` : Process the ingested data filtering out any misplaced values and aggregate the hourly data to BigQuery in GCP. 
- ```API layer```: Provided REST and GraphQL APIs to interact with the Global Distribution System, Including things like finding cheapest flights b/w 2 cities within k stops using Bellman Ford Algorithm, etc.

### Architechture Diagram

![alt text](https://github.com/Nit-1997/Global-Distribution-System-MonoRepo/blob/main/arch_diag.png)

