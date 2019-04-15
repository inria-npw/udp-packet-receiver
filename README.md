# Udp Packet Receiver

This project is part of the **On Predicting Aggregation** research. The program is meant to receive packets sent by the sender program, mark their time of arrival and create JSON files using those times.

## Getting started

The steps presented below were tested using Ubuntu 18.10.

### Prerequisites

This project requires JDK 8 and Gradle to set up and to run.

### Building the project

To build the project, simply run the following command at the root of the project:

```bash
gradle build
```

It should create the file _./build/libs/udp-packet-receiver-[version-number].jar_ where [version-number] is the current version of the program.

### Running the program

In the same directory as the previously generated _.jar_ file, use:

```bash
java -jar udp-packet-receiver-[version-number].jar
```

Replace [version-number] with the current version of the program.

### Commands

While the program is executing, it will listen on port 5020 for udp packets. Here is the list of commands you can enter:

1. _dump_ will dump all the accumulated packets.

1. _size_ will show the current number of accumulated packets.

1. Entering another string, the program will try to create a JSON file with the data from all the accumulated packets. If the creation is successful, all the accumulated packets will be dumped.

1. _ctrl+c_ will exit the program.

## Authors

* **William Lebel** - *Initial work* - william.leb@hotmail.com

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details