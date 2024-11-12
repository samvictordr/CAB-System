#!/bin/bash

# build.sh
# Script to compile and run the Java application with progress indicators

# Function to display a spinner while a command is running
spinner() {
    local pid=$1
    local delay=0.1
    local spin_chars='|/-\'
    while kill -0 "$pid" 2>/dev/null; do
        for char in $(echo $spin_chars | fold -w1); do
            printf "\r%s" "$char"
            sleep $delay
        done
    done
    printf "\r"
}

# Compile the Java source files
echo "Compiling Java source files..."
javac -cp ".:lib/mysql-connector-j-9.1.0.jar" -d bin $(find src -name "*.java") &
compilation_pid=$!
spinner $compilation_pid
wait $compilation_pid

if [ $? -ne 0 ]; then
    echo "Compilation failed."
    exit 1
else
    echo "Compilation successful."
fi

# Run the Java application
echo "Running the application..."
java -cp ".:lib/mysql-connector-j-9.1.0.jar:bin" services.MainApplication
run_pid=$!
spinner $run_pid
wait $run_pid

echo "Application has exited."