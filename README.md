# Scaling Blockchain With Parallel Processing

## More detailed information about this project can be found in the included report files.

## Building The Project
This Java project is built and managed by [Maven](https://maven.apache.org/what-is-maven.html).

Details for installing Maven can be found [here](https://maven.apache.org/install.html).

You can verify if Maven is installed by using the `mvn --version` command.

### Build Steps:
1. Navigate to the **base directory** of the repository, then choose a version of the project you 
   want to build and navigate to it.
2. Use the `mvn clean package` command to clean and build the project.

## Deploying The Project
The project must be built before it can be deployed.

To deploy the program, use the following commands for each version:

### v1 - Proof of Work
`java -jar target\v1_Proof_of_Work-1.0-SNAPSHOT-jar-with-dependencies.jar`

### v2 - Proof of Stake with TCP Server
Server:
`java -jar target\v2_Proof_of_Stake-1.0-SNAPSHOT-jar-with-dependencies.jar --server`

Client:
`java -jar target\v2_Proof_of_Stake-1.0-SNAPSHOT-jar-with-dependencies.jar`

### v3 - Sharding
Server:
`java -jar target\v3_Sharding-1.0-SNAPSHOT-jar-with-dependencies.jar --server`

Client:
`java -jar target\v3_Sharding-1.0-SNAPSHOT-jar-with-dependencies.jar`

**Note:** For v2 and v3, you can launch multiple clients to simulate multiple stakers.
