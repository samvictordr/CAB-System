 # CABS ![buildstat](https://github.com/samvictordr/CAB-System/actions/workflows/Build.yml/badge.svg)
Java application backend for certificates and badges cross validation and digital signature verification. Currently a work in progress.


> [!NOTE]
> Currently, the project exists in a preliminary planning stage. This means the actual backend API is not yet built(if you're a bit Java Savvy, you would have been able to tell form the weird file structure)
> I'm currently working on the API, all commits after 238afbf should be for the API.

## Installation and Set-up instructions(Local Setup):
clone the project, navigate into the folder you downloaded. Run this to set the MySQL database tables set up:

```shell
chmod +x SQLSetup.sh
./SQLSetup.sh
```

To complile and run the project:

```shell
chmod +x build.sh
./build.sh
```

## Setup and build on Docker:
Clone the project, navigate into the uncompressed folder, and then:

```shell
docker-compose up --build
```

After which the image should build and run. At this point, you can see the built container running on your docker dashboard. To attach this container to your terminal, use ```docker ps``` to find your container ID and then run:

```shell
docker attach <container_id>
```
