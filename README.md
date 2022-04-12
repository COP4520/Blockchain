# Scaling Blockchain With Parallel Processing

### Note: This README is a work in progress.

## Building The Project
This Java project is built and managed by [Maven](https://maven.apache.org/what-is-maven.html).

Details for installing Maven can be found [here](https://maven.apache.org/install.html).

You can verify if Maven is installed by using the `mvn --version` command.

### Build Steps:
1. Navigate to the base directory of the repository, then choose a version of the project you 
   want to build and navigate to it.
2. Use the `mvn clean package` command to clean and build the project.

## Deploying The Project
The project must be built before it can be deployed.
### Deploy Steps:
1. To deploy the program, use the following commands for each version:

#### v1
`java -jar v1_Proof_of_Work-1.0-SNAPSHOT-jar-with-dependencies.jar`

#### v2
Server:
`java -jar v2_Proof_of_Stake-1.0-SNAPSHOT-jar-with-dependencies.jar --server`

Client:
`java -jar v2_Proof_of_Stake-1.0-SNAPSHOT-jar-with-dependencies.jar`

#### v3
Server:
`java -jar v3_Sharding-1.0-SNAPSHOT-jar-with-dependencies.jar --server`

Client:
`java -jar v2_Sharding-1.0-SNAPSHOT-jar-with-dependencies.jar`
